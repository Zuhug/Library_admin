package com.karkar.springboot.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.karkar.springboot.exception.ServiceException;
import com.karkar.springboot.pojo.Admin;
import com.karkar.springboot.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class JwtIntercepter implements HandlerInterceptor {

    private static final String TOKKEN_ERROR = "401";

    @Autowired
    private AdminService adminService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();

        // 查询请求发出前的OPTIONS请求是检查服务器是否支持跨域的请求,
        // 并没有携带headers中的token信息
        // 需要对这个请求进行放行
        if ("OPTIONS".equals(method)) {
            return true;
        }

        String token = Optional.ofNullable(request.getHeader("token"))
                .orElseGet(() -> request.getParameter("token"));
        Optional.ofNullable(token).orElseThrow(() -> new ServiceException(TOKKEN_ERROR,"无token, 请重新登录"));
        String adminId;
        try {
            adminId = JWT.decode(token).getAudience().get(0);
            Admin admin = adminService.queryById(Integer.valueOf(adminId));
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(admin.getPassword())).build();
            verifier.verify(token);
        }catch (Exception e) {
            throw new ServiceException(TOKKEN_ERROR, "token验证失败, 请重新登录");
        }
        return true;
    }
}
