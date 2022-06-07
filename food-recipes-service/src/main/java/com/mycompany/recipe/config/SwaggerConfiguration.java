package com.mycompany.recipe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * Swagger Configuration class which contains the configuration for swagger.
 * @author Noble Sebastian.
 * @version 1.0.1.0
 */

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    /**
     *  Determine the API methods to show in swagger UI.
     * @return Docket
     */
    @Bean
    public Docket redditCloneApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.mycompany.recipe.contoller"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(getApiInfo());
    }

    /**
     * Populates the API basic information swagger UI.
     * @return ApiInfo
     */
    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
            .title("Recipes Api")
            .version("1.0.1.0")
            .description("API for Recipes Application")
            .contact(new Contact("Noble Sebastian", "//", "noblesebastiank@gmail.com"))
            .license("Apache License Version 2.0")
            .build();
    }
}