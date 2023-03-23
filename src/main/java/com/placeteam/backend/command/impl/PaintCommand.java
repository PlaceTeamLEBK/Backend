package com.placeteam.backend.command.impl;

import java.io.IOException;
import java.sql.SQLException;

import com.placeteam.backend.database.DatabaseException;
import com.placeteam.backend.helper.CommandHelper;
import com.placeteam.backend.helper.ServerUtils;
import com.placeteam.backend.model.Cooldown;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonValue;
import com.placeteam.backend.Bootstrap;
import com.placeteam.backend.command.BaseCommand;
import com.placeteam.backend.model.PaintData;
import com.placeteam.backend.model.enums.CommandNames;

public class PaintCommand extends BaseCommand {
	
	
	public static final CommandNames NAME = CommandNames.PAINT;
	
	private PaintData daten;
	
	protected PaintCommand(  WebSocketSession session) {
		super(NAME);
		super.setSession(session);
	}


	@Override
	public void execute() {
	WebSocketSession session = super.getSession();
	try {
		daten = Bootstrap.getDatabaseConnector().getKarte();
		daten.setCooldown(CommandHelper.getCooldown(getSession()));
		String resultAsJson = ServerUtils.getObjectMapper().writeValueAsString(this);
		if (session.isOpen()) {
			session.sendMessage(new TextMessage(resultAsJson));
		}
	} catch (Exception e) {
		e.printStackTrace();
		throw new RuntimeException(e);
	}


	}


	@JsonGetter("data")
	public PaintData getDaten() {
		return daten;
	}


}
