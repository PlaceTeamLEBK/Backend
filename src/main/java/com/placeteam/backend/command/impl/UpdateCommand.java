package com.placeteam.backend.command.impl;

import com.placeteam.backend.command.BaseClientCommand;
import com.placeteam.backend.command.BaseCommand;
import com.placeteam.backend.model.IDaten;
import com.placeteam.backend.model.Pixel;
import com.placeteam.backend.model.UpdateDaten;

public class UpdateCommand extends BaseCommand{

	public UpdateCommand(UpdateDaten daten, long timeStamp, String key) {
		super("update", timeStamp, (IDaten) daten);
	}

	@Override
	public void execute() {
		UpdateDaten daten = (UpdateDaten) super.getDaten();
		//#TODO
	}
}
