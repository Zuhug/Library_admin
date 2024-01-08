package com.karkar.springboot.utils;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.karkar.springboot.exception.ServiceException;
import com.karkar.springboot.pojo.Admin;
import com.karkar.springboot.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Component
public class TokenUtils {

    private static AdminService staticAdminService;

    @Autowired
    private AdminService adminService;

    @PostConstruct
    public void setAdminService() {
        staticAdminService = adminService;
    }

    public static String genToken(String id, String sign) {
        return JWT.create().withAudience(id)
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2))
                .sign(Algorithm.HMAC256(sign));
    }

    public static Admin getCurrentUser() {
        HttpServletRequest request = ( (ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = Optional.ofNullable(request.getHeader("token"))
                .orElseGet(() -> request.getParameter("token"));
        Optional.ofNullable(token).orElseThrow(() -> new ServiceException("获取token失败!"));
        String adminId;
        try {
             adminId = JWT.decode(token).getAudience().get(0);
        }catch (Exception e) {
            log.error("获取当前登录的管理员信息失败! token:{}", token, e);
            return null;
        }
        return staticAdminService.queryById(Integer.valueOf(adminId));
    }

}
