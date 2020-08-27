package com.informanage.sendinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication(exclude=DataSourceAutoConfiguration.class)
@ComponentScan(basePackages="com.informanage")
@EnableDiscoveryClient//服務注冊
@EnableFeignClients//服务发现
public class SendInfoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SendInfoApplication.class, args);
	}
}
