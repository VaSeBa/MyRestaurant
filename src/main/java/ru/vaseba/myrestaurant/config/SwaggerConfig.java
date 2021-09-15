package ru.vaseba.myrestaurant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RestController;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Collections.singletonList;
import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@EnableSwagger2
@Import({BeanValidatorPluginsConfiguration.class})
public class SwaggerConfig {

    @Bean
    public UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .deepLinking(TRUE)
                .displayOperationId(FALSE)
                .defaultModelsExpandDepth(1)
                .defaultModelExpandDepth(1)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(FALSE)
                .docExpansion(DocExpansion.NONE)
                .filter(TRUE)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(FALSE)
                .tagsSorter(TagsSorter.ALPHA)
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                .validatorUrl(null)
                .build();
    }

    @Bean
    public SecurityConfiguration security() {
        return SecurityConfigurationBuilder
                .builder()
                .scopeSeparator(",")
                .additionalQueryStringParams(null)
                .useBasicAuthenticationWithAccessCodeGrant(false).build();
    }

    @Bean
    public Docket api() {
        return new Docket(SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(withClassAnnotation(RestController.class))
                .paths(any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("FoodVoice REST API")
                .description("")
                .version("1.0.0")
                .contact(new Contact("Valentin Badasin", "https://github.com/VaSeBa", "badasin.valentin@yandex.ru"))
                .build();
    }

    private List<? extends SecurityScheme> securitySchemes() {
        return singletonList(
                new ApiKey("apiKey", "Authorization", "header")
        );
    }

    private List<SecurityContext> securityContexts() {
        return singletonList(
                SecurityContext
                        .builder()
                        .securityReferences(defaultAuth())
                        .forPaths(any())
                        .build()
        );
    }

    private List<SecurityReference> defaultAuth() {
        return singletonList(
                new SecurityReference(
                        "apiKey",
                        new AuthorizationScope[]{new AuthorizationScope("global", "accessEverything")}
                )
        );
    }
}
