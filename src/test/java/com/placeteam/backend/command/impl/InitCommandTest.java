package com.placeteam.backend.command.impl;

import com.placeteam.backend.helper.ErrorUtils;
import com.placeteam.backend.model.STD_VALUES;
import com.placeteam.backend.server.HttpSessionConfig;
import com.placeteam.backend.server.SocketHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

public class InitCommandTest {

    private InitCommand command;
    private WebSocketSession sessionMock;
    private HttpSession httpSessionMock;
    private List<HttpSession> activeSessions;

    @BeforeEach
    public void setUp() {
        httpSessionMock = mock(HttpSession.class);
        activeSessions = new ArrayList<>();
        activeSessions.add(httpSessionMock);
        HttpSessionConfig.addSession("Test" ,httpSessionMock);
        sessionMock = mock(WebSocketSession.class);
        command = new InitCommand("test-key");
        command.setSession(sessionMock);
    }

    @Test
    public void execute_withValidKey_setsTimestampAndAddsSessionToAssignedSessions() {
        command.execute();
        verify(httpSessionMock).setAttribute("timestamp", System.currentTimeMillis());
        verify(SocketHandler.getInstance().assignedSessions).put("test-key", sessionMock);
        verify(new PaintCommand(sessionMock)).execute();
    }

    @Test
    public void execute_withNullKey_sendsError() {
        command = new InitCommand(null);
        command.setSession(sessionMock);
        command.execute();
        verify(ErrorUtils.class);
        ErrorUtils.sendError(sessionMock, "No key provided", STD_VALUES.NO_DATA_PROVIDED);
    }

    @Test
    public void execute_withUnknownKey_doesNotSetTimestampOrAddSessionToAssignedSessions() {
        command = new InitCommand("unknown-key");
        command.setSession(sessionMock);
        command.execute();
        verify(httpSessionMock, never()).setAttribute("timestamp", System.currentTimeMillis());
        verify(SocketHandler.getInstance().assignedSessions, never()).put("unknown-key", sessionMock);
        verify(new PaintCommand(sessionMock), never()).execute();
    }
}
