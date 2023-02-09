package com.placeteam.backend.command.impl;

import com.placeteam.backend.command.BaseCommand;
import com.placeteam.backend.model.Cooldown;
import com.placeteam.backend.model.IDaten;
import com.placeteam.backend.model.enums.CommandNames;

public class CooldownCommand extends BaseCommand{

	public static final CommandNames NAME = CommandNames.COOLDOWN;

	public CooldownCommand(Cooldown daten, long timeStamp) {
		super(NAME, timeStamp,(IDaten) daten);
	}

	@Override
	public void execute() {
		Cooldown cooldown = (Cooldown) super.getDaten();
		//#TODO
	}
}
