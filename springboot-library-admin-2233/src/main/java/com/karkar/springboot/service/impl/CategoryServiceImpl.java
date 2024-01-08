package com.karkar.springboot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.karkar.springboot.controller.request.BaseRequest;
import com.karkar.springboot.mapper.CategoryMapper;
import com.karkar.springboot.pojo.Category;
import com.karkar.springboot.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper mapper;

    @Override
    public List<Category> queryAll() {
        return mapper.queryAll();
    }

    @Override
    public Category queryById(Integer id) {
        return mapper.queryById(id);
    }

    @Override
    public PageInfo<Category> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize(), baseRequest.getOrderBy());
        List<Category> categories = mapper.queryByCondition(baseRequest);
        PageInfo<Category> categoryPageInfo = new PageInfo<>(categories);
        return categoryPageInfo;
    }

    @Override
    public void addCategory(Category category) {
        mapper.addCategory(category);
    }

    @Override
    public void editCategory(Category category) {
        mapper.editCategory(category);
    }

    @Override
    public void deleteCategory(Integer id) {
        mapper.deleteCategory(id);
    }
}
