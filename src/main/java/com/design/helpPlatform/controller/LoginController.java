package com.design.helpPlatform.controller;

import com.design.helpPlatform.entity.ResponseMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @RequestMapping("/login")
    @ResponseBody
    public ResponseMap login(HttpSession session){

        session.setAttribute("loginedUser","admin");
        return new ResponseMap(true,"success","success");
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
    public ResponseMap submit(String userName,String password,String phone,String nickName){
        System.out.println(userName+password+phone+nickName);
        return new ResponseMap(true,"success","success");
    }

    @RequestMapping("/logout")
    @ResponseBody
    public ResponseMap logout(HttpSession session){
        session.removeAttribute("loginedUser");
        session.invalidate();
        return new ResponseMap(true,"success","success");
    }



}
