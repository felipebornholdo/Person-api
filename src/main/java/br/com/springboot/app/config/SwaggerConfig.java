package br.com.springboot.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String AUTH_SERVER = "/oauth";
    @Value("${security.oauth2.client.client-id}")
    private String CLIENT_ID;
    @Value("${security.oauth2.client.client-secret}")
    private String CLIENT_SECRET;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.springboot.app"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData())
                .securitySchemes(Collections.singletonList(securityScheme()))
                .securityContexts(Collections.singletonList(securityContext()));
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Spring Boot REST API")
                .description("\"Spring Boot REST API for Softplan\"")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .build();
    }

    @Bean
    public SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder()
                .clientId(CLIENT_ID)
                .clientSecret(CLIENT_SECRET)
                .scopeSeparator(" ")
                .useBasicAuthenticationWithAccessCodeGrant(true)
                .build();
    }

    private SecurityScheme securityScheme() {
        GrantType grantType = new AuthorizationCodeGrantBuilder()
                .tokenEndpoint(new TokenEndpoint(AUTH_SERVER + "/token", "oauthtoken"))
                .tokenRequestEndpoint(
                        new TokenRequestEndpoint(AUTH_SERVER + "/authorize", CLIENT_ID, CLIENT_SECRET))
                .build();

        return new OAuthBuilder().name("spring_oauth")
                .grantTypes(Collections.singletonList(grantType))
                .scopes(Arrays.asList(scopes()))
                .build();
    }

    private AuthorizationScope[] scopes() {
        return new AuthorizationScope[]{
                new AuthorizationScope("Leitura", "Para operações de leitura."),
                new AuthorizationScope("Escrita", "Para operações de escrita."),
                new AuthorizationScope("Total", "Acesso total.")};
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(
                        Collections.singletonList(new SecurityReference("spring_oauth", scopes())))
                .forPaths(PathSelectors.regex("/foos.*"))
                .build();
    }

}
