package com.hzhang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/5/19 11:17
 * @description：
 * @modified By：
 * @version: $
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket(Environment environment) {
        Profiles profiles = Profiles.of("dev");
        boolean flag = environment.acceptsProfiles(profiles);
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        ticketPar.name("token")
                .description("user token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
//                header中的token参数非必填，传空也可以
                .required(false).build();
        pars.add(ticketPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(flag)
                .groupName("PanzVor")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hzhang.controller"))
                .build()
                .globalOperationParameters(pars);
    }

    public ApiInfo apiInfo() {
        Contact DEFAULT_CONTACT = new Contact("PanzVor", "http://www.panzvor.com/", "344096911@qq.com");
        return new ApiInfoBuilder()
                .title("api swagger document")
                .description("个人博客项目swagger api 文档")
                .contact(DEFAULT_CONTACT)
                .version("1.0")
                .build();
    }
}
