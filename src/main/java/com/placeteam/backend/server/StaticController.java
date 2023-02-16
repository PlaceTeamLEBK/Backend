package com.placeteam.backend.server;

import java.util.Enumeration;

import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
public class StaticController {
	@GetMapping(path = "/")
	public FileSystemResource index(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Enumeration<String> headerKeys = request.getHeaderNames();
		while (headerKeys.hasMoreElements()) {
			String key = headerKeys.nextElement();
			System.err.println(key + ": " + request.getHeader(key));
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
		return new FileSystemResource("public/index.html");

		//Cookie sessionCookie = WebUtils.getCookie(request, "session");
		//if (sessionCookie == null) {
		//	String sessionUuid = SessionHolder.createSessionId();
		//	createSessionCookie(response, sessionUuid);
		//}

		//Cookie[] cookies = request.getCookies();
		//if (cookies == null) {
		//	String sessionUuid = SessionHolder.createSessionId();
		//	createSessionCookie(response, sessionUuid);
		//} else {
		//	System.out.println(cookies[0].getValue());
		//	Session session = SessionHolder.getSession(cookies[0].getValue());
		//	System.out.println(session.isSessionXOld(1));
		//}

		//return new FileSystemResource("public/index.html");
	}

	private void createSessionCookie(HttpServletResponse response, String sessionUuid) {
		Cookie cookie = new Cookie("session", sessionUuid);
		cookie.setMaxAge(7 * 24 * 60 * 60);
		response.addCookie(cookie);
	}
}
