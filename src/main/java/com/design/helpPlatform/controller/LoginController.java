package com.design.helpPlatform.controller;

import com.design.helpPlatform.entity.ResponseMap;
import com.design.helpPlatform.service.LoginService;
import com.design.helpPlatform.service.SubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    SubmitService submitService;
    @Autowired
    LoginService loginService;

    @RequestMapping("/login")
    @ResponseBody
    public ResponseMap login(HttpSession session,String userName,String password){
        try {
            boolean login = loginService.login(userName, password,session);
            if(login){
                return new ResponseMap(true,"success","success");
            }else{
                return new ResponseMap(false,"false","用户名或密码错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMap(false,"false",e.getMessage());

        }
    }

    @RequestMapping("/login_view")
    public String loginView(){
        return "login";
    }


    @RequestMapping("/submit_view")
    public String submitView(){
        return "submit";
    }

    @RequestMapping("/submit")
    @ResponseBody
    public ResponseMap submit(String userName,String password,String phone,String nickName,String rider){
        System.out.println(userName+password+phone+nickName+rider);
        boolean flag = submitService.submit(userName, password, phone, nickName, rider);
        if(flag){
            return new ResponseMap(true,"success","success");
        }else{
            return new ResponseMap(false,"false","用户名已被占用！请更换！");
        }
    }

    @RequestMapping("/logout")
    @ResponseBody
    public ResponseMap logout(HttpSession session){
        session.removeAttribute("loginedUser");
        session.invalidate();
        return new ResponseMap(true,"success","success");
    }



}
