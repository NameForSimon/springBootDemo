package com.wndz.application;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
@MapperScan("com.wndz.mapper")//mapper和启动类不在同一目录下就要开启启动时对mapper的包扫描
public class WndzApplication {
	public static void main(String[] args) {
		SpringApplication.run(WndzApplication.class, args);
	}
}
