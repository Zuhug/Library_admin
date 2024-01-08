package com.karkar.springboot.controller.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserPageRequest extends BaseRequest {

    @ApiModelProperty(value = "名字, 通过名字进行模糊查询")
    private String name;
    @ApiModelProperty(value = "联系方式, 通过联系方式进行模糊查询")
    private String phone;

}
