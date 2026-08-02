package com.management.MonaLibrary.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    public OpenAPI MySpringBootSwaggerDemo(){
        return new OpenAPI()
                .info(new Info().title("Sample Programme for Swagger configuration with SpringBoot")
                        .description("It is a demo project to show Swagger configuration with SpringBoot "+
                                "where entire Configuration of OPEN API Swagger is used to display documentation " +
                                "for exposed APIs by my Controller and it's documentation"));

    }
}
