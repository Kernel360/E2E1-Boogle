package com.kernel360.boogle.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.kernel360.boogle")) //현재 RequestMapping으로 할당된 모든 URL 리스트를 추출
                .paths(PathSelectors.any()).build() //그 중에 /**인 URL들만 필터링
                .useDefaultResponseMessages(false);
        //		.globalOperationParameters(global)
    }

    // Swagger 설명 설정
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Boogle API Documentation").description("Boogle API 문서")
                .version("1.0.0").build();
    }
}