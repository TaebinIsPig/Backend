package com.project.school.global.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun api(): Docket =
        Docket(DocumentationType.OAS_30)
            .apiInfo(apiInfo())
            .securitySchemes(listOf(apiKey()))
            .securityContexts(listOf(securityContext()))
            .useDefaultResponseMessages(false)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.project.school.domain"))
            .paths(PathSelectors.ant("/api/**"))
            .build()

    @Bean
    fun securityContext(): SecurityContext =
        SecurityContext.builder()
            .securityReferences(defaultAuth())
            .operationSelector { true }
            .build()

    private fun defaultAuth(): List<SecurityReference> =
        arrayOf(AuthorizationScope("global", "accessEverything"))
            .let { listOf(SecurityReference("Authorization", it)) }

    private fun apiKey() = ApiKey("Authorization", "Authorization", "header")

    private fun apiInfo(): ApiInfo =
        ApiInfoBuilder()
            .title("Schoo API Server")
            .description("School API Doc")
            .contact(Contact("윤태빈", "https://github.com/yoontaebin123", "gusckd000@gmail.com"))
            .version("1.0")
            .build()

}