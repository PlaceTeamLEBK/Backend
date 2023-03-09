package com.placeteam.backend.command;

import com.placeteam.backend.model.enums.CommandNames;

public abstract class BaseClientCommand extends BaseCommand {
	private final String key;

	protected BaseClientCommand(CommandNames name,  long timeStamp, String key) {
		super(name, timeStamp);
		this.key = key;
	}

	public String getKey() {
		return key;
	}
}
