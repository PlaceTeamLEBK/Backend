package com.placeteam.backend.command.impl;

import com.placeteam.backend.command.BaseCommand;
import com.placeteam.backend.model.Cooldown;
import com.placeteam.backend.model.enums.CommandNames;

public class CooldownCommand extends BaseCommand{

	public static final CommandNames NAME = CommandNames.COOLDOWN;
	
	private Cooldown daten;

	public CooldownCommand(Cooldown daten, long timeStamp) {
		super(NAME, timeStamp);
		this.daten = daten;
	}

	@Override
	public void execute() {
		//#TODO
	}
}
