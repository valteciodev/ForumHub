package Alura.ForumHub.infra.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                        .info(new Info()
                                .title("ForumHub API")
                                .description("API do projeto ForumHub - Alura")
                                .version("1.0.0")
                                .contact(new Contact()
                                        .name("Valtecio Silva Almeida")
                                        .email("valtecio.developer@gmail.com")
                                )
                                .license(new License()
                                        .name("Apache License 2.0")
                                        .url("https://www.apache.org/licenses/LICENSE-2.0")
                                )
                );
    }
}
