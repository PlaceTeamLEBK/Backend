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
import com.placeteam.backend.helper.ErrorUtils;
import com.placeteam.backend.model.STD_VALUES;

@Component
public class SocketHandler extends TextWebSocketHandler {
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
    private static final SocketHandler instance = new SocketHandler();

    public static SocketHandler getInstance() {
        return instance;
    }

    public static ObjectMapper getObjectMapper() {
        Builder builder = JsonMapper.builder();
        builder = builder.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true);
        builder = builder.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JsonMapper buildMapper = builder.build();
        return buildMapper;
    }

    public Map<String, WebSocketSession> assignedSessions = new HashMap<>();

    private List<WebSocketSession> sessions = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
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
            } else {
                ErrorUtils.sendError(session, "Command:  \"" + commandName + "\" not found", STD_VALUES.COMMAND_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ErrorUtils.sendAffrontError(session);
        }
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
