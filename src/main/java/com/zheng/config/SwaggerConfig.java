package com.zheng.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: ZhengTianLiang
 * @date: 2020/12/2 0002  21:40
 * @desc:
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * @author YanChengLong
     * @date 2019/3/27
     * @desc 注入 Swagger2 bean
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zheng.controller")) // 要扫描的包
                .paths(PathSelectors.any())
                .build();
    }
//  需要注意是的，这个apis方法是swagger要扫描的包，它必须写在select的后面，因为他是select方法返回对象里面的方法


    /**
     * @author YanChengLong
     * @date 2019/3/27
     * @desc api输入
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("产业资源共享平台模块文档 -——后端")
                .description("java -- 产业资源共享平台 模块文档示例")
                .termsOfServiceUrl("https://me.csdn.net/qq_27535933")
                .version("1.0")
                .build();
    }

}
