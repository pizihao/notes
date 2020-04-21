package com.liu.jdbc.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shidacaizi
 * @date 2020/4/19 10:53
 */
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
