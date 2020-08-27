package com.informanage.usermain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages={"com.informanage"})
//@MapperScan(basePackages ="com.informanage.usermain.mapper")
@EnableDiscoveryClient//服務注冊
@EnableFeignClients//服务发现
@EnableScheduling//启动定时任务
@EnableCaching//启动redis缓存
@EnableTransactionManagement // 开启事务
public class MainApplication {
        public static void main(String[] args) {
            SpringApplication.run(MainApplication.class, args);
        }
    }

