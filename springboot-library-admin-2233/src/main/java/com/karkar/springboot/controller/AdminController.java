package com.karkar.springboot.controller;

import com.karkar.springboot.common.Result;
import com.karkar.springboot.controller.dto.LoginDTO;
import com.karkar.springboot.controller.request.AdminPageRequest;
import com.karkar.springboot.controller.request.LoginRequest;
import com.karkar.springboot.controller.request.PasswordRequest;
import com.karkar.springboot.enums.OperatorType;
import com.karkar.springboot.log.LogAnnotation;
import com.karkar.springboot.pojo.Admin;
import com.karkar.springboot.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@Api(tags = {"管理员控制器"})
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService service;

    /**
     * 这个路由只是用来测试的，实际前端不会访问到这个请求, 如果想查询所有数据, 请用/admin/page
     * @return
     */
    @GetMapping("/get")
    @ApiOperation(value = "查询所有管理员信息")
    @LogAnnotation(module = "管理员列表", operator = "获取所有管理员信息", type = OperatorType.SELECT)
    public Result queryAll() {
        List<Admin> admins = service.queryAll();
        return Result.success(admins);
    }

    /**
     * 这个路由只是用来测试的，实际前端不会访问到这个请求
     * @return
     */
    @GetMapping("/get/{id}")
    @ApiOperation(value = "根据id查询管理员信息")
    @LogAnnotation(module = "管理员列表", operator = "获取单个管理员信息", type = OperatorType.SELECT)
    public Result queryById(@PathVariable("id") Integer id) {
        Admin admin = service.queryById(id);
        return Result.success(admin);
    }

    /**
     * 如果请求参数中没有携带条件的话, 代表查询所有
     * 一页最多传回10条数据
     * @param adminPageRequest
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(value = "传入一些查询条件, 查询满足条件的管理员, 对结果进行分页封装")
    @LogAnnotation(module = "管理员列表", operator = "管理员信息查询", type = OperatorType.SELECT)
    public Result page(AdminPageRequest adminPageRequest) {
        return Result.success(service.page(adminPageRequest));
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增管理员")
    @LogAnnotation(module = "管理员添加", operator = "新增管理员信息", type = OperatorType.INSERT)
    public Result addAdmin(@RequestBody Admin admin) {
        service.addAdmin(admin);
        return Result.success();
    }

    @PutMapping("/edit")
    @ApiOperation(value = "编辑管理员信息")
    @LogAnnotation(module = "管理员列表", operator = "编辑管理员信息", type = OperatorType.UPDATE)
    public Result editAdmin(@RequestBody Admin admin) {
        service.editAdmin(admin);
        return Result.success();
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation(value = "删除管理员")
    @LogAnnotation(module = "管理员列表", operator = "删除管理员信息", type = OperatorType.DELETE)
    public Result deleteAdmin(@PathVariable("id") Integer id) {
        service.deleteAdmin(id);
        return Result.success();
    }

    @PostMapping("/login")
    @ApiOperation("登录")
    @LogAnnotation(module = "登录", operator = "管理员登录", type = OperatorType.SELECT)
    public Result login(@RequestBody LoginRequest loginRequest) {
        LoginDTO info = service.login(loginRequest);
        return Result.success(info);
    }

    @PutMapping("/password")
    @ApiOperation("修改密码")
    @LogAnnotation(module = "管理员列表", operator = "修改密码", type = OperatorType.UPDATE)
    public Result password(@RequestBody PasswordRequest request) {
        service.changePassword(request);
        return Result.success();
    }
}
