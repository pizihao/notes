MySQL是怎么保证主备一致的？

- 主库接收到客户端的更新请求后，执行内部事务的更新逻辑，同时写 binlog。
- 备库 B 跟主库 A 之间维持了一个长连接。主库 A 内部有一个线程，专门用于服务备库 B 的这个长连接。
- 在备库 B 上通过 change master 命令，设置主库 A 的 IP、端口、用户名、密码，以及要从哪个位置开始请求 binlog，这个位置包含文件名和日志偏移量。
- 在备库 B 上执行 start slave 命令，这时候备库会启动两个线程， io_thread 和 sql_thread。其中 io_thread 负责与主库建立连接。
- 主库 A 校验完用户名、密码后，开始按照备库 B 传过来的位置，从本地读取 binlog，发给 B。
- 备库 B 拿到 binlog 后，写到本地文件，称为中转日志（relay log）。
- sql_thread 读取中转日志，解析出日志里的命令，并执行。
- binlog有三种格式  binlog_format
  - statement
  - row
  - mixed
- 当 binlog_format=statement 时，binlog 里面记录的就是 SQL 语句的原文。
- row 格式的 binlog 里没有了 SQL 语句的原文，而是替换成了两个 event：Table_map 和 Delete_rows。
- mysqlbinlog -vv data/master.000001 --start-position=8900; 指定解析 binlog
- 为什么会有 mixed 格式的 binlog？
  - 因为有些 statement 格式的 binlog 可能会导致主备不一致，所以要使用 row 格式。
  - 但 row 格式的缺点是，很占空间。比如你用一个 delete 语句删掉 10 万行数据，用 statement 的话就是一个 SQL 语句被记录到 binlog 中，占用几十个字节的空间。但如果用 row 格式的 binlog，就要把这 10 万条记录都写到 binlog 中。
  - 这样做，不仅会占用更大的空间，同时写 binlog 也要耗费 IO 资源，影响执行速度。所以，MySQL 就取了个折中方案，也就是有了 mixed 格式的 binlog。mixed 格式的意思是，MySQL 自己会判断这条 SQL 语句是否可能引起主备不一致，如果有可能，就用 row 格式，否则就用 statement 格式。
- row格式下会记录所有的数据，包括修改的添加的删除的，所以可以恢复数据库
- 用 binlog 来恢复数据的标准做法是，用 mysqlbinlog 工具解析出来，然后把解析结果整个发给 MySQL 执行
- 主备的双M结构：两个数据库互为主备关系，有可能出现循环复制的问题，解决方法：
  - 规定两个库的 server id 必须不同，如果相同，则它们之间不能设定为主备关系；
  - 一个备库接到 binlog 并在重放的过程中，生成与原 binlog 的 server id 相同的新的 binlog；
  - 每个库在收到从自己的主库发过来的日志后，先判断 server id，如果跟自己的相同，表示这个日志是自己生成的，就直接丢弃这个日志。

