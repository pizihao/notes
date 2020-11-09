# insert语句的锁为什么这么多？

- insert 唯一键冲突
- 发生唯一键冲突时，并不只是简单的报错，还会在冲突的索引上加上锁
- ![img](https://static001.geekbang.org/resource/image/63/2d/63658eb26e7a03b49f123fceed94cd2d.png)
  - 在 T1 时刻，启动 session A，并执行 insert 语句，此时在索引 c 的 c=5 上加了记录锁。注意，这个索引是唯一索引，因此退化为记录锁。
  - 在 T2 时刻，session B 要执行相同的 insert 语句，发现了唯一键冲突，加上读锁；同样地，session C 也在索引 c 上，c=5 这一个记录上，加了读锁。
  - T3 时刻，session A 回滚。这时候，session B 和 session C 都试图继续执行插入操作，都要加上写锁。两个 session 都要等待对方的行锁，所以就出现了死锁。
  - ![img](https://static001.geekbang.org/resource/image/3e/b8/3e0bf1a1241931c14360e73fd10032b8.jpg)
  - insert into … on duplicate key update 这个语义的逻辑是，插入一行数据，如果碰到唯一键约束，就执行后面的更新语句。如果有多个列违反了唯一性约束，就会按照索引的顺序，修改跟第一个索引冲突的行。
- insert 语句如果出现唯一键冲突，会在冲突的唯一值上加共享的 next-key lock(S 锁)。因此，碰到由于唯一键约束导致报错后，要尽快提交或回滚事务，避免加锁时间过长。