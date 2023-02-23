package com.placeteam.backend.server;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	private static final String WEBSOCKET_HANDLER_PATH = "/websocket";
	private SocketHandler socketHandler;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		socketHandler = SocketHandler.getInstance();
		registry.addHandler(socketHandler, WEBSOCKET_HANDLER_PATH);
		
		// TODO SocketHandler.add(socketIncommingMessageHandler);
	}
}
