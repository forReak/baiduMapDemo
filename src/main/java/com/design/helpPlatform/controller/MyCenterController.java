package com.design.helpPlatform.controller;

import com.design.helpPlatform.entity.User;
import com.design.helpPlatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/myCenter")
public class MyCenterController {

    @Autowired
    UserService userService;

    @RequestMapping("/myInfo")
    public String myInfo(HttpSession session, Model model){
        Integer userId = (Integer)session.getAttribute("userId");
        User user  = userService.getUserById(userId);
        model.addAttribute("user",user);
        return "myInfo";
    }
}
