package com.wndz.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration  
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	/**
	 * 注册websocket，html调用
	 */
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addHandler(webSocketServer(), "/webSocketServer/*"); 
	}

	   @Bean
	    public WebSocketHandler webSocketServer() {  
	        return new WebSocketServer();  
	    } 
}
