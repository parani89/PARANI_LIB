package com.kvp.web.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                //.paths(PathSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error"))).build().apiInfo(apiInfo());

    }
    private ApiInfo apiInfo() {

        Contact contact = new Contact("Parani", "","r.parani89@gmail.com");

        return new ApiInfoBuilder().description("PARANI API FOR LIBRARY")
                .license("Parani Express License").licenseUrl("www.google.com")
                .title("LIBRARY API")
                .termsOfServiceUrl("www.google.com")
                .contact(contact).build();
    }
}