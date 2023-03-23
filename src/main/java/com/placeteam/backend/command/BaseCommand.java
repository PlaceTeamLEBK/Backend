package com.placeteam.backend.command;

import com.fasterxml.jackson.annotation.JsonGetter;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.placeteam.backend.model.enums.CommandNames;

import jakarta.servlet.http.HttpSession;

public abstract class BaseCommand {

	
	private CommandNames name;
	
	private WebSocketSession session;
	
	public abstract void execute();
	

	protected BaseCommand(CommandNames name) {
		super();
		this.name = name;
	}

	@JsonGetter("command")
	public CommandNames getName() {
		return name;
	}


	@JsonIgnore
	public void setSession(WebSocketSession session) {
		this.session = session;
	}

	public WebSocketSession getSession() {
		return session;
	}

	

	
}
