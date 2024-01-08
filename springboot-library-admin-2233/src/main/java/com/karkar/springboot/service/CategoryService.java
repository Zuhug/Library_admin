package com.karkar.springboot.service;

import com.github.pagehelper.PageInfo;
import com.karkar.springboot.controller.request.BaseRequest;
import com.karkar.springboot.pojo.Category;

import java.util.List;

public interface CategoryService {

    List<Category> queryAll();
    Category queryById(Integer id);
    PageInfo<Category> page(BaseRequest baseRequest);
    void addCategory(Category category);
    void editCategory(Category category);
    void deleteCategory(Integer id);

}
