package com.placeteam.backend.command;

import com.placeteam.backend.model.IDaten;

public abstract class BaseClientCommand extends BaseCommand {
	private String key;

	protected BaseClientCommand(String name, IDaten daten, long timeStamp, String key) {
		super(name, timeStamp, daten);
		this.key = key;
	}

	public String getKey() {
		return key;
	}
}
