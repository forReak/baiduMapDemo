package com.design.helpPlatform.service.serviceImpl;

import com.design.helpPlatform.dao.UserDao;
import com.design.helpPlatform.entity.User;
import com.design.helpPlatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    @Override
    public Integer updateByUser(User user) {
        return userDao.updateByUser(user);
    }
}
