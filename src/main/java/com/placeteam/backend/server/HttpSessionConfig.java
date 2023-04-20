package com.placeteam.backend.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@Configuration
public class HttpSessionConfig {
    private static final Map<String, HttpSession> sessions = new HashMap<>();

    public static void addSession(String sessionId, HttpSession session) {
        sessions.put(sessionId, session);
    }

    public static List<HttpSession> getActiveSessions() {
        return new ArrayList<>(sessions.values());
    }

    public static HttpSession getSession(String sessionId) {
        return sessions.get(sessionId);
    }

    @Bean
    public ServletListenerRegistrationBean<HttpSessionListener> httpSessionListener() {
        ServletListenerRegistrationBean<HttpSessionListener> listenerRegistrationBean = new ServletListenerRegistrationBean<>();
        listenerRegistrationBean.setListener(new HttpSessionListener() {
            @Override
            public void sessionCreated(HttpSessionEvent hse) {
                sessions.put(hse.getSession().getId(), hse.getSession());
            }

            @Override
            public void sessionDestroyed(HttpSessionEvent hse) {
                sessions.remove(hse.getSession().getId());
            }
        });
        return listenerRegistrationBean;
    }
}
