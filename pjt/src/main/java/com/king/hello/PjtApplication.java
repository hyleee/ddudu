package com.king.hello;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.king")
@MapperScan("com.king.model.dao")
public class PjtApplication {

	public static void main(String[] args) {
		SpringApplication.run(PjtApplication.class, args);
	}

}
