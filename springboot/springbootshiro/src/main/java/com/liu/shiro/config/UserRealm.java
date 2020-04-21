package com.liu.shiro.config;

import com.liu.shiro.pojo.Users;
import com.liu.shiro.service.UsersService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author shidacaizi
 * @date 2020/4/20 9:54
 */
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
