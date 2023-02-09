package com.placeteam.backend.server;

import java.util.Enumeration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class StaticController {
	@GetMapping("/test/*")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		System.out.println();
		
		Enumeration<String> headerKeys = request.getHeaderNames();
		while (headerKeys.hasMoreElements()) {
			String key = headerKeys.nextElement();
			System.out.println(key + ": " + request.getHeader(key));
		}

		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			String sessionUuid = SessionHolder.createSessionId();
			createSessionCookie(response, sessionUuid);
		} else {
			System.out.println(cookies[0].getValue());
			Session session = SessionHolder.getSession(cookies[0].getValue());
			System.out.println(session.isSessionXOld(1));
		}

		return "Greetings from Spring Boot!";
	}

	private void createSessionCookie(HttpServletResponse response, String sessionUuid) {
		Cookie cookie = new Cookie("session", sessionUuid);
		cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
		response.addCookie(cookie);
	}
}
