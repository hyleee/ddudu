package com.ssafy.ddudu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ssafy.ddudu.model.dao")
public class DduduApplication {

	public static void main(String[] args) {
		SpringApplication.run(DduduApplication.class, args);
	}

}
