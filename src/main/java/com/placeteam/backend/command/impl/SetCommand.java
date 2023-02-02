package com.placeteam.backend.command.impl;

import com.placeteam.backend.command.BaseClientCommand;
import com.placeteam.backend.model.IDaten;
import com.placeteam.backend.model.Pixel;

public class SetCommand extends BaseClientCommand{

	public SetCommand(Pixel daten, long timeStamp, String key) {
		super("Set", (IDaten) daten, timeStamp, key);
	}

	@Override
	public void execute() {
		Pixel pixel = (Pixel) super.getDaten();
		//#TODO
	}
}
