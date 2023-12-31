package br.com.paramazon.demo.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/*@Configuration
@EnableSwagger2*/
public class SwaggerConfig {

    private static final String URL = "http://paramazon.com/";
    private static final String COMPANY_NAME = "Paramazon";

  /*  @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(informacoesApi())
                .securityContexts(List.of(securityContext()))
                .securitySchemes(List.of(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.paramazon.demo.application.controller"))
                .paths(PathSelectors.any()).build();
    }

    private ApiKey apiKey(){
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return List.of(new SecurityReference("JWT", authorizationScopes));
    }

    private ApiInfo informacoesApi() {
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        apiInfoBuilder.title("Paramazon - API");
        apiInfoBuilder.description("API REST para disponibilziar os serviços de gerenciamento do sistema Paramazon");
        apiInfoBuilder.version("1.0");
        apiInfoBuilder.termsOfServiceUrl("Termos de uso: Todos os direitos reservados - " + COMPANY_NAME);
        apiInfoBuilder.license(COMPANY_NAME);
        apiInfoBuilder.licenseUrl(URL);
        apiInfoBuilder.contact(new Contact(COMPANY_NAME, URL, "matheusdalvino50@gmail.com"));
        return apiInfoBuilder.build();
    }*/
}
