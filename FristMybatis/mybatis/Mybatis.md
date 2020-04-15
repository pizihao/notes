# Mybatis

## 一，简介

### 1，什么是mybatis

- MyBatis是一款优秀的持久层框架。
- 它支持定制化SQL、存储过程以及高级映射。
- MyBatis 避免了几乎所有的JDBC代码和手动设置参数以及获取结果集。
- MyBatis 可以使用简单的XML或注解来配置和映射原生类型、接口和Java的POJO (Plain Old Java Objects,普通老式Java对象)为数据库中的记录。

### 2，持久化

> 数据持久化

- 持久化就是将程序的数据在持久状态和瞬时状态转化的过程
- 内存：断电即失
- 数据库(jdbc),io文件持久化

> 为什么需要持久化

- 有一些对象，不能让它丢掉
- 内存太昂贵

### 3，持久层

> 学过的层

- DAO层
- Service层
- Controller层

> 持久层

- 完成持久化工作的代码块
- 层是界限十分明显的

### 4，为什么需要mybatis

- 帮助开发人员把数据存入到数据库中
- 方便
- 传统的JDBC代码太复杂了，简化操作，框架，自动化
- 不使用mybatis也可以，使用后更容易上手
- 优点：
  - 简单易学
  - 灵活
  - sq|和代码的分离，提高了可维护性。
  - 提供映射标签，支持对象与数据库的orm字段关系映射
  - 提供对象关系映射标签,支持对象关系组建维护
  - 提供xml标签,支持编写动态sql。

## 二，第一个mybatis程序

思路：搭建环境-->导入mybatis-->编写代码-->测试

### 1，搭建环境

- 搭建数据库

- 新建项目

  1. 新建一一个普通的maven项目

  2. 删除src目录

  3. 导入maven依赖

     ~~~xml
     <!--导入依赖-->
     <dependencies>
         <!--mysql-->
         <dependency>
             <groupId>mysql</groupId>
             <artifactId>mysql-connector-java</artifactId>
             <version>8.0.18</version>
         </dependency>
         <!--mybatis-->
         <dependency>
             <groupId>org.mybatis</groupId>
             <artifactId>mybatis</artifactId>
             <version>3.5.2</version>
         </dependency>
         <!--junit-->
         <dependency>
             <groupId>junit</groupId>
             <artifactId>junit</artifactId>
             <version>4.12</version>
         </dependency>
     </dependencies>
     ~~~

- 创建一个模块

  - 配置mybatis核心文件

    ~~~xml
    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
    <configuration>
    
        <!-- 别名 -->
        <!--<typeAliases>
            <package name="pojo"/>
        </typeAliases>-->
        <!-- 数据库环境 -->
        <environments default="development">
            <environment id="development">
                <transactionManager type="JDBC"/>
                <dataSource type="POOLED">
                    <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                    <property name="url" value="jdbc:mysql://localhost:3306/mybatis?characterEncoding=UTF-8&amp;useUnicode=true&amp;serverTimezone=UTC"/>
                    <property name="username" value="root"/>
                    <property name="password" value="123456"/>
                </dataSource>
            </environment>
        </environments>
        <!-- 映射文件 -->
        <!--<mappers>
            <mapper resource="pojo/Student.xml"/>
        </mappers>-->
    
    </configuration>
    ~~~

  - 编写mybatis工具类

    ~~~java
    // sqlSwssionFactory --> sqlSession
    public class MybaitsUtils {
        private static SqlSessionFactory sqlSessionFactory;
    
        static {
    
            try {
                // 获取mybatis第一获取sqlSessionFactory对象
                String resource = "mybatis-config.xml" ;
                InputStream inputstream = Resources.getResourceAsStream(resource);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputstream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    //        既然办了SqlSessionFactory. 顾名思义。我们就可以从中获得SqlSession的实例了。
    //        SqlSession完全包含了面向数据库执行sQL命令所需的所有方法。
        public static SqlSession getSqlSession(){
            return sqlSessionFactory.openSession();
        }
    }
    ~~~

- 编写代码

  - 实体类

    ~~~java
    // 实体类
    public class Users {
        private int id;
        private String name;
        private String password;
    
        @Override
        public String toString() {
            return "Users{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    
        public Users() {
        }
    
        public Users(int id, String name, String password) {
            this.id = id;
            this.name = name;
            this.password = password;
        }
    
        public int getId() {
            return id;
        }
    
        public void setId(int id) {
            this.id = id;
        }
    
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public String getPassword() {
            return password;
        }
    
        public void setPassword(String password) {
            this.password = password;
        }
    }
    ~~~

  - Mapper接口

    ~~~java
    public interface UsersMapper {
        List<Users> getUserList();
    }
    ~~~

  - 接口实现类由Impl换为xml

    ~~~xml
    <mapper namespace="com.liu.mapper.UsersMapper">
        <!--查询-->
        <select id="getUserList" resultType="com.liu.pojo.Users">
            select * from users
        </select>
    </mapper>
    ~~~

- 测试

  ~~~java
  org.apache.ibatis.binding.BindingException: Type interface com.liu.mapper.UsersMapper is not known to the MapperRegistry.
  ~~~

  绑定异常，每一个mapper.xml文件都需要在mybatis核心文件注册


### 2，CRUD

#### (1),namespace

命名空间中的中的包名要和Dao/mapper接口中的一致

#### (2),select

查询语句

- id：就是对应的namespace中的方法名

- resultType：Sql语句执行的返回值

- ~~~xml
  <!--查询-->
  <select id="getUserList" resultType="com.liu.pojo.Users">
      select * from users
  </select>
  <select id="getUserById" resultType="com.liu.pojo.Users">
      select * from users where id = #{id}
  </select>
  ~~~

#### (3),insert

~~~xml
<!--增加-->
<insert id="addUser" parameterType="com.liu.pojo.Users">
    insert into users(name, pwd) values (#{name}, #{pwd})
</insert>
~~~

#### (4),update

~~~xml
<!--修改-->
<update id="updateUser" parameterType="com.liu.pojo.Users">
    update users
    set
    name = #{name},
    pwd = #{pwd}
    where id = #{id};
</update>
~~~

#### (5),delete

~~~xml
<!--删除-->
<delete id="deleteUser">
    delete from users where id = #{id}
</delete>
~~~

#### (6),模糊查询

1. java代码执行的时候，传递通配符%%

2. 在SQL中拼接通配符

   ~~~xml
   select * from users where name like "%"#{value}"%";
   ~~~

### 3，配置解析

#### (1)，核心配置文件

- mybatis-config.xml

- mybatis的配置文件包含了会影响mybatis行为的设置和属性信息

  ~~~txt
  configuration (配置)
  properties (属性)
  settings (设置)
  typeAliases (类型别名)
  typeHandlers (类型处理器)
  objectFactory (对象工厂)
  plugins (插件)
  environments (环境配置)
  environment (环境变量)
  transactionManager (事务管理器)
  datasource (数据源)
  databaseIdProvider (数据库厂商标识)
  mappers (映射器)
  ~~~

- environments (环境设置)

  在mybatis中有两种类型的事务管理器，也就是environments.environment.transactionManagerd的type值

  - JDBC -这个配置就是直接使用了JDBC的提交和回滚设置，它依赖于从数据源得到的连接来管理事务作用域。
  - MANAGED -这个配置几乎没做什么。它从来不提交或回滚一个连接，而是让容器来管理事务的整个生命周期(比如JEE应用服务器的上下文)。默认情况下它会关闭连接，然而一些容器并不希望这样。因此需要将closeConnection属性设置为false来阻止它默认的关闭行为。

- 数据源就是连接数据库的各种值

  有三种内建的数据源类型(也就是type="[UNPOOLED|POOLED|JNDI]") ;

  - UNPOOLED :这个数据源的实现只是每次被请求时打开和关闭连接。

    ~~~java
    1,driver -这是JDBC驱动的Java类的完全限定名(并不是JDBC驱动中可能包含的数据源类)。
    2,url -这是数据库的JDBC URL地址。
    3,username -登录数据库的用户名。
    4,password -登录数据库的密码。
    5,defaultTransactionIsolationLevel -默认的连接事务隔离级别。
    6,defaultNetworkTimeout - The default network timeout value in mlliseconds to wait for the database operation to complete.See the API documentation of java . sql .Connect iontsetNetworkTimeout() for details.
    ~~~

  - POOLED：这种数据源的实现利用池的概念将JDBC连接对象组织起来，避免了创建新的连接实例时所必须的初始化和认证时间，这是一种事的并发web应用快速响应请求的流行处理方式

  - JNDI：这个数据源的实现是为了能在如EJB或应用服务器这类容器中使用，容器可以集中或在外部配置数据源，然后放置一个JNDI上下文的引用。

- 属性（properties）

  我们可以通过properties属性来实现引用配置文件

  这些属性都是可外部配置且可动态替换的，既可以在典型的Java属性文件中配置，亦可通过properties元素的子元素来传递。

  properties标签必须在最前面

  `<properties resouse="db.properties"/>`

  也可以自己定义一些数据

  优先使用外部配置文件的

- 类型别名 （typeAliases）

  - 类型别名是为Java类型设置一个短的名字。
  - 仅在于用来减少类完全限定名的冗余

  ~~~xml
  <typeAliases>
  	<typeAlias type="com.liu.pojo.Users" alias="Users"/>
  </typeAliases>
  	<package name="com.liu.pojo"/>
  </typeAliases>
  ~~~

- 设置（setting）

  驼峰命名装换，日志信息等

- 其他配置

  - typeHandlers (类型处理器)
  - plugins (插件)
  - objectFactory (对象工厂)

- 映射器 (mapper)

  - ~~~xml
    <!--使用相对类路径的资源引用-->
    <mappers>
        <mapper resource="mapper/UserMapper.xml"/>
    </mappers>
    ~~~

  - ~~~xml
    <!--使用映射器接口实现类完全限定类名-->
    <mappers>
        <mapper class="com.liu.mapper.UsersMapper"/>
    </mappers>
    注意接口和配置文件必须同名且在同一个包下
    ~~~

  - ~~~XML
    <!--使用包扫描-->
    <mappers>
        <package name="com.liu.mapper"/>
    </mappers>
    ~~~

### 4，生命周期

![](https://img-blog.csdn.net/20180728153552366?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dmeTI2OTU3NjY3NTc=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

![](https://images2018.cnblogs.com/blog/1096834/201805/1096834-20180511021503308-1355945929.png)

生命周期，和作用域，是至关重要的，因为错误的使用会导致非常严重的并发问题。

**SqISessionFactoryBuilder:**

- 一旦创建了Sq|SessionFactory,就不再需要它了
- 局部变量

**SqISessionFactory:**

- 可以想象为:数据库连接池
- SqlSessionFactory 一旦被创建就应该在应用的运行期间一直存在,没有任何理由丢弃它或重新创建另一个实例。
- SqlSessionFactory 的最佳作用域是应用作用域

**SqlSession**

- 连接到连接池的一个请求
- SqlSession的实例不是线程安全的，因此是不能被共享的，所以她的最佳作用域是请求或方法作用域
- 用完之后需要赶紧关闭，否则资源会被占用

### 5，解决属性名和字段名不一致的问题

如果两者不一致会解析错误，但是不会报异常，只会使传入的值为null

~~~java
public class User {
    private int id;
    private string name;
    private string password;
}
~~~

~~~xml
<select id="getUserById" resultType="com.liu.pojo.Users">
    select id,name,pwd from users where id = #{id}
</select>
~~~

这样的话pwd就是null

解决办法：

- 起别名 as

  ~~~xml
  <select id="getUserById" resultType="com.liu.pojo.Users">
      select id,name,pwd as password from users where id = #{id}
  </select>
  ~~~

- resultMap

  结果集映射

  ~~~xml
  <resultMap id="UserMap" type="com.liu.pojo.Users">
      <!--column是数据库中的字段，property是实体类中的属性-->
      <result column="id" property="id" />
      <result column="name" property="name"/>
      <result column="pwd" property="pwd"/>
  </resultMap>
  ~~~

  - resultMap元素是mybatis中最重要最强大的元素
  - ResultMap的设计思想是，对于简单的语句根本不需要配置显式的结果映射，而对于复杂一点的语句只需要描述它们的关系就行了。
  - ResultMap最优秀的地方在于,虽然你已经对它相当了解了，但是根本就不需要显式地用到他们。

## 三，日志

### 1，日志工厂

如果一个数据库操作，出现了异常，我们需要查错

logImpl 指定mybatis所用日志的具体实现，未指定时将自动查找

在mybatis中具体使用那个日志实现，在设置中设定

**STDOUT_LOGGING标准日志输出**

~~~xml
<!--标准的日志-->
<settings>
    <setting name="logImpl" value="STDOUT_LOGGING"/>
</settings>
~~~

LOG4J

首先需要导包

~~~xml
<dependencies>
    <dependency>
        <groupId>log4j</groupId>
        <artifactId>log4j</artifactId>
        <version>1.2.17</version>
    </dependency>
</dependencies>
~~~

~~~properties
#log4j基本配置
log4j.rootLogger=DEBUG,console,file
#设置输出控制台信息
log4j.appender.console=org.apache.log4j.ConsoleAppender
log4j.appender.console.Target = System.out
log4j.appender.console.Threshold=DEBUG     #控制台只会打印INFO级别及以上的日志信息
log4j.appender.console.layout = org.apache.log4j.PatternLayout
log4j.appender.console.layout.ConversionPattern=%c-%m%n
#设置输出文件信息
log4j.appender.file = org.apache.log4j.RollingFileAppender
log4j.appender.file.File=log/mybatis.log     #日志文件路径，在应用下的log/mybatis.log文件中
log4j.appender.file.Append=true   #追加
log4j.appender.file.MaxFileSize=100mb    #达到100m后产生一个新文件
log4j.appender.file.Threshold=ERROR      #只会写ERROR级别及以上的日志信息
log4j.appender.file.layout=org.apache.log4j.PatternLayout     #布局器
log4j.appender.file.layout.ConversionPattern=%c-%m%n   #布局器格式
~~~

~~~xml
<settings>
    <setting name="logImpl" value="LOG4J"/>
</settings>
~~~

## 四，分页

为什么要分页？

- 减少数据的处理量

使用**limit**分页

~~~sql
select * from users limit 3
~~~

使用Mybatis实现分页，核心SQL

1. 接口

   ~~~java
   List<Users> getUserByLimit(HashMap<String, Integer> map);
   ~~~

2. Mapper.xml

   ~~~xml
   <select id="getUserByLimit" resultMap="userMap" parameterType="map">
       select
       *
       from users
       limit #{startIndex}, #{pageSize};
   </select>
   ~~~

3. 测试

   ~~~java
   @Test
   public void getUserByLimit(){
       SqlSession sqlSession = MybaitsUtils.getSqlSession();
       UsersMapper mapper = sqlSession.getMapper(UsersMapper.class);
   
       HashMap<String, Integer> map = new HashMap<>();
       map.put("startIndex", 1);
       map.put("pageSize", 5);
   
       List<Users> list = mapper.getUserByLimit(map);
   
       list.forEach(users -> {
           System.out.println(users.toString());
       });
   
       sqlSession.commit();
       sqlSession.close();
   }
   ~~~

## 五，注解开发

### 1，面向接口编程

- 大家之前都学过面向对象编程，也学习过接口，但在真正的开发中,很多时候我们会选择面向接口编程
- **根本原因: ==解耦== ,可拓展,提高复用,分层开发中,上层不用管具体的实现,大家都遵守共同的标准,使得开发变得容易。规范性更好**
- 在一个面向对象的系统中，系统的各种功能是由许许多多的不同对象协作完成的。在这种情况下，各个对象内部是如何实现自己的,对系统设计人员来讲就不那么重要了;
- 而各个对象之间的协作关系则成为系统设计的关键。小到不同类之间的通信，大到各模块之间的交互，在系统设计之初都是要着重考虑的，这也是系统设计的主要工作内容。面向接口编程就是指按照这种思想来编程。

##### 关于接口的理解

- 接口从更深层次的理解，应是定义(规范，约束)与实现(名实分离的原则)的分离。
- 接口的本身反映了系统设计人员对系统的抽象理解。
- 接口应有两类:
  - 第一类是对一个个体的抽象，它可对应为一个抽象体(abstract lass);
  - 第二类是对一个个体某一方面的抽象，即形成-一个抽象面(interface) ;
- 一个体有可能有多个抽象面。抽象体与抽象面是有区别的。

##### 三个面向区别

- 面向对象是指,我们考虑问题时，以对象为单位，考虑它的属性及方法.
- 面向过程是指，我们考虑问题时，以-一个具体的流程(事务过程)为单位，考虑它的实现.
- 接口设计与非接口设计是针对复用技术而言的，与面向对象(过程)不是一个问题.更多的体现就是对系统整体的架构.

## 六，关联，一对多和多对多

### 测试环境搭建

1. 数据库

   ~~~sql
   SET FOREIGN_KEY_CHECKS=0;
   
   DROP TABLE IF EXISTS `student`;
   CREATE TABLE `student` (
     `id` int(10) NOT NULL AUTO_INCREMENT,
     `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
     `tid` int(10) DEFAULT NULL,
     PRIMARY KEY (`id`),
     KEY `tid` (`tid`),
     CONSTRAINT `tid` FOREIGN KEY (`tid`) REFERENCES `teacher` (`id`)
   ) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
   
   INSERT INTO `student` VALUES ('1', '小红', '1');
   INSERT INTO `student` VALUES ('2', '小黑', '1');
   INSERT INTO `student` VALUES ('3', '小蓝', '1');
   INSERT INTO `student` VALUES ('4', '小绿', '1');
   INSERT INTO `student` VALUES ('5', '小白', '1');
   INSERT INTO `student` VALUES ('6', '小橙', '1');
   INSERT INTO `student` VALUES ('7', '小黄', '1');
   INSERT INTO `student` VALUES ('8', '小青', '1');
   INSERT INTO `student` VALUES ('9', '小紫', '1');
   INSERT INTO `student` VALUES ('10', '小棕', '1');
   INSERT INTO `student` VALUES ('11', '小灰', '1');
   
   DROP TABLE IF EXISTS `teacher`;
   CREATE TABLE `teacher` (
     `id` int(10) NOT NULL AUTO_INCREMENT,
     `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
     PRIMARY KEY (`id`)
   ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
   
   INSERT INTO `teacher` VALUES ('1', '老师');
   ~~~

2. 导入lombok

   ~~~xml
   <dependencies>
       <dependency>
           <groupId>org.projectlombok</groupId>
           <artifactId>lombok</artifactId>
           <version>1.18.12</version>
       </dependency>
   </dependencies>
   ~~~

3. 新建实体类Teacher，Student

   ~~~java
   @Data
   @AllArgsConstructor
   @NoArgsConstructor
   public class Student {
       private int id;
       private String name;
       private Teacher teacher;
   }
   
   @Data
   @AllArgsConstructor
   @NoArgsConstructor
   public class Teacher {
       private int id;
       private String name;
   }
   
   ~~~

4. 建立Mapper接口

   ~~~java
   public interface TeacherMapper {
       List<Teacher> getTeacherList();
   }
   ~~~

5. 建立Mapper.xml文件

   ~~~xml
   <?xml version="1.0" encoding="UTF-8"?>
   <!DOCTYPE mapper
           PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
           "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
   
   <mapper namespace="com.liu.mapper.TeacherMapper">
       <resultMap id="resultMap" type="com.liu.pojo.Teacher">
           <result column="id" property="id"/>
           <result column="name" property="name"/>
       </resultMap>
       <select id="getTeacherList" resultType="com.liu.pojo.Teacher">
           select * from teacher;
       </select>
   
   </mapper>
   ~~~

6. 在核心文件中绑定注册mapper接口或者文件

   ~~~xml
   <?xml version="1.0" encoding="UTF-8"?>
   <!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
   <configuration>
   
       <!-- 别名 -->
       <!--<typeAliases>
           <package name="pojo"/>
       </typeAliases>-->
       <settings>
           <setting name="logImpl" value="STDOUT_LOGGING"/>
       </settings>
       <!-- 数据库环境 -->
       <environments default="development">
           <environment id="development">
               <transactionManager type="JDBC"/>
               <dataSource type="POOLED">
                   <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                   <property name="url" value="jdbc:mysql://localhost:3306/mybatis?characterEncoding=UTF-8&amp;useUnicode=true&amp;serverTimezone=UTC"/>
                   <property name="username" value="root"/>
                   <property name="password" value="123456"/>
               </dataSource>
           </environment>
       </environments>
       <!-- 映射文件 -->
       <mappers>
           <mapper resource="mapper/TeacherMapper.xml"/>
           <mapper resource="mapper/StudentMapper.xml"/>
       </mappers>
   </configuration>
   ~~~

7. 测试

   ~~~java
   public class MyTest {
       @Test
       public void getTeacherList(){
           SqlSession sqlSession = MybatisUtils.getSqlSession();
           TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
   
           List<Teacher> users = mapper.getTeacherList();
   
           users.forEach(users1 -> {
               System.out.println(users1.toString());
           });
   
           sqlSession.commit();
           sqlSession.close();
       }
   }
   ~~~

有外键关系的时候可以嵌套处理

~~~xml
StudentMapper.xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.liu.mapper.StudentMapper">
    <resultMap id="resultTeacgerAndStudentMap" type="com.liu.pojo.Student">
        <result column="id" property="id"/>
        <result column="name" property="name"/>
        <!--复杂的属性需要单独处理：
            对象：association
            集合: collection
        -->
        <association property="teacher" column="tid" javaType="com.liu.pojo.Teacher" select="com.liu.mapper.TeacherMapper.getTeacherById"/>
    </resultMap>
    <select id="getStudentAndTeacherList" resultMap="resultTeacgerAndStudentMap">
        select * from student;
    </select>

</mapper>
~~~

~~~xml
TeacherMapper.xml

<select id="getTeacherById" resultType="com.liu.pojo.Teacher">
    select * from teacher where id = #{id};
</select>
~~~

嵌套代码：

~~~xml
<resultMap id="resultTeacgerAndStudentAllMap" type="com.liu.pojo.Student">
    <result column="id" property="id"/>
    <result column="name" property="name"/>
    <association property="teacher" javaType="com.liu.pojo.Teacher">
        <result column="id" property="id"/>
        <result column="name" property="name"/>
    </association>
</resultMap>
~~~

## 七，动态sql

> 什么是动态SQL:

==动态SQL就是指根据不同的条件生成不同的SQL语句==

搭建实验环境

~~~sql
SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` varchar(100) NOT NULL,
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `author` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `views` int(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
~~~

创建一个基础工程

1. 导入maven依赖

   ```xml
   <dependencies>
       <dependency>
           <groupId>org.projectlombok</groupId> <artifactId>lombok</artifactId>
           <version>1.18.12</version>
       </dependency>
   </dependencies>
   ```

2. 编写配置文件

3. 编写实体类

   ~~~java
   @Data
   @AllArgsConstructor
   @NoArgsConstructor
   public class Blog {
       private int id;
       private String title;
       private String author;
       private Date createTime;
       private int views;
   }
   ~~~

4. 编写实体类对应的mapper接口和mapper.xml

5. 编写代码插入数据

   ~~~java
   public class MyTest {
       @Test
       public void addBlog(){
           SqlSession sqlSession = MybatisUtils.getSqlSession();
           BolgMapper mapper = sqlSession.getMapper(BolgMapper.class);
   
           String title = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
           String author = "abcdefghijklmnopqrstuvwxyz";
           String views = "123456789";
           char[] title2 = title.toCharArray();
           char[] author2 = author.toCharArray();
           char[] views2 = views.toCharArray();
           Random random = new Random();
           for (int i = 0; i < 500; i++) {
               String title1 = "";
               String author1 = "";
               String views1 = "";
               for( int j = 0; j < 6; j ++) {
                   char a = title2[random.nextInt(title2.length)];
                   title1 = title1 + a;
               }
               for( int j = 0; j < 4; j ++) {
                   char a = author2[random.nextInt(author2.length)];
                   author1 = author1 + a;
               }
               for( int j = 0; j < 3; j ++) {
                   char a = views2[random.nextInt(views2.length)];
                   views1 = views1 + a;
               }
               Blog blog = new Blog(IdUtils.getId(), title1, author1, new Date(), Integer.parseInt(views1));
               mapper.addBlog(blog);
           }
   
           sqlSession.commit();
           sqlSession.close();
       }
   ~~~

> 有关驼峰命名和数据库各个单词之间的"_"的替换：

`mapUnderscoreToCamelCase`:是否开启自动驼峰命名规则映射，即从经典数据库列名A_COLUMN到java属性名aColumn的类似映射

~~~xml
<settings>
    <setting name="logImpl" value="STDOUT_LOGGING"/>
    <setting name="mapUnderscoreToCamelCase" value="true"/>
</settings>
~~~

### 2，动态SQL常用标签

#### (1)，if, where

> 动态SQL通常要做的事情是根据条件包含where子句的一部分,通过where标把第一个字句的and清除，如果有的话例子：

~~~xml
<select    id="findActiveBlogwithTitlelike"
resultType="B1og">
SELECT * FROM BLOG
WHERE state = "ACTIVE"
<if test="title = null">
AND title like #{tit1e}
</if>
</select>
~~~

~~~xml
<select id="getListByPlan" parameterType="map" resultType="com.liu.pojo.Blog">
    select * from blog where
    <where>
        <if test="title != null">
            and title like "%"#{title}"%"
        </if>
        <if test="author != null">
            and author like "%"#{author}"%"
        </if>
    </where>
</select>
~~~

测试

~~~java
@Test
public void getListByPlan(){
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    BolgMapper mapper = sqlSession.getMapper(BolgMapper.class);

    HashMap map = new HashMap();
    map.put("title","L");
    List<Blog> blogs = mapper.getListByPlan(map);

    blogs.forEach(blog -> {
        System.out.println(blog.toString());
    });

    sqlSession.commit();
    sqlSession.close();
}
~~~

#### (2)，set

set元素会前置set关键字，同时会删除无关的逗号

~~~xml
<update id="updateAuthorIfNecessary">
update Author
<set>
<if test="username != null">
    username=#{username},
</if>
<if test="password != null" >
    password=#{(password},
</if>
<if test="email != null">
    email={email}, 
</if>
<if test="bio != null">
    bio=#{bio}
</if>
</set>
where id=#{id}
</update>

~~~

#### (3)，trim

可以通过trim来自定义where元素标签：

~~~xml
<trim prefix="WHERE" prefixOverrides="AND |OR">
    ...
</trim>
~~~

prefixOverrides属性会忽略通过管道分割的文本序列(注意其中的空格是必须的)，他的作用是移除所有指定在prefixOverrides属性中的内容，并插入prefix指定的内容。

同理set：

~~~xml
<trim prefix="SET" suffixOverrides="AND |OR">
    ...
</trim>
~~~

suffixOverrides：后缀

#### (4)，choose，when， otherwise

有时我们不想应用到所有的条件语句，而只想从中选择其一项。 针对这种情况，MyBatis提供了choose元素，它有点像Java中的switch语句。

~~~xml
<select id="findActiveBloglike resultType="Blog">
	SELECT * FROM BLOG WHERE state = 'ACTIVE'
<choose>
	<when test="title != null">
    	AND title like #{title}
    </when>
    <when test="author != null and author.name != null">
    	AND author_name like #{author.name}
    </when>
    <otherwise>
    	AND featured = 1
   	</otherwise>
</choose>
</select>
~~~

#### (5)，sql

有些时候，我们会把一公共的代码片段收取出来方便复用

~~~xml
<sql id="test">
    select *
</sql>
~~~

在后面可以通过test引用：

~~~xml
<include refid="test"/> 
~~~

#### (6)，Foreach

动态SQL的另外一个常用的操作需求是对一个集合进行遍历，通常是在构建IN条件语句的时候。

foreach元素功能非常强大，他允许你指定一个集合，声明可以在元素体内使用的集合项(item)和索引(index)，他也允许你指定开头语结尾的字符串以及在迭代结果之间放置分隔符，这个元素是很智能的，因此他不会偶然地附件多余的分隔符

==动态SQL就是在拼接SQL==

## 八，mybatis缓存

> 什么是缓存？

- 存在内存中的临时数据
- 将用户经常查询的数据放在缓存中，用户去查询数据时就不从磁盘上查询，从缓存中查询，从而提高查询效率，解决了高并发系统的性能问题

> 为什么使用缓存？

- 减少和数据库的交互次数，减少系统开销，提高系统效率

> 什么样的数据能使用缓存

- 经常查询并且不经常改变的数据

### 1，mybatis缓存

- mybatis缓存包含一个强大的查询缓存特性，他何以非常方便的定制河配置缓存，缓存可以极大的提升查询效率
- mybatis系统中默认定义了两级缓存：一级缓存和二级缓存
  - 默认情况下，只有一集缓存开启，(SqlSession级别缓存，也成为本地缓存)
  - 二级缓存需要手动开启和配置，他是基于namespace级别的缓存
  - 为了提高扩展性，Mybatis定义了缓存接口Cache，我们可以通过实现Cache接口来自定义二级缓存

### 2，一级缓存

- 一级缓存也叫本地缓存
  - 与数据库同一次会话期间查询到的数据会放在本地缓存中
  - 以后如果需要获取相同的数据，直接从缓存中拿，没必要再去查询数据库

##### 测试要点：

- 必须开启日志。
- 把同一个查询放在一个是SQLSession会话中

> 其他

- 映射语句文件中的所有select 语句的结果将会被缓存。
- 映射语句文件中的所有insert，update 和delete语句会刷新缓存。
- 缓存会使用最近最少使用算法(LRU, Least Recently Used)算法来清除不需要的缓存。
  缓存不会定时进行刷新(也就是说，没有剧新间隔)。
- 缓存会保存列表或对象(无论查询方法返回哪种)的1024个引用。
- 缓存会被视为读写缓存,这意味着获取到的对象并不是共享的，可以安全地被调用者修改,而不干扰其他调用者或线程所做的潜在修改。

### 3，二级缓存

- 二级缓存也叫全局缓存，一级缓存作用域太低了所以诞生了二级缓存
- 基于namespace级别的缓存，一个命名空间，对应一个二级缓存
- 工作机制
  - 一个会话查询一条数据，这个数据就会被放在当前会话的一级缓存中
  - 如果当前会话关闭了，这个会话对应的一级缓存就没了;但是我们想要的是，会话关闭了，一级缓存中的数据被保存到二级缓存中;
  - 新的会话查询信息，就可以从二级缓存中获取内容;
  - 不同的mapper查出的数据会放在自己对应的缓存(map) 中;

##### 开启二级缓存

首先开启二级缓存

`cacheEnabled`:全局地开启或关闭配置文件中的所有映射器已经配置的任何缓存

~~~xml
<settings>
    <setting name="logImpl" value="STDOUT_LOGGING"/>
    <setting name="cacheEnabled" value="true"/>
</settings>
~~~

在映射文件中加标签，即可在当前mapper中使用缓存

~~~xml
<chche />
~~~



##### 问题：

- 需要将实体类序列化

##### 结论

- 只要开启了二级缓存，在同一个Mapper下就有效
- 所有的数据都会放在一级缓存中
- 只有当会话提交或者关闭的时候才会提交到二级缓存中

### 4，缓存原理

当用户进行查询时，会先查看二级缓存，如果二级缓存有则返回，如果没有就继续查看一级缓存，如果一级缓存有就返回，如果一级缓存没有就去查询数据库，返回的结果放入一级缓存中。

### 5,自定义缓存 --Ehcache

首先导入maven依赖

修改映射文件

```xml
<chche type="org.mybatis.caches.EhcacheCache"/>
```

## 九，和Spring的整合

导入相应依赖

~~~xml
<!--mysql-->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.18</version>
</dependency>
<!--mybatis-->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.5.2</version>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.12</version>
</dependency>
<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.1.9.RELEASE</version>
</dependency>
<!--spring 操作数据库的依赖-->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>5.1.9.RELEASE</version>
</dependency>
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
    <!-- 不设置scope就是全局
            <scope>test</scope>   -->
</dependency>
<!--mybatis整合spring的依赖-->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis-spring</artifactId>
    <version>2.0.2</version>
</dependency>
~~~

编写配置文件