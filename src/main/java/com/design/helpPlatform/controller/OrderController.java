package com.design.helpPlatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myOrder")
public class OrderController {

    @RequestMapping("/list")
    public String orderList(){
        return "myOrder";
    }

}
