package com.placeteam.backend.command.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.placeteam.backend.command.BaseCommand;
import com.placeteam.backend.model.Cooldown;
import com.placeteam.backend.model.enums.CommandNames;

public class CooldownCommand extends BaseCommand{

	public static final CommandNames NAME = CommandNames.COOLDOWN;
	
	private Cooldown daten;

	@JsonCreator
	public CooldownCommand(@JsonProperty("data") Cooldown daten,@JsonProperty("timeStamp") long timeStamp) {
		super(NAME, timeStamp);
		this.daten = daten;
	}

	@Override
	public String execute() {
		return null;
		//#TODO
	}
}
