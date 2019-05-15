package com.design.helpPlatform.service.serviceImpl;

import com.design.helpPlatform.dao.UserDao;
import com.design.helpPlatform.entity.User;
import com.design.helpPlatform.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static com.design.helpPlatform.util.Util.getMD5ofStr;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserDao userDao;

    /**
     * 用户登陆
     * @param userName 用户名
     * @param password 密码
     * @param session 缓存
     * @return
     */
    @Override
    public boolean login(String userName, String password, HttpSession session) {
        Map<String,String> map = new HashMap<>();
        map.put("userName",userName);
        map.put("password",getMD5ofStr(password));
        //登陆 与数据库交互
        User user = userDao.getUserByUseNameAndPassword(map);
        if(user!=null){
            //如果存在，那么将用户信息放入session中
            session.setAttribute("loginedUser",user.getNickName());
            session.setAttribute("userId",user.getId());
            session.setAttribute("rider",user.getRider());
            return true;
        }else{
            //如果无法从数据库中查询到，返回false
            return false;
        }
    }
}
