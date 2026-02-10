package org.example.floatnovel.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("float-novel")
                .packagesToScan("org.example.floatnovel.controller") // 改成你的 controller 包路径
                .build();
    }
}
