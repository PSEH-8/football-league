package com.exercise.apis.config.spring;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket serviceDocumentation() {

        return new Docket(DocumentationType.SWAGGER_2).groupName("application-api").apiInfo(metadata()).select()
                .paths(Predicates.or(PathSelectors.regex("/v1/.*"))).build();
    }

    @Bean
    public UiConfiguration uiConfig() {

        return UiConfiguration.DEFAULT;
    }

    private static ApiInfo metadata() {

        return new ApiInfoBuilder().title("Football League Springboot")
                .description("Football League Springboot ")
                .version("1.0").contact("sumit.nagariya@flipkart.com").build();
    }

}
