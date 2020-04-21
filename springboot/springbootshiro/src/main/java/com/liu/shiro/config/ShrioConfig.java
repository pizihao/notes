package com.liu.shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author shidacaizi
 * @date 2020/4/20 9:53
 */
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
