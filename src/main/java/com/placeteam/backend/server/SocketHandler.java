package com.placeteam.backend.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketHandler extends TextWebSocketHandler{
	private static List<SocketMessaging> messageHandlerList = new ArrayList<SocketMessaging>();

	private static final SocketHandler thisObj = new SocketHandler();
	
	public static SocketHandler getInstance() {
		return thisObj;
	}
	
	public static void notifyMessageHandler(String message, SocketMessaging messsageHandler) {
		if (messsageHandler == null) {
			remove(messsageHandler);
			return;
		}
		messsageHandler.incommingMessage(message);
	}
	
	public static void remove(SocketMessaging messageHandler) {
		messageHandlerList.remove(messageHandler);
	}
	
	private List<WebSocketSession> sessions = new ArrayList<>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session);
	}
	
	public static void add(SocketMessaging messageHandler) {
		messageHandlerList.add(messageHandler);
	}
	
	public void sendMessage(String message) throws IOException {
		TextMessage textMessage = new TextMessage(message);
		for (WebSocketSession session : sessions) {
			if (!session.isOpen()) {
				continue;
			}
			session.sendMessage(textMessage);
		}
	}
}
