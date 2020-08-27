package com.informanage.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@ComponentScan(basePackages={"com.informanage"})
//@MapperScan(basePackages ="com.informanage.user.mapper")
@EnableDiscoveryClient//服務注冊
@EnableFeignClients//服务发现
public class UserApplication {
        public static void main(String[] args) {
            SpringApplication.run(UserApplication.class, args);
        }
    }

