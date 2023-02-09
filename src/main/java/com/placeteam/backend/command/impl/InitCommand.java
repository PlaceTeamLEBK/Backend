package com.placeteam.backend.command.impl;

import com.placeteam.backend.command.BaseClientCommand;
import com.placeteam.backend.command.BaseCommand;
import com.placeteam.backend.model.EmptyModel;
import com.placeteam.backend.model.IDaten;
import com.placeteam.backend.model.Pixel;
import com.placeteam.backend.model.UpdateDaten;
import com.placeteam.backend.model.enums.CommandNames;

public class InitCommand extends BaseCommand{

	public static final CommandNames NAME = CommandNames.INIT;

	public InitCommand(long timeStamp, String key) {
		super(NAME, timeStamp, (IDaten) new EmptyModel());
	}

	@Override
	public void execute() {
		//#TODO
	}
}
