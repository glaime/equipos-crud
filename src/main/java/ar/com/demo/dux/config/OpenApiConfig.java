package ar.com.demo.dux.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info =  @Info(
                title = "Challenge DUX Software",
                version = "1.0.0",
                description = "Resolución de prueba técnica para DUX"
        )
)
public class OpenApiConfig {

}
