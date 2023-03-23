package com.placeteam.backend.server;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Enumeration;

public class HttpInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

		Enumeration<String> headerKeys = request.getHeaderNames();
		while (headerKeys.hasMoreElements()) {
			String key = headerKeys.nextElement();
			System.err.println(key + ": " + request.getHeader(key));
		}

		String sessionId = session.getId();
		if(HttpSessionConfig.getSession(sessionId) == null){
			HttpSessionConfig.addSession(sessionId, session);
		}



		Object timeStampAttribute = session.getAttribute("timeStamp");
		Object freshAttribute = session.getAttribute("fresh");
		if (timeStampAttribute == null || freshAttribute == null) {
			session.setAttribute("timeStamp", System.currentTimeMillis() / 1000L);
			session.setAttribute("fresh", true);
		} else {
			session.setAttribute("fresh", false);
		}
		System.err.printf("Session ID: %s\n", session.getId());
		System.err.printf("timeStamp: %s\n", session.getAttribute("timeStamp"));
		System.err.printf("fresh: %b\n", session.getAttribute("fresh"));

        return true;
    }
}
