package com.placeteam.backend.server;

import org.springframework.web.socket.WebSocketSession;

public interface SocketMessaging {
	void incommingMessage(String message, WebSocketSession session);
}
