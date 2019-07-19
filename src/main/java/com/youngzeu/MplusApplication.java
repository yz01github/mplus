package com.youngzeu.mplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.youngzeu.mplus.dao")
@SpringBootApplication
public class MplusApplication {

	public static void main(String[] args) {
		SpringApplication.run(MplusApplication.class, args);
	}

}
