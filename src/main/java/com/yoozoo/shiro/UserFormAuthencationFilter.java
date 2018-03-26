package com.yoozoo.shiro;


import com.yoozoo.domain.User;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by Hao on 2018/3/26.
 */
public class UserFormAuthencationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response,Object mapperValue){

        Subject subject =getSubject(request,response);
        //如果isAuthenticated 为false，证明不是登录过的，同时isRemember 为true
        //证明是没登录过 直接通过isRememberMe进来的

        if(!subject.isAuthenticated()&& subject.isRemembered()){
            Session session =subject.getSession(true);

            if(session.getAttribute("username")==null){
                User dbuser = (User) subject.getPrincipal();
                session.setAttribute("username",dbuser.getUsername());
            }
        }

        return subject.isAuthenticated() || subject.isRemembered();
    }

}
