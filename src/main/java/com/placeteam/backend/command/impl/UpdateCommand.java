package com.placeteam.backend.command.impl;

import com.placeteam.backend.command.BaseCommand;
import com.placeteam.backend.model.UpdateDaten;
import com.placeteam.backend.model.enums.CommandNames;

public class UpdateCommand extends BaseCommand{

	public static final CommandNames NAME = CommandNames.UPDATE;

	private UpdateDaten daten;

	public UpdateCommand(UpdateDaten daten, long timeStamp, String key) {
		super(NAME, timeStamp);
		this.daten = daten;
	}

	@Override
	public void execute() {
		//#TODO
	}
}
