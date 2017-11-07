package com.tianqian.self;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.tianqian.self.dao")
public class SelfFalseworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SelfFalseworkApplication.class, args);
	}
}
