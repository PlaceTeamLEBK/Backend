package com.placeteam.backend.command.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.placeteam.backend.model.Cooldown;
import com.placeteam.backend.model.enums.CommandNames;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CooldownCommandTest {

    @Mock
    private WebSocketSession sessionMock;

    private CooldownCommand commandUnderTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        //when session is open return true

        when(sessionMock.isOpen()).thenReturn(true);
        Cooldown daten = new Cooldown();
        daten.setCooldown(10);
        commandUnderTest = new CooldownCommand(daten);
        commandUnderTest.setSession(sessionMock);
    }

    @Test
    void execute_sendsResultAsJsonToWebSocketSession() throws IOException {
        // Setup
        final String expectedResult = "{\"data\":{\"seconds\":10},\"name\":\"cooldown\"}";


        // Run the test
        commandUnderTest.execute();

        // Verify the results
        verify(sessionMock).sendMessage(new TextMessage(expectedResult));
    }

    @Test
    void execute_sessionNotOpen_throwsException() throws IOException {
        // Arrange
        when(sessionMock.isOpen()).thenReturn(false);

        // Act & Assert
        Assertions.assertThrows(RuntimeException.class, () -> commandUnderTest.execute());
    }


    @Test
    void getName_returnsCooldown() {
        // Act
        final CommandNames result = commandUnderTest.getName();

        // Assert
        assertEquals(CommandNames.COOLDOWN, result);
    }

    @Test
    void getDaten_returnsDaten() {
        // Act
        final Cooldown result = commandUnderTest.getDaten();

        // Assert
        assertEquals(10, result.getCooldown());
    }
}
