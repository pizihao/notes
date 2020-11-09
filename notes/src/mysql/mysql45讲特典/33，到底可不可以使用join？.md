到底可不可以使用join？

- 使用 join 语句，性能比强行拆成多个单表执行 SQL 语句的性能要好；
- 如果使用 join 语句的话，需要让小表做驱动表。
- 已上两个结论的条件是可以使用被驱动表的索引
- 如果被驱动表没有索引，会对所有关联的表全表扫描

> 现在 evayinfo_test_cs 表 有1000条数据 evayinfo_test_cs1 有 100 条数据

~~~SQL
EXPLAIN SELECT * FROM evayinfo_test_cs1 STRAIGHT_JOIN evayinfo_test_cs ON ( evayinfo_test_cs.a = evayinfo_test_cs1.a );
~~~

以evayinfo_test_cs1做驱动表

可以看到结果 对evayinfo_test_cs1表做了全表扫描，对evayinfo_test_cs表使用了索引a 查询的rows为101，总共读了101行，作为连接的条件，最好有索引



- 对于两个关联表来说，什么叫做小表呢？在决定哪个表做驱动表的时候，应该是两个表按照各自的条件过滤，过滤完成之后，计算参与 join 的各个字段的总数据量，数据量小的那个表，就是“小表”，应该作为驱动表。



关于Simple Nested-Loop Join(NLJ)和Block Nested-Loop Join (BNL)两个算法 被驱动表没有索引的情况下

> NLJ

由于被驱动表的条件字段上没有索引，因此每次到被驱动表去匹配的时候，就要做一次全表扫描。

> BNL

流程是这样的

- 把驱动表的数据读入线程内存join_buffer中，把所查询的字段拿出放入内存中，如果查询的是*就把整个表拿出来放到内存中
- 扫描被驱动表，把被驱动表中的每一行拿出来，跟join_buffer中的数据做对比，满足join条件的，作为结果集的一部分返回

![img](https://static001.geekbang.org/resource/image/15/73/15ae4f17c46bf71e8349a8f2ef70d573.jpg)

如果join_buffer太小放不下的话会分段处理



![img](https://static001.geekbang.org/resource/image/69/c4/695adf810fcdb07e393467bcfd2f6ac4.jpg)