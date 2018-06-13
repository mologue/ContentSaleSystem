package com.sangmo.controller;

import com.sangmo.domain.User;
import com.sangmo.service.UserService;
import com.sangmo.util.UserSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/api/login")
    @ResponseBody
    public ModelMap doLogin(@RequestParam("userName") String userName, @RequestParam("password") String password,
                            ModelMap modelMap, HttpServletResponse response){
        if(userService.userExists(userName,password)){
            modelMap.addAttribute("code",200);
            modelMap.addAttribute("message","login success");
            modelMap.addAttribute("result",true);
            Cookie userNameCookie = new Cookie("userName",userName);
            Cookie userTypeCookie = new Cookie("userType",""+userService.getUserType(userName));
            userNameCookie.setPath("/");
            userTypeCookie.setPath("/");
            userNameCookie.setMaxAge(60*60);
            userTypeCookie.setMaxAge(60*60);
            response.addCookie(userNameCookie);
            response.addCookie(userTypeCookie);
        }else{
            modelMap.addAttribute("code",401);
            modelMap.addAttribute("message","账户或密码错误");
            modelMap.addAttribute("result",false);
        }
        return modelMap;

    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletResponse response,HttpServletRequest request){
        Cookie userNameCookie = UserSessionUtil.getUserCookie(request).get("userName");
        Cookie userTypeCookie = UserSessionUtil.getUserCookie(request).get("userType");
        userNameCookie.setMaxAge(0);
        userNameCookie.setPath("/");
        userTypeCookie.setMaxAge(0);
        userTypeCookie.setPath("/");
        response.addCookie(userNameCookie);
        response.addCookie(userTypeCookie);
        return "redirect:/";
    }


}
