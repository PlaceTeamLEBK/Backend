package com.placeteam.backend.command;

import com.placeteam.backend.model.IDaten;

public abstract class BaseCommand {

	
	private String name;
	
	private long timeStamp;
	
	private IDaten daten;
	
	
	
	public abstract void execute();
	

	protected BaseCommand(String name, long timeStamp, IDaten daten) {
		super();
		this.name = name;
		this.timeStamp = timeStamp;
		this.daten = daten;
	}

	public String getName() {
		return name;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public IDaten getDaten() {
		return daten;
	}

	

	
}
