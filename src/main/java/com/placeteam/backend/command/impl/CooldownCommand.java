package com.placeteam.backend.command.impl;

import com.placeteam.backend.command.BaseCommand;
import com.placeteam.backend.model.Cooldown;
import com.placeteam.backend.model.IDaten;

public class CooldownCommand extends BaseCommand{

	public CooldownCommand(Cooldown daten, long timeStamp) {
		super("cooldown", timeStamp,(IDaten) daten);
	}

	@Override
	public void execute() {
		Cooldown cooldown = (Cooldown) super.getDaten();
		//#TODO
	}
}
