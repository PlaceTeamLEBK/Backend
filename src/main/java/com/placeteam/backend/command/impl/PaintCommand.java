package com.placeteam.backend.command.impl;

import java.io.IOException;
import java.sql.SQLException;

import com.placeteam.backend.database.DatabaseException;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.placeteam.backend.Bootstrap;
import com.placeteam.backend.command.BaseCommand;
import com.placeteam.backend.model.Karte;
import com.placeteam.backend.model.enums.CommandNames;
import com.placeteam.backend.server.SocketIncommingMessageHandler;

public class PaintCommand extends BaseCommand {
	
	
	public static final CommandNames NAME = CommandNames.PAINT;
	
	private Karte daten;
	
	protected PaintCommand( long timeStamp, WebSocketSession session) {
		super(NAME, timeStamp);
		super.setSession(session);
	}


	@Override
	public void execute() {
	WebSocketSession session = super.getSession();
	try {
		daten = Bootstrap.getDatabaseConnector().getKarte();
		String resultAsJson = SocketIncommingMessageHandler.getObjectMapper().writeValueAsString(this);
		if (session.isOpen()) {
			session.sendMessage(new TextMessage(resultAsJson));
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (DatabaseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		throw new RuntimeException(e);
	}


	}


}
