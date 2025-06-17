package common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("Bike Share API Swagger")
                .version("1.0")
                .description("Bike Share API 명세서입니다.");

        return new OpenAPI().components(new Components()).info(info);
    }
}
