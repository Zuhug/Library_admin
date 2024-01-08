package com.karkar.springboot.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "操作日志记录")
public class OperatorLogger implements Serializable {

    @ApiModelProperty(value = "操作Id")
    private String id;
    @ApiModelProperty(value = "模块")
    private String module;
    @ApiModelProperty(value = "操作")
    private String operator;
    @ApiModelProperty(value = "请求参数")
    private String reqParam;
    @ApiModelProperty(value = "响应参数")
    private String respParam;
    @ApiModelProperty(value = "花费时间")
    private Long timeCost;
    @ApiModelProperty(value = "请求路径")
    private String uri;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createtime;
    @ApiModelProperty(value = "操作方法")
    private String method;
    @ApiModelProperty(value = "操作类型")
    private String type;

}
