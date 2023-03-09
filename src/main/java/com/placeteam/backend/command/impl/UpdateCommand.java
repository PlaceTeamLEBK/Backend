package com.placeteam.backend.command.impl;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.placeteam.backend.command.BaseCommand;
import com.placeteam.backend.model.Pixel;
import com.placeteam.backend.model.enums.CommandNames;
import com.placeteam.backend.helper.ServerUtils;
import com.placeteam.backend.server.SocketHandler;

public class UpdateCommand extends BaseCommand{

	public static final CommandNames NAME = CommandNames.UPDATE;

	private Pixel daten;

	public UpdateCommand(@JsonProperty("data") Pixel daten) {
		super(NAME);
		this.daten = daten;
	}

	@Override
	public void execute() {
		try {
			ObjectMapper objectMapper = ServerUtils.getObjectMapper();
			String jsonResult = objectMapper.writeValueAsString(this);
			SocketHandler.getInstance().sendMessage(jsonResult);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@JsonGetter("data")
	public Pixel getDaten() {
		return daten;
	}
}
