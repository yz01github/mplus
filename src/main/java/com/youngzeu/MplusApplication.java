package com.youngzeu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@MapperScan(basePackages = "com.youngzeu.mplus.dao")
@SpringBootApplication
@EnableAsync
public class MplusApplication {

	public static void main(String[] args) {
		SpringApplication.run(MplusApplication.class, args);
	}

}
