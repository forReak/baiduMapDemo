package com.design.helpPlatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/buy")
public class BuyController {

    @RequestMapping("/makeOrder")
    public String makeOrderView(){
        return "buy";
    }
}
