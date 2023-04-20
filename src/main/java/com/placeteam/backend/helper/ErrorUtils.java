package com.placeteam.backend.helper;

import java.io.IOException;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.placeteam.backend.model.Error;
import com.placeteam.backend.model.STD_VALUES;
import com.placeteam.backend.server.SocketHandler;

public class ErrorUtils {


    //Wollte Luca haben :(
    private static String[] affronts = {
            "You're a disgrace to the human race.",
            "You're a waste of oxygen.",
            "You're a waste of space.",
            "You're a waste of time.",
            "You're a waste of life.",
            "You're a waste of food.",
            "You're a waste of water.",
            "You're a waste of air.",
            "You're a waste of energy.",
            "You're a waste of money.",
            "You're a waste of resources.",
            "You're a waste of food."};

    public static String getRandomAffront() {
        return affronts[(int) (Math.random() * affronts.length)];
    }

    public static void sendAffrontError(WebSocketSession session) {
        sendError(session, getRandomAffront(), STD_VALUES.ERROR_CODE_AFFRONT);
    }

    public static void sendError(WebSocketSession session, String message, int code) {
        try {
            String jsonString = SocketHandler.getObjectMapper().writeValueAsString(new Error(message, code));
            session.sendMessage(new TextMessage(jsonString));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendNoDataError(WebSocketSession session) {
        sendError(session, "No data provided", STD_VALUES.NO_DATA_PROVIDED);
    }
}
