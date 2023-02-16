package com.placeteam.backend.command.impl;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.placeteam.backend.command.BaseCommand;
import com.placeteam.backend.model.Pixel;
import com.placeteam.backend.model.enums.CommandNames;
import com.placeteam.backend.server.SocketHandler;
import com.placeteam.backend.server.SocketIncommingMessageHandler;

public class UpdateCommand extends BaseCommand{

	public static final CommandNames NAME = CommandNames.UPDATE;

	private Pixel daten;

	public UpdateCommand(@JsonProperty("data") Pixel daten,@JsonProperty("timeStamp") long timeStamp) {
		super(NAME, timeStamp);
		this.daten = daten;
	}

	@Override
	public void execute() {
		try {
			ObjectMapper objectMapper = SocketIncommingMessageHandler.getObjectMapper();
			String jsonResult = objectMapper.writeValueAsString(this);
			SocketHandler.getInstance().sendMessage(jsonResult);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
