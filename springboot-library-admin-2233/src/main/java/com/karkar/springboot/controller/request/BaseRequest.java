package com.karkar.springboot.controller.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseRequest {

    // 设置一个默认值, 即使不传值也会有一些默认数据
    @ApiModelProperty(value = "当前页")
    private Integer pageNum = 1;
    @ApiModelProperty(value = "当前页面大小")
    private Integer pageSize = 10;
    @ApiModelProperty(value = "排序规则")
    private String orderBy = "id desc";

}
