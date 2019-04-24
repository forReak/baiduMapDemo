package com.design.helpPlatform.controller;

import com.design.helpPlatform.entity.ResponseMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @RequestMapping("/login")
    @ResponseBody
    public ResponseMap login(HttpSession session){
        session.setAttribute("loginedUser","admin");
        return new ResponseMap(true,"success","success");
    }

    @RequestMapping("/myCenter")
    public String myCenter(){
        return "myCenter";
    }
}
