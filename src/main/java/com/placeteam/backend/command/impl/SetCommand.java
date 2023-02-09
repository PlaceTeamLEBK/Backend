package com.placeteam.backend.command.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.placeteam.backend.command.BaseClientCommand;
import com.placeteam.backend.model.Pixel;
import com.placeteam.backend.model.enums.CommandNames;

public class SetCommand extends BaseClientCommand{

	public static final CommandNames NAME = CommandNames.SET;
	
	private Pixel daten;

	public SetCommand(@JsonProperty("data") Pixel daten, @JsonProperty("timeStamp") long timeStamp,@JsonProperty("key") String key) {
		super(NAME, timeStamp, key);
		this.daten = daten;
	}

	@Override
	public String execute() {
		return null;
		//#TODO
	}
}
