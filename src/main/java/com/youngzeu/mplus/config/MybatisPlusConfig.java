package com.youngzeu.mplus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

@Configuration
public class MybatisPlusConfig {

	@Bean
	public ISqlInjector getMetaObjectHandler() {
		return new LogicSqlInjector();
	}

	/**
	 * 分页插件
	* @Description: TODO
	* @param 		@return    参数  
	* @return 		PaginationInterceptor    返回类型  
	* @throws
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor page = new PaginationInterceptor();
		page.setDialectType("mysql");
		return page;
	}


}
