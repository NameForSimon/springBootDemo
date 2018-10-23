package com.wndz.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

/**
 * 相当于以前的web.xml，可以在controller上加上注解配置，可以在启动项application里面重写onStartup方法配置
 * 过滤器和拦截器的区别：过滤器就是只有写好的地址可以其他都不可以；拦截器就是只有写好的地址拦截，其他都可以。
 * @author Administrator
 *
 */
@Configuration
public class WebConfig {
	@Autowired
	private TimeInterceptor timeInterceptor;
	
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();

		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		
        List<MediaType> fastMediaTypes = new ArrayList<>();  
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);  
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes); 
		
		fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
		
		HttpMessageConverter<?> converter = fastJsonHttpMessageConverter;
		
		return new HttpMessageConverters(converter);

	}
	
	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
	    return new ServletRegistrationBean(new ServletTest(),"/servletTest");
	}

//	过滤器	
//	@Bean
//	public FilterRegistrationBean timeFilter() {
//		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//		
//		WndzFilter timeFilter = new WndzFilter();
//		registrationBean.setFilter(timeFilter);
//		
//		List<String> urls = new ArrayList<>();
//		//所有的链接都要过滤一次
//		urls.add("/*");
//		registrationBean.setUrlPatterns(urls);
//		
//		return registrationBean;
//	}
	
	@Bean
	public ServletListenerRegistrationBean<ListenerTest> servletListenerRegistrationBean() {
	    return new ServletListenerRegistrationBean<ListenerTest>(new ListenerTest());
	}
	
	
//	拦截器
//	public void addInterceptors(InterceptorRegistry registry) {
//		//使用注入不用new就是为了让spring管理生命周期
//		registry.addInterceptor(timeInterceptor);
//	}
}
