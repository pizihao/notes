# “order by”是怎么工作的？

- sort_buffer 用于排序的缓冲区，可以使用sort_buffer_size设置该值的大小。InnoDB默认为256KB

> 全字段排序

- 当查询的记录需要排序的时候，MySQL 会给每个线程分配一块内存用于排序，称为 sort_buffer。

- 如果要排序的数据量小于 sort_buffer_size，排序就在内存中完成。但如果排序数据量太大，内存放不下，则不得不利用磁盘临时文件辅助排序。

- 判断一个语句是否使用了临时文件

  ~~~sql
  /* 打开optimizer_trace，只对本线程有效 */
  SET optimizer_trace='enabled=on'; 
  
  /* @a保存Innodb_rows_read的初始值 */
  select VARIABLE_VALUE into @a from  performance_schema.session_status where variable_name = 'Innodb_rows_read';
  
  /* 执行语句 */
  select city, name,age from t where city='杭州' order by name limit 1000; 
  
  /* 查看 OPTIMIZER_TRACE 输出 */
  SELECT * FROM `information_schema`.`OPTIMIZER_TRACE`\G
  
  /* @b保存Innodb_rows_read的当前值 */
  select VARIABLE_VALUE into @b from performance_schema.session_status where variable_name = 'Innodb_rows_read';
  
  /* 计算Innodb_rows_read差值 */
  select @b-@a;
  ~~~

- 可以从以上命令执行出来的number_of_tmp_files查看出是否使用了临时文件，他表示的是排序过程是使用到的临时文件数，存放不下时，就需要使用外部排序，外部排序一般使用归并排序算法。需要创建多个文件，每个文件单独排序后再合并成一个单独的大文件

- 如果 sort_buffer_size大于需要排序的数据量的大小，number_of_tmp_files 就是 0，表示排序可以直接在内存中完成。不需要借助磁盘等外存

- 以上命令出现结果的examined_rows是指参与排序的行数

- packed_additional_fields 排序过程对字符串做了“紧凑”处理

![img](https://img-blog.csdnimg.cn/20190509152315130.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzI5MDY2MzI5,size_16,color_FFFFFF,t_70)

> rowid 排序

- mysql 中有一个参数max_length_for_sort_data
- 这个参数是专门控制用于排序的行数据长度的参数，如果单行超过了这个值，就代表单行太长，mysql会更换一个排序算法
- rowid 方式和全字段方式一样，需要先把查询到的结果全部放在内存或硬盘中，再使用相关算法进行排序。而排序后由于没有保存所需的字段，需要按顺序使用主键再从索引树上查询，查到一个就返回一个，而不用把所有内容查完放到内存上再一并返回。

![img](https://img-blog.csdnimg.cn/20190509152509778.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzI5MDY2MzI5,size_16,color_FFFFFF,t_70)

- 如果 MySQL 实在是担心排序内存太小，会影响排序效率，才会采用 rowid 排序算法，这样排序过程中一次可以排序更多行，但是需要再回到原表去取数据。
- 如果 MySQL 认为内存足够大，会优先选择全字段排序，把需要的字段都放到 sort_buffer 中，这样排序后就会直接从内存里面返回查询结果了，不用再回到原表去取数据。
- mysql的设计思想：如果内存够，就要多利用内存，尽量减少磁盘访问。
- 对于 InnoDB 表来说，rowid 排序会要求回表多造成磁盘读，因此不会被优先选择。
- 覆盖索引是指，索引上的信息足够满足查询请求，不需要再回到主键索引上去取数据。
- 相当于全字段排序 rowid排序会多一次回表，拿到之前的数据
- 关于排序时占用的内存在排序完成后就free掉归还给系统了
- sort_buffer是在server层的
- 关于排序的时候的总行数，在server层每一次调用存储引擎的数据接口的时候所扫描的行数都会算一次扫描，如果在某一查询过程中，存储引擎的接口被调用了两次，那么这次查询所扫描的总行数为这两次扫描的行数相加
- 存储引擎内部自己调用接口查询，或者回表查询是不会计入扫描次数的，参照inplace DDL
- 关于binlog_row_image 前提是binlog格式必须为row格式或者mixed格式
  - binlog_row_image=FULL,binlog记录所有数据的前后镜像，是最安全的设置，但性能也是最低的，如果数据出现误操作，可以能通过flashback或binlog2sql等快速闪回工具恢复数据，如果对短时间前后数据安全性要求比较高，推荐此设置。
  - binlog_row_image=MINIMAL,binlog日志的前镜像只记录唯一识别列(唯一索引列、主键列)，后镜像只记录修改列，性能是最高的，占用的资源等也是最少的，如果数据出现误操作，不能通过flashback或binlog2sql等快速闪回工具恢复数据。
  - binlog_row_image=NOBLOB,binlog记录所有的列，就像full格式一样。但对于BLOB或TEXT格式的列，如果他不是唯一识别列(唯一索引列、主键列)，或者没有修改，那就不记录。此设置可以简单理解为在没有text和blob等大字段时，效果同FULL一样；在有text和blob等大字段时，如果大字段不是唯一识别列(唯一索引列、主键列)，或者没有修改，那就不记录。

