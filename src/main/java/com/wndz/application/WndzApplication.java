package com.wndz.application;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableAutoConfiguration
public class WndzApplication {
	public static void main(String[] args) {
		SpringApplication.run(WndzApplication.class, args);
	}
}
