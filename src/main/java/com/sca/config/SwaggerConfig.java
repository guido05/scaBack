package com.sca.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
public class SwaggerConfig implements WebMvcConfigurer {

	List<String> lista = Arrays.asList("https", "http");
	Set<String> protocolo = new HashSet<>(lista);

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.sca.controller")).paths(PathSelectors.any())
				.build().apiInfo(apiInfo()).groupName("public-api");
	}
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Servicios Faturacion")
				.description("Documentación de los servicios facturacion").contact(null)
				.description("Acá va la información del contacto").licenseUrl("FERRARI GUIDO").version("1.0").build();
	}

}
