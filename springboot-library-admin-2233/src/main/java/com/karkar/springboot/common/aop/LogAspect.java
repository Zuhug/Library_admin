package com.karkar.springboot.common.aop;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentParser;
import cn.hutool.json.JSONUtil;
import com.karkar.springboot.common.Result;
import com.karkar.springboot.log.LogAnnotation;
import com.karkar.springboot.pojo.OperatorLogger;
import com.karkar.springboot.service.OperatorLoggerService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Autowired
    private OperatorLoggerService service;

    @Pointcut("@annotation(com.karkar.springboot.log.LogAnnotation)")
    public void pointcut() {}

    @Around("pointcut()")
    public Result log(ProceedingJoinPoint joinPoint) throws Throwable {
        Long startTime = System.currentTimeMillis();
        Result obj = (Result) joinPoint.proceed();
        Long time = System.currentTimeMillis() - startTime;
        // 保存日志
        record(joinPoint, time, obj);
        return obj;
    }

    // 这里对这个管理员模块进行异常日志的收集
//    @AfterThrowing(pointcut = "pointcut()", throwing = "e")
//    public void logException(JoinPoint joinPoint, Throwable e) {
//        System.out.println("异常日志生成: ");
//    }

    /**
     * 管理员模块普通操作日志的生成
     *
     * @param joinPoint 连接点
     * @param time      响应时间
     * @param obj       返回结果
     */
    private void record(ProceedingJoinPoint joinPoint, Long time, Result obj) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        List<String> argList = new ArrayList<>();
        // 获取被@PathVariable()修饰的参数无法通过getArgs()获取
        Map map = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        // 被@PathVariable()修饰的参数和被@RequestBody修饰的参数需要分别获取
        // 没有想到什么好的办法去解决, 先写一个判断
        if ( JSONUtil.toJsonStr(map).equals("{}") ) {
            Arrays.stream(joinPoint.getArgs())
                    .forEach( o -> argList.add(JSONUtil.toJsonStr(o)) );
        } else {
            argList.add(JSONUtil.toJsonStr(map));
        }
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);

        log.info("======================开始日志记录=======================");
        String reqId = DateUtil.format(new Date(), "yyyyMMdd") + Math.abs(IdUtil.fastSimpleUUID().hashCode());
        String uri = request.getRequestURI();
        String module = logAnnotation.module();
        String operator = logAnnotation.operator();
        String type = logAnnotation.type().value();
        String params = argList.get(0);
        // 请求的方法名
        String clzName = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();

        String header = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgentParser.parse(header);

        log.info("请求URI: {}", uri);
        log.info("请求ID: {}", reqId);
        log.info("模块: {}", module);
        log.info("操作: {}", operator);
        log.info("类型: {}", type);
        log.info("请求参数: {}", params);
        log.info("请求方法: {}", clzName + "." + methodName + "()");
        log.info("浏览器类型: {}", userAgent.getBrowser().toString());
        log.info("操作系统: {}", userAgent.getPlatform());
        log.info("执行时间: {} ms", time);

        OperatorLogger logger = new OperatorLogger()
                                            .setId(reqId) // id
                                            .setModule(module) // 模块
                                            .setType(type) // 类型
                                            .setOperator(operator) // 操作
                                            .setReqParam(params) // 请求参数
                                            .setRespParam(JSONUtil.toJsonStr(obj)) // 响应结果
                                            .setUri(uri) // 请求路径
                                            .setMethod(clzName + "." + methodName + "()") // 请求方法
                                            .setTimeCost(time); // 花费时间

        log.info("======================结束日志记录=======================");
        service.addOperatorLog(logger);
    }

}
