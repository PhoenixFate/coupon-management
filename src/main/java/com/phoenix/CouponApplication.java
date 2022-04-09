package com.phoenix;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;


@ServletComponentScan
@MapperScan("com.phoenix.*.dao")
@SpringBootApplication
@EnableScheduling
public class CouponApplication extends SpringBootServletInitializer{
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CouponApplication.class);
	}
	
    public static void main(String[] args) {
        SpringApplication.run(CouponApplication.class, args);
    }




}
