MySQL是怎么保证数据不丢的？

- binlog 的写入机制
- 事务执行过程中，先把日志写到 binlog cache，事务提交的时候，再把 binlog cache 写到 binlog 文件中。
- 一个事务的 binlog 是不能被拆开的，因此不论这个事务多大，也要确保一次性写入
- 系统给 binlog cache 分配了一片内存，每个线程一个，参数 binlog_cache_size 用于控制单个线程内 binlog cache 所占内存的大小。如果超过了这个参数规定的大小，就要暂存到磁盘。事务提交的时候，执行器把 binlog cache 里的完整事务写入到 binlog 中，并清空 binlog cache。
- binlog文件的write，fsync。write只是写到内存中，fsync才是真正的将内存中的binlog刷新到binlog磁盘文件中。
- write 和 fsync 的时机，是由参数 sync_binlog 控制的：
  - sync_binlog=0 的时候，表示每次提交事务都只 write，不 fsync；
  - sync_binlog=1 的时候，表示每次提交事务都会执行 fsync；
  - sync_binlog=N(N>1) 的时候，表示每次提交事务都 write，但累积 N 个事务后才 fsync。
- redo log 的写入机制
- 事务在执行过程中，生成的 redo log 是要先写到 redo log buffer 的。
- redolog 存在三种状态
  - 存在 redo log buffer 中，物理上是在 MySQL 进程内存中，
  - 写到磁盘 (write)，但是没有持久化（fsync)，物理上是在文件系统的 page cache 里面，
  - 持久化到磁盘，对应的是 hard disk。
- 为了控制 redo log 的写入策略，InnoDB 提供了 innodb_flush_log_at_trx_commit 参数，它有三种可能取值：
  - 设置为 0 的时候，表示每次事务提交时都只是把 redo log 留在 redo log buffer 中 ;
  - 设置为 1 的时候，表示每次事务提交时都将 redo log 直接持久化到磁盘；
  - 设置为 2 的时候，表示每次事务提交时都只是把 redo log 写到 page cache。
- InnoDB 有一个后台线程，每隔 1 秒，就会把 redo log buffer 中的日志，调用 write 写到文件系统的 page cache，然后调用 fsync 持久化到磁盘。
- 事务执行中间过程的 redo log 也是直接写在 redo log buffer 中的，这些 redo log 也会被后台线程一起持久化到磁盘。也就是说，一个没有提交的事务的 redo log，也是可能已经持久化到磁盘的。

> 还有两种情况会把redolog写入到磁盘

- 一种是，redo log buffer 占用的空间即将达到 innodb_log_buffer_size 一半的时候，后台线程会主动写盘。注意，由于这个事务并没有提交，所以这个写盘动作只是 write，而没有调用 fsync，也就是只留在了文件系统的 page cache。
- 另一种是，并行的事务提交的时候，顺带将这个事务的 redo log buffer 持久化到磁盘。假设一个事务 A 执行到一半，已经写了一些 redo log 到 buffer 中，这时候有另外一个线程的事务 B 提交，如果 innodb_flush_log_at_trx_commit 设置的是 1，那么按照这个参数的逻辑，事务 B 要把 redo log buffer 里的日志全部持久化到磁盘。这时候，就会带上事务 A 在 redo log buffer 里的日志一起持久化到磁盘。

> 组提交（group commit）机制

- 日志逻辑序列号（log sequence number，LSN） 
- LSN 是单调递增的，用来对应 redo log 的一个个写入点。每次写入长度为 length 的 redo log， LSN 的值就会加上 length。
- LSN 也会写到 InnoDB 的数据页中，来确保数据页不会被多次执行重复的 redo log
- 在并发更新场景下，第一个事务写完 redo log buffer 以后，接下来这个 fsync 越晚调用，组员可能越多，节约 IOPS 的效果就越好。