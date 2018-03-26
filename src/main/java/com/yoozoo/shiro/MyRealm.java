package com.yoozoo.shiro;

import com.yoozoo.domain.User;
import com.yoozoo.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by Hao on 2018/3/26.
 */
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Subject subject = SecurityUtils.getSubject();

        User authcUser = (User) subject.getPrincipal();

        List<String> perms = userMapper.findPermissionByUserId(authcUser.getId());

        if (perms !=null){
            for(String perm:perms){
                if(!StringUtils.isEmpty(perm)){
                    info.addStringPermission(perm);
                }
            }
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        User dbUser = userMapper.findByName(token.getUsername());

        if(dbUser == null){
            return null;
        }
        return new SimpleAuthenticationInfo(dbUser,dbUser.getPassword(),"");
    }
}
