package com.yoozoo.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Hao on 2018/3/26.
 */
@Configuration
public class ShiroConfig {

    /**
     * 1 创建ShiroFilterFactoryBean
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultSecurityManager manager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();

        // 关联SecurityManager
        bean.setSecurityManager(manager);

        Map<String,String> filterMap = new LinkedHashMap<>();
        //认证过滤器
        filterMap.put("/user/login","anon");
        filterMap.put("/defaultKaptcha","anon");
        filterMap.put("/product/toList","perms[product:list]");
        filterMap.put("/product/toAdd","perms[product:add]");
        filterMap.put("/product/toUpdate","perms[product:update]");
        filterMap.put("/index","userFilter");//index的请求只要使用Remember就可以访问 ("/index","user") 原来默认用user
        filterMap.put("/**","authc");

        //将自定义过滤器添加到shiro过滤器列表
        Map<String,Filter> fiters = new LinkedHashMap<>();
        fiters.put("userFilter",userFormAuthencationFilter());
        bean.setFilters(fiters);

        //添加shiro过滤器
        bean.setFilterChainDefinitionMap(filterMap);
        //添加登录请求
        bean.setLoginUrl("/toLogin");
        //添加未认证请求
        bean.setUnauthorizedUrl("/unauth");

        return bean;
    }

    /**
     * 添加自定义过滤器配置
     */
    @Bean(name="userFilter")
    public UserFormAuthencationFilter userFormAuthencationFilter(){
        return new UserFormAuthencationFilter();
    }
    /**
     * 2 SecurityManager
     */
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(MyRealm myRealm,CookieRememberMeManager rememberMeManager){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();

        // 关联myRealm
        manager.setRealm(myRealm);

        // 关联Remember
        manager.setRememberMeManager(rememberMeManager);

        return manager;
    }


    @Bean
    public MyRealm myRealm(){
        MyRealm myRealm = new MyRealm();
        return myRealm;
    }

    /**
     * 整合thymeleaf框架
     */
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }

    @Bean
    public CookieRememberMeManager cookieRememberMeManager(SimpleCookie cookie){
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCookie(cookie);
        return rememberMeManager;
    }

    /**
     * RememberMe
     */
    @Bean
    public SimpleCookie simpleCookie(){
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        return cookie;
    }
}
