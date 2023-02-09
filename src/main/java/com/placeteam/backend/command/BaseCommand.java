package com.placeteam.backend.command;

import com.placeteam.backend.model.IDaten;
import com.placeteam.backend.model.enums.CommandNames;

public abstract class BaseCommand {

	
	private CommandNames name;
	
	private long timeStamp;
	
	private IDaten daten;
	
	
	
	public abstract void execute();
	

	protected BaseCommand(CommandNames name, long timeStamp, IDaten daten) {
		super();
		this.name = name;
		this.timeStamp = timeStamp;
		this.daten = daten;
	}

	public CommandNames getName() {
		return name;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public IDaten getDaten() {
		return daten;
	}

	

	
}
