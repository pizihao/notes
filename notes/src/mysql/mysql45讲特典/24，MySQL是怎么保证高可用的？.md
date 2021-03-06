MySQL是怎么保证高可用的？

- 在一个主备关系中，每个备库接收主库的 binlog 并执行。正常情况下，只要主库执行更新生成的所有 binlog，都可以传到备库并被正确地执行，备库就能达到跟主库一致的状态，这就是最终一致性。

> 主备延迟

- 主备切换可能是一个主动运维动作，比如软件升级、主库所在机器按计划下线等，也可能是被动操作，比如主库所在机器掉电。
- 同步延迟
  - 主库 A 执行完成一个事务，写入 binlog，我们把这个时刻记为 T1;
  - 之后传给备库 B，我们把备库 B 接收完这个 binlog 的时刻记为 T2;
  - 备库 B 执行完成这个事务，我们把这个时刻记为 T3。
  - 所谓主备延迟，就是同一个事务，在备库执行完成的时间和主库执行完成的时间之间的差值，就是上面的T3-T1中间的时间
  - 可以在备库上通过show slave status命令查看备库延迟了多少秒 就是返回结果中的seconds_behind_master
  - seconds_behind_master的计算方法
    - 每个事务的 binlog 里面都有一个时间字段，用于记录主库上写入的时间；
    - 备库取出当前正在执行的事务的时间字段的值，计算它与当前系统时间的差值，得到 seconds_behind_master。
  - 网络正常情况下，主备延迟的主要来源是备库接收完 binlog 和执行完这个事务之间的时间差。
- 主备延迟的来源
  - 首先，有些部署条件下，备库所在机器的性能要比主库所在的机器性能差。
  - 备库上的查询耗费了大量的 CPU 资源，影响了同步速度，造成主备延迟。
    - 处理：一主多从，除了备库外，可以多接几个从库，让这些从库来分担读的压力。
    - 通过 binlog 输出到外部系统，比如 Hadoop 这类系统，让外部系统提供统计类查询的能力。
  - 大事务，大事务会造成主备延迟
  - 大表DDL ，数据定义
  - 备库的并行复制能力

> 主备切换的策略

- 可靠性优先策略
  - 采用这个策略系统会有一段不可用时间，这个时间段中可能有一大部分是在等到主备延迟减少至0，所以必须提前判断出主备延迟是多少。如果太大就不做切换
- 可用性优先策略
  - 使用 row 格式的 binlog 时，数据不一致的问题更容易被发现。而使用 mixed 或者 statement 格式的 binlog 时，主备之间数据可能会不一致
  - 主备切换的可用性优先策略会导致数据不一致。
- 在满足数据可靠性的前提下，MySQL 高可用系统的可用性，是依赖于主备延迟的。延迟的时间越小，在主库故障的时候，服务恢复需要的时间就越短，可用性就越高。



