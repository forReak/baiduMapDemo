package com.design.helpPlatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 此控制器控制的跳转页面都是静态的
 */
@Controller
public class staticController {

    @RequestMapping("/rider")
    public String rider(){
        return "rider";
    }

    @RequestMapping("/sendRule")
    public String sendRule(){
        return "sendRule";
    }

    @RequestMapping("/webSiteResume")
    public String webSiteResule(){
        return "webSiteResume";
    }
}
