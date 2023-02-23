package com.placeteam.backend.command;

import org.springframework.web.socket.WebSocketSession;

import com.placeteam.backend.model.enums.CommandNames;

import jakarta.servlet.http.HttpSession;

public abstract class BaseCommand {

	
	private CommandNames name;
	
	private long timeStamp;
	
	private WebSocketSession session;
	
	public abstract void execute();
	

	protected BaseCommand(CommandNames name, long timeStamp) {
		super();
		this.name = name;
		this.timeStamp = timeStamp;
	}

	public CommandNames getName() {
		return name;
	}

	public long getTimeStamp() {
		return timeStamp;
	}


	public void setSession(WebSocketSession session) {
		this.session = session;
	}

	public WebSocketSession getSession() {
		return session;
	}

	

	
}
