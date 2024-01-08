package com.karkar.springboot.mapper;

import com.karkar.springboot.controller.request.BaseRequest;
import com.karkar.springboot.pojo.OperatorLogger;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OperatorLoggerMapper {

    // 这里只用BaseRequest进行分页
    List<OperatorLogger> queryAllLogInfo(BaseRequest baseRequest);
    void addOperatorLog(OperatorLogger operatorLogger);

}
