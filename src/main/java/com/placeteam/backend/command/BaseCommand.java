package com.placeteam.backend.command;

import com.placeteam.backend.model.enums.CommandNames;

public abstract class BaseCommand {

	
	private CommandNames name;
	
	private long timeStamp;
	
	
	
	
	public abstract String execute();
	

	protected BaseCommand(CommandNames name, long timeStamp) {
		super();
		this.name = name;
		this.timeStamp = timeStamp;
	}

	public CommandNames getName() {
		return name;
	}

	public long getTimeStamp() {
		return timeStamp;
	}


	

	
}
