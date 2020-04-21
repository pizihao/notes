package com.liu.swagger.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author shidacaizi
 * @date 2020/4/20 16:42
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    //配置了swagger的Docket的bean实例
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.liu.swagger.controller"))
                .build();
    }

    //配置Swagger信息 = apiInfo
    private ApiInfo apiInfo(){
        //作者信息
        Contact contact = new Contact("shidacaizi", "http://shidacaizi.cn/", "2335715300@qq.com");
        return new ApiInfo(
                "SwaggerAPI文档",
                "志愿带走世界的光明",
                "1.0",
                "http://localhost:8080/",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }
}
