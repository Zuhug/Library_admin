package com.karkar.springboot.controller;

import com.karkar.springboot.common.Result;
import com.karkar.springboot.controller.request.UserPageRequest;
import com.karkar.springboot.pojo.User;
import com.karkar.springboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin // 解决前端跨域问题
@Api(tags = {"用户控制器"})
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @ApiOperation(value = "查询所有用户信息")
    @GetMapping("/get")
    public Result queryAll() {
        List<User> users = service.queryAll();
        return Result.success(users);
    }

    @ApiOperation(value = "根据id查询用户信息")
    @GetMapping("/get/{id}")
    public Result queryById(@PathVariable("id") Integer id) {
        User user = service.queryById(id);
        return Result.success(user);
    }

    @ApiOperation(value = "传入一些查询条件, 查询满足条件的用户, 对结果进行分页封装")
    @GetMapping("/page")
    public Result page(UserPageRequest userRequest) {
        return Result.success(service.page(userRequest));
    }

    @ApiOperation(value = "新增用户")
    @PostMapping("/add")
    public Result addUser(@RequestBody User user) {
        service.addUser(user);
        return Result.success();
    }

    @ApiOperation(value = "编辑用户信息")
    @PutMapping("/edit")
    public Result editUser(@RequestBody User user) {
        service.editUser(user);
        return Result.success();
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/del/{id}")
    public Result deleteUser(@PathVariable("id") Integer id) {
        service.deleteUser(id);
        return Result.success();
    }

}
