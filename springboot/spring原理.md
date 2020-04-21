# SpringBoot

## Springboot自动配置

pox.xml

~~~xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.2.6.RELEASE</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>
~~~

这相当于一个父依赖，进入之后可以看到

~~~xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-dependencies</artifactId>
    <version>2.2.6.RELEASE</version>
    <relativePath>../../spring-boot-dependencies</relativePath>
</parent>
~~~

还有一层父依赖，继续点入

。。。

在spring-boot-dependencies中springboot导入了大量的依赖

所以springboot 的依赖在父工程中包括

~~~xml
<resources>
    <resource>
        <filtering>true</filtering>
        <directory>${basedir}/src/main/resources</directory>
        <includes>
            <include>**/application*.yml</include>
            <include>**/application*.yaml</include>
            <include>**/application*.properties</include>
        </includes>
    </resource>
    <resource>
        <directory>${basedir}/src/main/resources</directory>
        <excludes>
            <exclude>**/application*.yml</exclude>
            <exclude>**/application*.yaml</exclude>
            <exclude>**/application*.properties</exclude>
        </excludes>
    </resource>
</resources>
~~~

对配置文件的支持等

我们在写入一些SpringBoot依赖的时候不需要指定版本，就是因为有哪些版本仓库

> 启动器

- 启动器就是 spring-boot-starter，带有这种字样的依赖名
- 说白了就是Springboot 的启动场景
- 比如spring-boot-starter-web,使用之后，它就会帮我们自动导入web环境所有的依赖
- springboot会将所有的功能场景，都变成一个个的启动器。
- 如果我们要使用什么功能，只需要找到对应的启动器就可以了



> 主程序：
>
> 这是springboot程序的入口

~~~java
// @SpringBootApplication 标注这是一个springboot的应用
@SpringBootApplication
public class HelloworldApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloworldApplication.class, args);
    }
}
~~~

- 注解：

  - ~~~java
    @SpringBootConfiguration//：springboot的配置
    @Configuration//：spring配置类
    //这也是一个springboot的组件
    ~~~

  - ~~~java
    @EnableAutoConfiguration//：自动配置
        @AutoConfigurationPackage//：自动配置包
            @Import(AutoConfigurationPackages.Registrar.class)//：自动配置包注册
        @Import(AutoConfigurationImportSelector.class)//：自动配置导入选择
    ~~~

  - ~~~java
    //在类AutoConfigurationImportSelector中
    List<String> configurations = getCandidateConfigurations(annotationMetadata, attributes);
    
    //进入getCandidateConfigurations
    protected List<String> getCandidateConfigurations(AnnotationMetadata metadata, AnnotationAttributes attributes) {
        List<String> configurations = SpringFactoriesLoader.loadFactoryNames(getSpringFactoriesLoaderFactoryClass(),
        getBeanClassLoader());
        Assert.notEmpty(configurations, "No auto configuration classes found in META-INF/spring.factories. If you " + "are using a custom packaging, make sure that file is correct.");
        return configurations;
    }
    ~~~

  - ~~~java
    META-INF/spring.factories;
    //全部的自动配置
    ~~~

  - 为什么在META-INF/spring.factories里面的这么多配置没有生效，而是必须导入依赖才会生效？

    ~~~java
    @ConditionalOnXXXX
    // 核心注解，用于判断，如果这里面的条件都满足，才生效，不然无法生效
    // XXXX 有几个比如WebApplication，Class，MissinBean
    ~~~

  - ==结论：springboot所有的自动配置都是在启动的时候启动并加载spring.factories，所有的配置类都在这里面，但是不一定生效，只要判断条件生效，只要导入了对应的start，就有对应的启动器了，有了启动器，我们自动装配就会生效，然后就配置成功！==

  1. springboot在启动的时候， 从类路径下/META-INF/ spring. factories获取指定的值;
  2. 将这些自动配置的类导入容器，自动配置就会生效,帮我进行自动配置!
  3. 以前我们需要自动配置的东西，现在springboot帮我们做了!
  4. 整合javaEE, 解决方案和自动配置的东西都在spring-boot-autoconfigure-2.2.0.RELEASE.jar这个包下
  5. 它会把所有需要导入的组件，以类名的方式返回，这些组件就会被添加到容器: 
  6. 容器中也会存在XXXXAutoConfiguration的文件，就是这些类给容器中导入了这个场景需要的所有组件，并自动配置
  7. 有了自动配置类，就省去了我们手动装配的时间

> SpringApplication类主要做的事情

- 推断应用的类型时普通的项目还是一个Web项目
- 查找并加载所有可用初始化器，设置到initializers属性中
- 找出所有的应用程序监听器，设置到initializers属性中
- 推断并设置main方法的定义类，找到运行的主类

## Spring Boot 配置

### application.properties

springboot文档：

https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#common-application-properties

application.yml

~~~yml
#k: v
#对空格和缩进的要求非常高
#可以注入到java类中
#例如端口号配置
server:
  port: 8080

#普通的key-value:
name: shidacaizi

#对象
person:
  name: shidacaizi
  age: 20

#行内写法
student: {name: shidacaizi,age: 20}

#数组
porple:
  - woman
  - man
  - child
~~~

yaml可以给实体类直接赋值

1. 使用@value直接给属性

   ~~~java
   @Component
   @Data
   @AllArgsConstructor
   @NoArgsConstructor
   public class Dog {
       private Integer id;
       @Value("宸宸")
       private String name;
       @Value("3")
       private Integer age;
   }
   ~~~

   测试：

   ~~~java
   @SpringBootTest
   class ConfigApplicationTests {
   
       @Autowired
       private Dog dog;
   
       @Test
       void contextLoads() {
           System.out.println(dog);
       }
   
   }
   ~~~

2. 通过yml进行赋值

   ~~~java
   @Component
   @Data
   @AllArgsConstructor
   @NoArgsConstructor
   @ConfigurationProperties(prefix = "person")
   public class Person {
       private String name;
       private Integer age;
       private Boolean happy;
       private Date birth;
       private Map<String, Object> maps;
       private List<?> list;
       private Dog dog;
   }
   ~~~

   application.yml

   ~~~yml
   person:
     name: shidacaizi
     age: 20
     happy: false
     birth: 2020/4/17
     maps: {k1: v1,k2: v2}
     list:
       - code
       - music
       - girl
     dog:
       id: 1
       name: "宸宸"
       age: 3
   ~~~

   测试;

   ~~~java
   @SpringBootTest
   class ConfigApplicationTests {
   
       @Autowired
       private Person person;
   
       @Test
       void contextLoads() {
           System.out.println(person);
       }
   
   }
   ~~~

3. 还可以指定加载的配置文件

   test.properties

   ~~~properties
   name=shidacaizi
   ~~~

   ~~~java
   @Component
   @Data
   @AllArgsConstructor
   @NoArgsConstructor
   @PropertySource(value = "chlasspath:test.properties")
   public class Person {
       @Valie("${name}")
       private String name;
       private Integer age;
       private Boolean happy;
       private Date birth;
       private Map<String, Object> maps;
       private List<?> list;
       private Dog dog;
   }
   ~~~

松散绑定：支持驼峰命名和"-"的转换。

jsR303校验：@Validated注解，数据校验

- 如@Emali注解的属性，就必须输入一个邮箱的格式
- @Length注解的属性，大小必须在指定范围内
- @NotEmpty注解的属性，必须不为空
- @Range被注解的属性必须在合适的范围内

### 自动配置再谈

配置文件可以写什么？

它和spring.factories有很大的联系

在自动配置类中，使用@configuration注解并使用@bean注入的 配置文件的信息，在配置文件中，也就是application.yml中编写的配置就可以被springboot识别。

> 这就是自动装配的原理!

精髓:

- SpringBoot启动会加载大量的自动配置类
- 我们看我们需要的功能有没有在SpringBoot默认写好的自动配置类当中;
- 我们再来这个自动配置类中到底配置了哪些组件; (只要我们要用的组件存在在其中，我们就不需要再手动配置了)
- 给容器中自动配置类添加组件的时候，会从properties类中获取某些属性。 我们只需要在配置文件中指定这些属性的值即可;
- xxxxAutoConfigurartion:自动配置类;给容器中添加组件
- xxxProperties:封装配置文件中相关属性

可以通过debug = true 查看哪些自动配置生效了

## SpringBoot Web开发

Springboot到底帮我们配置了什么？

我们能不能进行修改？

能修改哪些东西？

能不能扩展？

- XXXXAutoConfiguration。。。向容器中自动配置组件
- XXXXProperties 自动装配类，装配配置文件中自定义的一些内容

要解决的问题：

- 导入静态资源。。。

  ~~~java
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
      if (!this.resourceProperties.isAddMappings()) {
          logger.debug("Default resource handling disabled");
          return;
      }
      Duration cachePeriod = this.resourceProperties.getCache().getPeriod();
      CacheControl cacheControl = this.resourceProperties.getCache().getCachecontrol().toHttpCacheControl();
      if (!registry.hasMappingForPattern("/webjars/**")) {
          customizeResourceHandlerRegistration(registry.addResourceHandler("/webjars/**")
                                               .addResourceLocations("classpath:/META-INF/resources/webjars/")
                                               .setCachePeriod(getSeconds(cachePeriod)).setCacheControl(cacheControl));
      }
      String staticPathPattern = this.mvcProperties.getStaticPathPattern();
      if (!registry.hasMappingForPattern(staticPathPattern)) {
          customizeResourceHandlerRegistration(registry.addResourceHandler(staticPathPattern)
                                               .addResourceLocations(getResourceLocations(this.resourceProperties.getStaticLocations()))
                                               .setCachePeriod(getSeconds(cachePeriod)).setCacheControl(cacheControl));
      }
  }
  ~~~

  - webjars：以maven的方式引入前端框架

  - 获取当前目录下的静态资源

    - ~~~java
      /**
      classpath:/META_INF/resource/
      classpath:/resources/
      classpath:/static/
      classpath:/public/
      ~~~

    - 以上五个位置都可以存放静态资源

- 首页

  首页文件index.html,可以放在

  ~~~java
  /**
  classpath:/META_INF/resource/
  classpath:/resources/
  classpath:/static/
  classpath:/public/
  ~~~

  以上五个位置。

  在项目开发中会经过一个controller跳转到首页

  springboot在2.2.x之前配置favicon.ico需要配置

  ~~~properties
  spring:
    mvc:
      favicon:
        enabled: false
  ~~~

  springboot在2.2.x之后配置favicon.ico需要在HTML文件中

  ~~~html
  <!DOCTYPE html>
  <html lang="en" xmlns:th="http://www.thymeleaf.org">
  <head>
      <meta charset="UTF-8">
      <link rel="icon" th:href="@{/public/favicon.ico}" type="image/x-icon"/>
      <title>Title</title>
  </head>
  <body>
      <h1>
          这里是首页
      </h1>
  </body>
  </html>
  ~~~

  两者都需要把favicon.ico文件放在resources目录下

- 模板引擎

  thymeleaf模板引擎

- 装配扩展SpringMVC

  ~~~java
  @Configuration
  public class MyMvcConfig implements WebMvcConfigurer {
  
      //视图跳转
      @Override
      public void addViewControllers(ViewControllerRegistry registry) {
          registry.addViewController("/").setViewName("index");
      }
  
  }
  ~~~

- 增删改查

  - 提取页面公共部分

  - ~~~java
    th:fragment="sidebar"
    //被提取的部分
    ~~~

    ~~~java
    th:insert="~{commons/bar::sidebar(active='welcome.html')}"></div>
    //导入和传值
    ~~~

  - 

- 拦截器

- 国际化

## JDBC

导入的依赖

~~~xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>2.1.2</version>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
        <exclusions>
            <exclusion>
                <groupId>org.junit.vintage</groupId>
                <artifactId>junit-vintage-engine</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid</artifactId>
        <version>1.1.12</version>
    </dependency>
    <dependency>
        <groupId>log4j</groupId>
        <artifactId>log4j</artifactId>
        <version>1.2.17</version>
    </dependency>
</dependencies>

<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
~~~

使用druid数据源

~~~yml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/mybatis?characterEncoding=UTF-8&useUnicode=true&serverTimezone=UTC
    username: root
    password: 123456
    type: com.alibaba.druid.pool.DruidDataSource

    #Springboot 是不注入这些属性的，需要自己绑定
    #druid 数据源专有配置
    initialSize: 5
    minIdle: 5
    maxActive: 20
    maxWait: 60000
    timeBetweenEvictionRunsMillis: 60000
    minEvictableIdleTimeMilis: 300000
    validationQuery: SELECT 1 FROM DUAL
    testWhlleIdle: true
    testOnBorrow: false
    testOnReturn: false
    poolPreparedStatements: true

    #配置监控统政filters,stat; 监控规i计。log4j：日志记录， wall;防sql院入
    #自如果允许时报聞javg. lang. ClassNotPoundExeption: org.opache.log4j.Priority
    #者的导人Log4j 依赖即时，Maven 地址: https://mvnrepository. com/artifact/log4j/log4j
    filters: stat,wall,1og4j
    maxPoolPreparedStatementPerConnectionSize: 20
    useGlobalDataSourceStat: true
    connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500

~~~

config.bean

~~~java
@Configuration
public class DruidController {
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    // 后台监控
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        //后台需要有人登陆
        HashMap<String, String> initParameters = new HashMap<>();
        //增加配置loginUsername和loginPassword是固定的，不可更改
        initParameters.put("loginUsername", "admin");
        initParameters.put("loginPassword", "123456");
        //允许谁可以访问
        initParameters.put("allow", "");
        //禁止谁能访问
        //        initParameters.put("shidacaizi", "127.0.0.1");
        //设置初始化参数
        bean.setInitParameters(initParameters);
        return bean;
    }

    //filter
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        //可以过滤那些请求
        Map<String, String> initParameters = new HashMap<>();
        //这些东西不进行统计
        initParameters.put("exclusions", "*.js,*.css,/druid/*");
        bean.setInitParameters(initParameters);
        return bean;
    }
}
~~~

请求`http://localhost:8080/druid/login.html`便可以进入控制台

## Mybatis

整合包

~~~xml
<dependency>   <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.1.2</version>
</dependency>
~~~

## SpringSecurity

安全框架。

在web开发中安全是第一位

功能性需求：否

shiro，SpringSecurity：这两个很像

~~~java
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //首尔所有人访问，功能页只有对应的权限可以查看
        http.authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/level1/**").hasRole("vip1")
            .antMatchers("/level2/**").hasRole("vip2")
            .antMatchers("/level3/**").hasRole("vip3");

        //没有权限会自动跳到登录界面
        //定制登录页
        http.formLogin().loginPage("/tologin")
            .usernameParameter("username")
            .passwordParameter("password")
            .loginProcessingUrl("/login");
        http.csrf().disable();
        //注销 和退出后的页面
        http.logout().logoutSuccessUrl("/");
        //记住我
        http.rememberMe().rememberMeParameter("rem");
    }

    //认证
    //密码必须使用加密后的
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
            .withUser("shidacaizi")
            .password(new BCryptPasswordEncoder().encode("123456"))
            .roles("vip2","vip3")
            .and().withUser("root")
            .password(new BCryptPasswordEncoder().encode("123456"))
            .roles("vip1","vip2","vip3");
    }
}
~~~

## shiro

~~~java
    @Configuration
    public class ShrioConfig {
        //ShrioFilterFactoryManager
        @Bean
        public ShiroFilterFactoryBean getFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
            ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
            //设置安全管理器
            bean.setSecurityManager(defaultWebSecurityManager);
            //添加生shrio内置过滤器
            /*
            *   anon：无需认证就可以访问
            *   anthc：必须认证才能访问
            *   user：必须有记住我功能才能用
            *   perms：拥有对某个资源的权限才能访问
            *   role：拥有某个角色权限才能访问
            * */
            //拦截
            Map<String, String> filterMap = new LinkedHashMap<>();
            //授权,正常情况下。没有授权会跳转到未授权页面
            filterMap.put("/add", "perms[users:add]");
            filterMap.put("/update", "perms[users:update]");
            filterMap.put("/logout", "logout");
            filterMap.put("/index","authc");
            filterMap.put("/","authc");
            bean.setFilterChainDefinitionMap(filterMap);
            bean.setLoginUrl("/tologin");
            bean.setUnauthorizedUrl("/unauth");
            return bean;
        }
        //defaulWebSecurityManager
        @Bean(name="defaultWebSecurityManager")
        public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
            DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
            defaultWebSecurityManager.setRealm(userRealm);
            return defaultWebSecurityManager;
        }

        //创建realm对象，需要自定义类
        @Bean
        public UserRealm userRealm(){
            return new UserRealm();
        }

        //整合shiroDialect 用来整合 shiro 和 thymeleof
        @Bean
        public ShiroDialect getShiroDialect(){
            return new ShiroDialect();
        }
    }
~~~

~~~java
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UsersService usersService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=》授权doGetAuthorizationInfo");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //        info.addStringPermission("users:add");

        //拿到当前登录的用户
        Subject subject = SecurityUtils.getSubject();
        Users users = (Users) subject.getPrincipal();

        //设置当前用户的权限
        info.addStringPermission(users.getPerms());

        return info;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了=》认证doGetAuthenticationInfo");
        //用户名 密码，数据中取出来
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        Users users = usersService.getUsersByName(token.getUsername());
        if (users == null){
            return null;
        }
        //密码认证
        return new SimpleAuthenticationInfo(users,users.getPwd(),"");
    }
}
~~~

## Swagger

前后端分离

- 后端：后端控制层，服务层，数据访问层
- 前端：前端控制层，视图层

### springboot集成swagger

~~~xml
<dependencies>
    <!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui -->
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger-ui</artifactId>
        <version>2.9.2</version>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger2 -->
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger2</artifactId>
        <version>2.9.2</version>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
        <exclusions>
            <exclusion>
                <groupId>org.junit.vintage</groupId>
                <artifactId>junit-vintage-engine</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
</dependencies>

<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
~~~

~~~java
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    //配置了swagger的Docket的bean实例
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo());
    }

    //配置Swagger信息 = apiInfo
    private ApiInfo apiInfo(){
        //作者信息
        Contact contact = new Contact("shidacaizi", "http://shidacaizi.cn/", "2335715300@qq.com");
        return new ApiInfo(
            "SwaggerAPI文档",
            "志愿带走世界的光明",
            "1.0",
            "http://localhost:8080/",
            contact,
            "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0",
            new ArrayList()
        );
    }
}
~~~

启动类上加@EnableSwagger2

## 任务

### 异步任务

~~~java
@EnableAsync  // 开启异步注解功能
~~~

### 邮件任务

### 定时执行任务

~~~java
TaskScheduler; //任务调度者
TaskExecutor; //任务执行者

@EnableScheduler; //开启定时任务功能的注解
@Scheduler //表示什么时候执行

//cron表达式
~~~

## 分布式Dubbo+Zookeeper+SpringBoot