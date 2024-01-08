package com.karkar.springboot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.karkar.springboot.controller.request.BaseRequest;
import com.karkar.springboot.mapper.OperatorLoggerMapper;
import com.karkar.springboot.pojo.OperatorLogger;
import com.karkar.springboot.service.OperatorLoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperatorLoggerServiceImpl implements OperatorLoggerService {

    @Autowired
    private OperatorLoggerMapper mapper;


    @Override
    public PageInfo<OperatorLogger> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize(), baseRequest.getOrderBy());
        List<OperatorLogger> admins = mapper.queryAllLogInfo(baseRequest);
        PageInfo<OperatorLogger> loggerPageInfo = new PageInfo<>(admins);
        return loggerPageInfo;
    }

    @Override
    public void addOperatorLog(OperatorLogger operatorLogger) {
        mapper.addOperatorLog(operatorLogger);
    }

}
