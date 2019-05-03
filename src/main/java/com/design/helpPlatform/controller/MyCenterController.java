package com.design.helpPlatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myCenter")
public class MyCenterController {


    @RequestMapping("/myInfo")
    public String myInfo(){
        return "myInfo";
    }
}
