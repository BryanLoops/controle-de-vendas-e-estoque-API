package com.bryanlopes.controledevendaeestoque.api.infra.springdoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SpringDocConfigurations {
	
	@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("controledevendaseestoque API")
                        .description("API Rest para controle de vendas e estoque")
                        .contact(new Contact()
                                .name("Time Backend")
                                .email("backend@controle.vd"))
                .license(new License()
                        .name("Apache 2.0")
                        .url("http://voll.med/api/licenca")));
    }
}
