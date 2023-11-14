 package com.mdtalalwasim.blog.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI openAPI() {
		  
		 
		return new OpenAPI()
				.info(new Info().title("Blog Application Beckend API")
						.description("This is blog application project api and Developed by Md. Talal Wasim")
						.version("1.0")
						.contact(new Contact().name("Md. Talal Wasim").email("md.talalwasim@gmail.com").url("https://mdtalalwasim.github.io/"))
						.license(new License().name("Licence"))
						).externalDocs(new ExternalDocumentation().url("https://mdtalalwasim.github.io/").description("This is external url"));
	}

}
