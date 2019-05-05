package com.design.helpPlatform.service.serviceImpl;

import com.design.helpPlatform.dao.UserDao;
import com.design.helpPlatform.entity.User;
import com.design.helpPlatform.service.SubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.design.helpPlatform.util.Util.getMD5ofStr;

@Service
public class SubmitServiceImpl implements SubmitService {

    @Autowired
    UserDao userDao;

    @Override
    public boolean submit(String userName, String password, String phone, String nickName, String rider) {
        //根据用户名查找用户信息
        User user = userDao.getUserByUerName(userName);
        if(user==null){
            //进行注册
            User submitUser = new User();
            submitUser.setUserName(userName);
            submitUser.setPassword(getMD5ofStr(password));
            submitUser.setPhoneNum(phone);
            submitUser.setNickName(nickName);
            submitUser.setRider(Integer.valueOf(rider));
            int i = userDao.insertUserByUserInfo(submitUser);
            return true;
        }else{
            //用户名被占用
            return false;
        }
    }
}
