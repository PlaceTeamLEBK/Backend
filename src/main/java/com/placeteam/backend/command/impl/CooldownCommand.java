package com.placeteam.backend.command.impl;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.placeteam.backend.helper.ServerUtils;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.placeteam.backend.command.BaseCommand;
import com.placeteam.backend.model.Cooldown;
import com.placeteam.backend.model.enums.CommandNames;

import java.io.IOException;

public class CooldownCommand extends BaseCommand{

	public static final CommandNames NAME = CommandNames.COOLDOWN;
	
	private final Cooldown daten;

	@JsonCreator
	public CooldownCommand(@JsonProperty("data") Cooldown daten) {
		super(NAME);
		this.daten = daten;
	}

	@Override
	public void execute() {
		WebSocketSession session = super.getSession();
		try {
		String resultAsJson = ServerUtils.getObjectMapper().writeValueAsString(this);
		if (session.isOpen()) {
			session.sendMessage(new TextMessage(resultAsJson));
		} else {
			System.err.println("Session is closed");
			throw new RuntimeException("Session is closed");
		}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@JsonGetter("data")
	public Cooldown getDaten() {
		return daten;
	}
}
