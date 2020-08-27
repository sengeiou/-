package com.informanage.common;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

@Configuration
//@MapperScan(basePackages ={"com.informanage.user.mapper"})
@MapperScan("com.informanage.*.mapper")
public class MapperConfig {
	// 逻辑删除插件,3.1.1起不需要自己配置

	/**
	 * 分页插件
	 */
	/**
	 * mybatis-plus分页插件
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
		return paginationInterceptor;
	}

}
