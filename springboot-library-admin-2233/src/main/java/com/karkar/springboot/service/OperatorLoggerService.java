package com.karkar.springboot.service;

import com.github.pagehelper.PageInfo;
import com.karkar.springboot.controller.request.BaseRequest;
import com.karkar.springboot.pojo.OperatorLogger;

public interface OperatorLoggerService {

    PageInfo<OperatorLogger> page(BaseRequest baseRequest);
    void addOperatorLog(OperatorLogger operatorLogger);
}
