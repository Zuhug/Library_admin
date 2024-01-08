package com.karkar.springboot.service;

import com.github.pagehelper.PageInfo;
import com.karkar.springboot.controller.request.BaseRequest;
import com.karkar.springboot.pojo.User;

import java.util.List;

public interface UserService {
    List<User> queryAll();
    User queryById(Integer id);
    PageInfo<User> page(BaseRequest baseRequest);
    void addUser(User user);
    void editUser(User user);
    void deleteUser(Integer id);
}
