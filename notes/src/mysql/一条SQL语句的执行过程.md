### 一，没有索引

1. 一条SQL语句最先执行的是`from table_name` 把表文件加载到内存中
2. 其次 `where` 子句中的条件判断，这个过程中会把所有的数据进行过滤，取出符合条件的记录，生成一张临时的内存表
3. `group by ` 会把这个临时表切分
4. 如果存在having，则一般会与聚合函数一起使用
5. 在这个时候会去执行select 回去查看需要查询什么值，如果当前内存表中，全部存在就直接使用，如果没有则需要回表去查询
6. `oder by`使得查询出的数据会进行排序
7. limit 取结果中的前几个值

### 二，特殊的SQL语句

1. insert select 语句 查询添加

   - insert into \`table_name\`(\`id\`,\`name\`)  select \`id\`,\`name\` from \`table_name1\` where ...
   - 直接将查询出的结果并入添加中，类似于先查询后添加

2. insert update语句: 查询更新

   - ~~~sql
     update video t1
     	set t1.vcount= t1.vcount + (
     	select vcount from temp_video t2 
     	where t2.vid=t1.vid
     )
     ~~~

   - 查询出来的值可以直接作为set 后的值传进去

3. insert on duplicate key update语句: 添加或更新

   - insert和update分别代表添加子句和查询子句
   - on duplicate key 表示如果要插入的数据存在就进行修改，修改语句就是update子句

### 三，InnoDB详细

架构图解

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190311140955659.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMzNzQ1MTAy,size_16,color_FFFFFF,t_70)

#### 1，mysql中的内存

- mysql的设计运用一系列的算法，将对数据的大部分的操作都放到了内存中，这样减少的磁盘IO,极大提高了数据库的读写性能
- 数据在内存中是以数据页的形式存储的，某次查询的时候，如果要查询的数据已经在内存页中了，那么就不需要再去读一次磁盘了，如果在内存中没有命中的话，就需要去磁盘中获取，这种情况无疑是很慢的。
- mysql的设计是要求可以在内存中完成的操作就尽量在内存中完成。

![img](https://pic4.zhimg.com/80/v2-c2865d1ac1865223c79dfa4335e13773_720w.jpg)

- mysql中的内存部分是 buffer pool 即数据库缓冲池
- Buffer Pool中以页 为单位把从数据文件中取过来的记录连接为一个列表
- 这个列表中5/8是属于New SubList(新数据页区) 剩下的3/8是Old SubList(旧数据页区)这个比例可以通过 innodb_old_blocks_pct 这个值改变
- Midpoint  是新数据页（New SubList）列表的尾部与旧数据页（Old Sublist）列表的头相交的边界。
- 当一个页面读入缓冲池时，它首先会插入旧列表（Old Sublist）的头部如果再次读取到旧列表（Old Sublist）的数据页时（在一定时间内，由参数innodb_old_blocks_time来决定，默认1000ms）将其移动到新列表的的头部。
- 随着数据库的逐渐运行，中点数据的不断插入，新老页面都会随着其他页面的更新而老化，而最终未被使用到的页面将到达旧列表的尾部被淘汰掉。
- 写redolog的过程也是在内存中完成的，记录了对实际数据文件的物理变更，为了保证数据的持久性，InnoDB采用了WAL机制，日志优先落盘，也就是说在内存中实际修改后的脏页被刷到磁盘成为干净页之前，这一段的记录日志就已经落盘了
- 相关参数 
  - innodb_log_group_home_dir 指定redo日志的存放路径
  - innodb_log_files_size redolog文件大小
  - innodb_log_files_in_group redolog 日志组 已经废弃 此参数主要用于兼容以前的版本
- 写入过程
  - redolog 以循环的方式写入文件，当所有的文件写满的时候，会回到第一个文件的相应位置进行覆盖写
  - 其中有一个位置点叫writepos 它记录着当前的位置，一边写writepos一边向后移动，直到写完所有文件，再回到开头覆盖写入
  - checkpoint也是一个位置点，代表着内存中要刷盘的位置，checkpoint 和 writepos之间空着的部分可以用来写新的记录，其他部分需要刷盘
  - 当要写满的时候，需要把系统的更新停下来，然后把 checkpoint 向前推进
  - 写redolog的过程也是一个写磁盘的过程，不过这个过程是循序斜盘，效率要快多
- 写入时机 两阶段提交
  1. mysql接收到一条更新语句后，回去把要更新的数据页扫到内存中。
  2. 进行更新，把更新的操作记录到redolog，当然不是直接写入到redolog文件中，而是写入redo log buffer  ，再由redo log buffer顺序IO写盘到redolog文件。
  3. 然后更新内存数据页中的数据。
  4. redo log buffer顺序IO写盘到redolog文件，处于prepare阶段
  5. 系统生成binlog。binlog刷盘，这样就保证了数据的持久性
  6. 事务commit，这个时候就把redolog改成commit状态，更新完成
  7. 如果在1,2,3,的时候系统崩溃了，重启后查看redolog 会发现它记录的数据版本和系统数据文件的LSN相同，就说明数据文件根本没有被写入，直接当回滚处理
  8. 如果在4的过程中崩溃，发现redolog文件还没有到prepare阶段，直接回滚
  9. 如果在4之后系统崩溃，这个时候写入了redolog，并且处于prepare阶段，说明redolog已经写完了，这个时候可以通过redolog恢复binlog和数据。
- 写盘过程 
  - redolog写入磁盘的过程叫做fsync
  - fsync的时候把redologbuffer中的信息记录顺序写入到磁盘中
- 脏页落盘过程
  - 当 buffer pool 中的数据页达到一定量的脏页或 InnoDB 的 IO 压力较小 时，都会触发脏页的刷盘操作。
  - 当开启 double write 时，InnoDB 刷脏页时首先会复制一份刷入 double write，在这个过程中，由于double write的页是连续的，对磁盘的写入也是顺序操作，性能消耗不大。
  - 无论是否经过 double write，脏页最终还是需要刷入表空间的数据文件。刷入完成后才能释放 buffer pool 当中的空间。
  - insert buffer 也是 buffer pool 中的一部分，当 buffer pool 空间不足需要交换出部分脏页时，有可能将 insert buffer 的数据页换出，刷入共享表空间中的 insert buffer 数据文件中。
  - 当 innodb_stats_persistent=ON 时，SQL 语句所涉及到的 InnoDB 统计信息也会被刷盘到 innodb_table_stats 和 innodb_index_stats 这两张系统表中。
  - 有一些情况下可以不经过 double write 直接刷盘
    - 关闭 double write
    - 不需要 double write 保障，如 drop table 等操作
- 关于 double write
  - InnoDB buffer pool 一页脏页大小为 16 KB，如果只写了前 4KB 时发生宕机，那这个脏页就发生了写失败，会造成数据丢失。
  - 为了避免这一问题，InnoDB 使用了 double write 机制。
  - 在写入数据文件之前，先将脏页写入 double write 中，当然这里的写入都是需要刷盘的
  - 此外还有**Double write buffer** 
  - 脏页落盘前的备份
- Change Buffer  写缓冲
  - Change Buffer是对insert buffer的进阶，insert buffer只对insert做了优化，而Change Buffer对其他的更新操作也会有优化
  - 在更新操作的时候，要修改的数据不会被加载进内存，而是记录缓冲变更，等数据页被读取的时候，这个时候数据页会被加载进内存，然后和change buffer中的数据合并恢复到缓冲池
  - 从而降低磁盘IO，提升性能
- sort_buffer 
  - 用于排序
- `SELECT * FROM information_schema.INNODB_TRX` 查看正在执行的事务
-  `SELECT * FROM INFORMATION_SCHEMA.INNODB_LOCKS;` 查看正在锁的事务
- `SELECT * FROM INFORMATION_SCHEMA.INNODB_LOCK_WAITS;`查看等待锁的事务





