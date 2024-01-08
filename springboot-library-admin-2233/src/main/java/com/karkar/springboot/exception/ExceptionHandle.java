package com.karkar.springboot.exception;

import cn.hutool.core.util.StrUtil;
import com.karkar.springboot.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    public Result exceptionError(Exception e) {
        log.error("系统错误", e);
        return Result.error("系统错误");
    }

    @ExceptionHandler(value = ServiceException.class)
    public Result serviceExceptionError(ServiceException e) {
        log.error("业务异常", e);
        if (StrUtil.isNotBlank(e.getCode())) return Result.error(e.getCode(), e.getMessage());
        return Result.error(e.getMessage());
    }

}
