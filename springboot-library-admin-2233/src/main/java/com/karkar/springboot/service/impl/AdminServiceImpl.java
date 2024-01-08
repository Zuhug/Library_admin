package com.karkar.springboot.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.karkar.springboot.controller.dto.LoginDTO;
import com.karkar.springboot.controller.request.BaseRequest;
import com.karkar.springboot.controller.request.LoginRequest;
import com.karkar.springboot.controller.request.PasswordRequest;
import com.karkar.springboot.exception.ServiceException;
import com.karkar.springboot.mapper.AdminMapper;
import com.karkar.springboot.pojo.Admin;
import com.karkar.springboot.service.AdminService;
import com.karkar.springboot.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper mapper;

    private static final String PASS_SALT = "Karkaro.O";

    @Override
    public List<Admin> queryAll() {
        return mapper.queryAll();
    }

    @Override
    public Admin queryById(Integer id) {
        Admin admin = Optional
                .ofNullable(mapper.queryById(id))
                .orElseThrow(() -> new ServiceException("用户不存在！"));
        return admin;
    }

    @Override
    public PageInfo<Admin> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize(), baseRequest.getOrderBy());
        List<Admin> admins = mapper.queryByCondition(baseRequest);
        PageInfo<Admin> adminPageInfo = new PageInfo<>(admins);
        return adminPageInfo;
    }

    @Override
    public void addAdmin(Admin admin) {
        Optional.ofNullable(admin.getUsername()).orElseThrow(() -> new ServiceException("请输入用户名"));
        int res = mapper.uniqueCheck(admin.getUsername());
        if (res >= 1) throw new ServiceException("用户名重复");
        Optional.ofNullable(admin.getPassword()).orElseThrow(() -> new ServiceException("请输入密码"));
        // 密码加密, 加盐
        admin.setPassword(securePass(admin.getPassword()));
        mapper.addAdmin(admin);
    }

    @Override
    public void editAdmin(Admin admin) {
        mapper.editAdmin(admin);
    }

    @Override
    public void deleteAdmin(Integer id) {
        mapper.deleteAdmin(id);
    }

    @Override
    public LoginDTO login(LoginRequest loginRequest) {
        Optional.ofNullable(loginRequest.getPassword()).orElseThrow(() -> new ServiceException("请输入密码"));
//        loginRequest.setPassword(securePass(loginRequest.getPassword()));

        Admin admin = Optional
                .ofNullable(mapper.login(loginRequest))
                .orElseThrow(() -> new ServiceException("用户名或密码错误"));

        if (!admin.isStatus()) throw new ServiceException("该用户处于不可用状态");

        LoginDTO info = new LoginDTO();
        BeanUtils.copyProperties(admin, info);

        String token = TokenUtils.genToken(String.valueOf(admin.getId()), admin.getPassword());
        info.setToken(token);

        return info;
    }

    @Override
    public void changePassword(PasswordRequest request) {
        Optional.ofNullable(request.getNewPass()).orElseThrow(() -> new ServiceException("请输入密码"));
        request.setNewPass(securePass(request.getNewPass()));
        mapper.changePassword(request);
    }

    private String securePass(String password) {
        return SecureUtil.md5(password + PASS_SALT);
    }


}
