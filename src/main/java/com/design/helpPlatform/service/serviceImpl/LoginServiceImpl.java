package com.design.helpPlatform.service.serviceImpl;

import com.design.helpPlatform.dao.UserDao;
import com.design.helpPlatform.entity.User;
import com.design.helpPlatform.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static com.design.helpPlatform.util.Util.getMD5ofStr;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserDao userDao;

    @Override
    public boolean login(String userName, String password, HttpSession session) {
        Map<String,String> map = new HashMap<>();
        map.put("userName",userName);
        map.put("password",getMD5ofStr(password));
        User user = userDao.getUserByUseNameAndPassword(map);
        if(user!=null){
            session.setAttribute("loginedUser",user.getNickName());

            return true;
        }else{
            return false;
        }
    }
}
