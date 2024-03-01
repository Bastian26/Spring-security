package com.mybytes.springsection2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.mybytes.springsection2.controller") //Optional - @SpringBootApplication contains component scan
public class MyBytesBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBytesBackendApplication.class, args);
	}

}
