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

#### 1，mysql把表文件加载到内存

- mysql的设计运用一系列的算法，将



