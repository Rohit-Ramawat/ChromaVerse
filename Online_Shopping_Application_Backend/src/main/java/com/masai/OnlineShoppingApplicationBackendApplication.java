package com.masai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication
@ComponentScan(basePackages = "com.masai")
@OpenAPIDefinition(info = @Info(title = "Online Shopping API", version = "1.0", description = "Shopping Information"))
@SecurityScheme(name = "demo-openapi", scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class OnlineShoppingApplicationBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineShoppingApplicationBackendApplication.class, args);
	}

}
