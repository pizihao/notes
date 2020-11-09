join语句怎么优化？

> Multi-Range Read 优化

- Multi-Range Read 优化 (MRR)。这个优化的主要目的是尽量使用顺序读盘。
- 回表是指，InnoDB 在普通索引 a 上查到主键 id 的值后，再根据一个个主键 id 的值到主键索引上去查整行数据的过程。这个过程是一行一行的查数据
- 如果随着索引的值递增顺序查询的话，id 的值就变成随机的，那么就会出现随机访问，性能相对较差。
- 因为大多数的数据都是按照主键递增顺序插入得到的，所以我们可以认为，如果按照主键的递增顺序查询的话，对磁盘的读比较接近顺序读，能够提升读性能。
- 这就是 MRR 优化的设计思路：
  1. 根据索引 a，定位到满足条件的记录，将 id 值放入 read_rnd_buffer 中 ;
  2. 将 read_rnd_buffer 中的 id 进行递增排序；
  3. 排序后的 id 数组，依次到主键 id 索引中查记录，并作为结果返回。
  4. 这里，read_rnd_buffer 的大小是由 read_rnd_buffer_size 参数控制的。如果步骤 1 中，read_rnd_buffer 放满了，就会先执行完步骤 2 和 3，然后清空 read_rnd_buffer。之后继续找索引 a 的下个记录，并继续循环。
- 如果要使用MRR优化，需要设置set optimizer_switch="mrr_cost_based=off"
- ![img](https://static001.geekbang.org/resource/image/d5/c7/d502fbaea7cac6f815c626b078da86c7.jpg)
- MRR能够提升性能的核心在于，这条查询语句在索引 a 上做的是一个范围查询，可以得到足够多的主键 id。这样通过排序以后，再去主键索引查数据，才能体现出“顺序性”的优势。

> Batched Key Access BKA 是对NLJ算法的优化

![img](https://static001.geekbang.org/resource/image/68/88/682370c5640244fa3474d26cc3bc0388.png)

- 大概思路是，一次性在驱动表中拿出足够数量的值，放到join_buffer中，最后再一次性的传给被驱动表
- 然后顺序的查询出结果集
- 需要先设置：set optimizer_switch='mrr=on,mrr_cost_based=off,batched_key_access=on';

> BNL算法性能问题

大表 join 操作虽然对 IO 有影响，但是在语句执行结束后，对 IO 的影响也就结束了。但是，对 Buffer Pool 的影响就是持续性的，需要依靠后续的查询请求慢慢恢复内存命中率。

通过验证发现，即使是多表联查也只是驱动表会全表扫描，如果后面的表有索引的话，没有索引直接走全表扫描



