package com.generali.mailservice.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customizeSwagger(){
        final String bearerName = "Bearer";
        final String basicName = "Basic";

        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(bearerName))
                .addSecurityItem(new SecurityRequirement().addList(basicName))
                .components(new Components()
                        .addSecuritySchemes(bearerName, new SecurityScheme()
                                .name(bearerName)
                                .type(SecurityScheme.Type.HTTP)
                                .bearerFormat("JWT")
                                .scheme("bearer"))
                        .addSecuritySchemes(basicName, new SecurityScheme()
                                .name(basicName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("basic")))
                .info(new Info()
                        .title("Generali mail-service authentication")
                        .version("v1"));



    }


}
