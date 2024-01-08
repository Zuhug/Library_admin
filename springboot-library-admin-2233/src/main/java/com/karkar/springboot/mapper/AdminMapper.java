package com.karkar.springboot.mapper;

import com.karkar.springboot.controller.request.BaseRequest;
import com.karkar.springboot.controller.request.LoginRequest;
import com.karkar.springboot.controller.request.PasswordRequest;
import com.karkar.springboot.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminMapper {
    List<Admin> queryAll();
    Admin queryById(Integer id);
    List<Admin> queryByCondition(BaseRequest baseRequest);
    void addAdmin(Admin admin);
    void editAdmin(Admin admin);
    void deleteAdmin(Integer id);
    Admin login(LoginRequest loginRequest);
    void changePassword(PasswordRequest request);
    int uniqueCheck(String username);
    default Admin display(Admin admin) {
        return null;
    }
}
