package com.karkar.springboot.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AdminPageRequest extends BaseRequest{

    @ApiModelProperty(value = "用户名, 通过用户名进行模糊查询")
    private String username;
    @ApiModelProperty(value = "联系方式, 通过联系方式进行模糊查询")
    private String phone;
    @ApiModelProperty(value = "邮箱, 通过邮箱进行模糊查询")
    private String email;

}
