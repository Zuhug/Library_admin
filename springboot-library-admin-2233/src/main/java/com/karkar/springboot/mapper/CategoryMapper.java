package com.karkar.springboot.mapper;

import com.karkar.springboot.controller.request.BaseRequest;
import com.karkar.springboot.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CategoryMapper {

    List<Category> queryAll();
    Category queryById(Integer id);
    List<Category> queryByCondition(BaseRequest baseRequest);
    void addCategory(Category category);
    void editCategory(Category category);
    void deleteCategory(Integer id);
}
