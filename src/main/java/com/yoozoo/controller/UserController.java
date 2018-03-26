package com.yoozoo.controller;

import com.yoozoo.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Hao on 2018/3/26.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/login")
    public String login(User user,String rememberMe, String code,HttpServletRequest request,Model model){

        //判断验证码是否正确
        if(!StringUtils.isEmpty(code)){
            String verifyCode = (String) request.getSession().getAttribute("verifyCode");
            if(!code.equals(verifyCode)){
                model.addAttribute("msg","验证码输入有误");
                return "login";
            }
        }


        Subject subject = SecurityUtils.getSubject();
        //1234 ef83d5a0a7e52c2bab29db9d4026ae4f
        Md5Hash md5Hash = new Md5Hash(user.getPassword(),user.getUsername(),2);
        //AuthenticationToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),md5Hash.toString());

        //实现rememberMe功能
        if(rememberMe !=null && rememberMe.equals("1")){
            token.setRememberMe(true);
        }

        try {
            subject.login(token);

            User dbUser = (User) subject.getPrincipal();

            request.getSession().setAttribute("username",dbUser.getUsername());

            return "redirect:/index";

        } catch (UnknownAccountException e) {
            model.addAttribute("msg","用户不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "login";
        }

    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject =SecurityUtils.getSubject();
        subject.logout();

        return "redirect:/toLogin";
    }

}
