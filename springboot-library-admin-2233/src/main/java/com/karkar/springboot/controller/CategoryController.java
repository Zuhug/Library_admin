package com.karkar.springboot.controller;

import com.karkar.springboot.common.Result;
import com.karkar.springboot.controller.request.CategoryRequest;
import com.karkar.springboot.pojo.Category;
import com.karkar.springboot.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@Api(tags = {"分类控制器"})
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    /**
     * 这个路由只是用来测试的，实际前端不会访问到这个请求, 如果想查询所有数据, 请用/category/page
     * @return
     */
    @GetMapping("/get")
    @ApiOperation(value = "查询所有管理员信息")
    public Result queryAll() {
        List<Category> categorys = service.queryAll();
        return Result.success(categorys);
    }

    /**
     * 这个路由只是用来测试的，实际前端不会访问到这个请求
     * @return
     */
    @GetMapping("/get/{id}")
    @ApiOperation(value = "根据id查询管理员信息")
    public Result queryById(@PathVariable("id") Integer id) {
        Category category = service.queryById(id);
        return Result.success(category);
    }

    /**
     * 如果请求参数中没有携带条件的话, 代表查询所有
     * 一页最多传回10条数据
     * @param categoryRequest
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(value = "传入一些查询条件, 查询满足条件的分类, 对结果进行分页封装")
    public Result page(CategoryRequest categoryRequest) {
        return Result.success(service.page(categoryRequest));
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增分类")
    public Result addCategory(@RequestBody Category category) {
        service.addCategory(category);
        return Result.success();
    }

    @PutMapping("/edit")
    @ApiOperation(value = "编辑分类信息")
    public Result editCategory(@RequestBody Category category) {
        service.editCategory(category);
        return Result.success();
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation(value = "删除分类")
    public Result deleteCategory(@PathVariable("id") Integer id) {
        service.deleteCategory(id);
        return Result.success();
    }
}
