package com.placeteam.backend.server;

import java.util.Enumeration;

import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
	}

	@GetMapping(path = "/favicon.ico")
	public FileSystemResource favicon() {
		return new FileSystemResource("public/favicon.png");
	}


}
