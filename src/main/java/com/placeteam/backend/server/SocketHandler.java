package com.placeteam.backend.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.json.JsonMapper.Builder;
import com.placeteam.backend.command.BaseCommand;
import com.placeteam.backend.helper.CommandHelper;

@Component
public class SocketHandler extends TextWebSocketHandler {
	private List<WebSocketSession> sessions = new ArrayList<>();
	// TODO: assign session as soon as init is run
	private Map<String, WebSocketSession> assignedSessions = new HashMap<>();

	private static final SocketHandler instance = new SocketHandler();
	
	public static SocketHandler getInstance() {
		return instance;
	}

	private static ObjectMapper getObjectMapper() {
		Builder builder = JsonMapper.builder();
		builder = builder.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true);
		// TODO: true
		builder = builder.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
		JsonMapper buildMapper = builder.build();
		return buildMapper;
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session);
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

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		try {
			String payload = message.getPayload();
			ObjectMapper mapper = getObjectMapper();
			CommandModel commandName = mapper.readValue(payload, CommandModel.class);
			Class<?> commandClass = CommandHelper.getCommandByName(commandName.getCommand());
			if (commandClass != null) {
				BaseCommand command = (BaseCommand) mapper.readValue(payload, commandClass);
				command.setSession(session);
				command.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static class CommandModel {
		private String command;

		public String getCommand() {
			return command;
		}

		@JsonSetter("command")
		public void setCommand(String command) {
			this.command = command;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return getCommand();
		}

	}
}
