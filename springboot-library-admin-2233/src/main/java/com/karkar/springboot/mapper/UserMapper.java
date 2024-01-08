package com.karkar.springboot.mapper;

import com.karkar.springboot.controller.request.BaseRequest;
import com.karkar.springboot.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    List<User> queryAll();
    User queryById(Integer id);
    List<User> queryByCondition(BaseRequest baseRequest);
    void addUser(User user);
    void editUser(User user);
    void deleteUser(Integer id);
    default User display(User user) {
        return null;
    }

}
