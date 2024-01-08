package com.karkar.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(Environment env) {

        // 设置要显示的Swagger环境
        Profiles profiles = Profiles.of("dev");

        // 通过env.acceptsProfiles判断是否处于自己设定的环境中
        boolean is_dev = env.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                // 是否启用Swagger, 默认true, 如果false,则访问不到swagger-ui.html
                .enable(is_dev)
                .groupName("Karkar")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.karkar.springboot.controller"))
                .paths(PathSelectors.ant("/**"))
                .build();
    }

    //构建 api文档的详细信息函数
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("XX平台API接口文档")
                //创建人
                .contact(new Contact("蓝心海", "https://www.baidu.com/",
                        "3050494918@qq.com"))
                //版本号
                .version("1.0")
                //描述
                .description("系统API描述")
                .build();
    }

}
