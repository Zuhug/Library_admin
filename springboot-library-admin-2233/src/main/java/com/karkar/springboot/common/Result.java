package com.karkar.springboot.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "通用的返回给前端的类 Result")
public class Result {

    @ApiModelProperty(value = "成功码")
    private static final String SUCCESS_CODE = "200";

    @ApiModelProperty(value = "错误码")
    private static final String ERROR_CODE = "-1";

    @ApiModelProperty(value = "返回状态码")
    private String code;
    @ApiModelProperty(value = "前端从这里接收传回的数据")
    private Object data;
    @ApiModelProperty(value = "一些消息提示, 主要用于请求失败的时候")
    private String msg;


    public static Result success(Object data) {
        return success()
                .setData(data);
    }

    public static Result success() {
        return new Result()
                .setCode(SUCCESS_CODE);
    }

    public static Result error(String msg) {
        return new Result()
                .setCode(ERROR_CODE)
                .setMsg(msg);
    }

    public static Result error(String code, String msg) {
        return new Result()
                .setCode(code)
                .setMsg(msg);
    }

}
