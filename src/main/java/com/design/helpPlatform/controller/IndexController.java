package com.design.helpPlatform.controller;

import com.design.helpPlatform.entity.ResponseMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping("/")
    public String index(ResponseMap responseMap, Model model){
        model.addAttribute("info",new ResponseMap(true,"hello world!","成功查询！"));
        return "index";
    }

    @RequestMapping("/search")
    @ResponseBody
    public ResponseMap ajaxDemo(){
        return new ResponseMap(true,"返回结果对象","返回成功");
    }
}
