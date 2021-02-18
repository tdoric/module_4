package com.example.m4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example*")
public class M4Application {

	public static void main(String[] args) {
		SpringApplication.run(M4Application.class, args);
	}

}
