package com.gachifarm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	@Qualifier(value = "loginInterceptor")
	private HandlerInterceptor interceptor;

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/loginForm").setViewName("Account/LoginForm");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor)
				.addPathPatterns("/cart/**/", "/order/**/")
				.addPathPatterns("/user/**/")
				.addPathPatterns("/store/registerForm", "/store/regist", "/store/updateForm/{storeName}", "/store/update", "/store/product/registerForm/{storeName}", "/store/product/updateForm")
				.addPathPatterns("/product/regist", "/product/registerForm", "/product/updateForm/{productId}", "/product/update")
				.addPathPatterns("/board/registerForm", "/board/register", "/board/{boardId}/updateForm", "/board/update", "/board/answer",
						"/group/product/attendForm", "/group/product/attend", "/group/product/registerForm/{productId}", "/group/product/register", "/group/product/updateForm/{gProductId}",
						"/group/product/update", "/group/buyer/list/{gProductId}/{pageNo}", "/review/registerForm/{productId}/{lineProductId}", "/review/register", "/board/{boardId}/delete");
	}
	
}
