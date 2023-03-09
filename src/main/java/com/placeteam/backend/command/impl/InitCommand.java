package com.placeteam.backend.command.impl;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.placeteam.backend.command.BaseCommand;
import com.placeteam.backend.model.Cooldown;
import com.placeteam.backend.model.STD_VALUES;
import com.placeteam.backend.model.enums.CommandNames;
import com.placeteam.backend.server.HttpSessionConfig;
import com.placeteam.backend.server.SocketHandler;
import jakarta.servlet.http.HttpSession;

import java.util.List;


public class InitCommand extends BaseCommand {

	public static final CommandNames NAME = CommandNames.INIT;
	private final String key;

	@JsonCreator
	public InitCommand(@JsonProperty("key") String key) {
		super(NAME);

		this.key = key;
	}
	
	@Override
	public void execute() {
		List<HttpSession> activeSessions = HttpSessionConfig.getActiveSessions();
		for (HttpSession activeSession : activeSessions) {
			if (activeSession.getId().equals(key)) {
				activeSession.setAttribute("timestamp", System.currentTimeMillis());
				SocketHandler.getInstance().assignedSessions.put(key, getSession());
			}
		}

		new PaintCommand( super.getSession()).execute();
	}
	
	
}
