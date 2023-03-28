package com.web.SpringWeb.servico.autenticacao;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class LoginInterceptorAppConfig extends WebMvcConfigurerAdapter{
	
	/**
	 * @param resgistry
	 */
	public void addIntercerptors(InterceptorRegistry resgistry) {
		resgistry.addInterceptor(new LoginInterceptor())
		.excludePathPatterns(
		"/login",
		"/error",
		"/logar",
		"/vendor/**",
		"/favicon.ico",
		"/css/**");
		
	}

}
