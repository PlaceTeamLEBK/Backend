package com.placeteam.backend.command;

import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.placeteam.backend.model.enums.CommandNames;

public abstract class BaseCommand {


	private CommandNames name;

	private WebSocketSession session;

	protected BaseCommand(CommandNames name) {
		super();
		this.name = name;
	}


	public abstract void execute();

	@JsonGetter("command")
	public CommandNames getName() {
		return name;
	}

	public WebSocketSession getSession() {
		return session;
	}

	@JsonIgnore
	public void setSession(WebSocketSession session) {
		this.session = session;
	}




}
