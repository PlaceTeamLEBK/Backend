package com.placeteam.backend.command.impl;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.placeteam.backend.command.BaseCommand;
import com.placeteam.backend.helper.ErrorUtils;
import com.placeteam.backend.model.STD_VALUES;
import com.placeteam.backend.model.enums.CommandNames;
import com.placeteam.backend.server.HttpSessionConfig;
import com.placeteam.backend.server.SocketHandler;

import jakarta.servlet.http.HttpSession;


public class InitCommand extends BaseCommand {

	public static final CommandNames NAME = CommandNames.INIT;
	private final String KEY;

	@JsonCreator
	public InitCommand(@JsonProperty("key") String key) {
		super(NAME);

		this.KEY = key;
	}

	@Override
	public void execute() {
		if (KEY == null) {
			ErrorUtils.sendError(getSession(), "No key provided", STD_VALUES.NO_DATA_PROVIDED);
		} else{
			boolean found = searchSession();

			if (!found) {
				ErrorUtils.sendError(getSession(), "Invalid key", STD_VALUES.INVALID_KEY);
			}
			new PaintCommand(super.getSession(), KEY).execute();
		}
	}

	private boolean searchSession() {
		List<HttpSession> activeSessions = HttpSessionConfig.getActiveSessions();
		boolean found = false;
		for (HttpSession activeSession : activeSessions) {
			if (activeSession.getId()!= null && activeSession.getId().equals(KEY)) {
				activeSession.setAttribute("timestamp", System.currentTimeMillis());
				SocketHandler.getInstance().assignedSessions.put(KEY, getSession());
				found = true;
			}
		}
		return found;
	}


}
