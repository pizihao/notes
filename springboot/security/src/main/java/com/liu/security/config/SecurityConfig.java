package com.liu.security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author shidacaizi
 * @date 2020/4/19 16:49
 */
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
