package com.karkar.springboot.service;

import com.github.pagehelper.PageInfo;
import com.karkar.springboot.controller.dto.LoginDTO;
import com.karkar.springboot.controller.request.BaseRequest;
import com.karkar.springboot.controller.request.LoginRequest;
import com.karkar.springboot.controller.request.PasswordRequest;
import com.karkar.springboot.pojo.Admin;

import java.util.List;

public interface AdminService {
    List<Admin> queryAll();
    Admin queryById(Integer id);
    PageInfo<Admin> page(BaseRequest baseRequest);
    void addAdmin(Admin admin);
    void editAdmin(Admin admin);
    void deleteAdmin(Integer id);
    LoginDTO login(LoginRequest loginRequest);
    void changePassword(PasswordRequest request);
}
