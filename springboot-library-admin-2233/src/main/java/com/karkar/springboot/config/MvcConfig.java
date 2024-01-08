package com.karkar.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private JwtIntercepter jwtIntercepter;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // 指定Controller统一接口前缀
        configurer.addPathPrefix("api", clz -> clz.isAnnotationPresent(RestController.class));
    }

//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*") // 允许跨域的域名
//                .allowedMethods("*") // 允许任何方法
//                .allowedHeaders("*") // 允许任何请求头
//                .allowCredentials(true) // 带上Cookie信息
//                .exposedHeaders(HttpHeaders.SET_COOKIE).maxAge(3600L); // maxAge(3600)表明在3600s内，不需要再发送预检请求, 可以缓存该结果
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtIntercepter)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/admin/login");
    }

}
