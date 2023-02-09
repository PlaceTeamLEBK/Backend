package com.placeteam.backend.command.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.placeteam.backend.command.BaseCommand;
import com.placeteam.backend.model.UpdateDaten;
import com.placeteam.backend.model.enums.CommandNames;

public class UpdateCommand extends BaseCommand{

	public static final CommandNames NAME = CommandNames.UPDATE;

	private UpdateDaten daten;

	public UpdateCommand(@JsonProperty("data") UpdateDaten daten,@JsonProperty("timeStamp") long timeStamp) {
		super(NAME, timeStamp);
		this.daten = daten;
	}

	@Override
	public String execute() {
		return null;
		//#TODO
	}
}
