package com.design.helpPlatform.controller;

import com.design.helpPlatform.entity.ResponseMap;
import com.design.helpPlatform.entity.User;
import com.design.helpPlatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 更新用户信息
     * @param user
     * @param session
     * @return
     */
    @RequestMapping("/updateUserInfo")
    @ResponseBody
    public ResponseMap updateUserInfo(User user, HttpSession session){
        try {
            Integer userId = (Integer)session.getAttribute("userId");
            user.setId(userId);
            Integer f = userService.updateByUser(user);
            return new ResponseMap(true,"","查询成功！");

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMap(true,"error",e.getMessage());

        }

    }
}
