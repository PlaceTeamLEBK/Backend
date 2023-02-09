package com.placeteam.backend.command.impl;

import com.placeteam.backend.command.BaseCommand;
import com.placeteam.backend.model.IDaten;
import com.placeteam.backend.model.UpdateDaten;
import com.placeteam.backend.model.enums.CommandNames;

public class UpdateCommand extends BaseCommand{

	public static final CommandNames NAME = CommandNames.UPDATE;

	public UpdateCommand(UpdateDaten daten, long timeStamp, String key) {
		super(NAME, timeStamp, (IDaten) daten);
	}

	@Override
	public void execute() {
		UpdateDaten daten = (UpdateDaten) super.getDaten();
		//#TODO
	}
}
