package com.karkar.springboot.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.karkar.springboot.controller.request.BaseRequest;
import com.karkar.springboot.mapper.UserMapper;
import com.karkar.springboot.pojo.User;
import com.karkar.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public List<User> queryAll() {
        // 由于这是一个简单的Demo
        // 我们只进行一些简单的业务, 所以直接返回
        return mapper.queryAll();
    }

    @Override
    public User queryById(Integer id) {
        return mapper.queryById(id);
    }

    @Override
    public PageInfo<User> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize(), baseRequest.getOrderBy());
        List<User> users = mapper.queryByCondition(baseRequest);
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        return userPageInfo;
    }

    @Override
    public void addUser(User user) {
        user.setCardid(DateUtil.format(new Date(), "yyyyMMdd") + Math.abs(IdUtil.fastSimpleUUID().hashCode()));
        mapper.addUser(user);
    }

    @Override
    public void editUser(User user) {
        mapper.editUser(user);
    }

    @Override
    public void deleteUser(Integer id) {
        mapper.deleteUser(id);
    }
}
