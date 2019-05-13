package com.design.helpPlatform.service;

import com.design.helpPlatform.entity.User;

public interface UserService {

    User getUserById(Integer id);

    Integer updateByUser(User user);
}
