package com.placeteam.backend.command.impl;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.placeteam.backend.command.BaseClientCommand;
import com.placeteam.backend.model.enums.CommandNames;

public class InitCommand extends BaseClientCommand{

	public static final CommandNames NAME = CommandNames.INIT;

	@JsonCreator
	public InitCommand(@JsonProperty("timeStamp") long timeStamp,@JsonProperty("key") String key) {
		super(NAME, timeStamp, key);
	}
	
	@Override
	public void execute() {
		new PaintCommand(getTimeStamp(), super.getSession()).execute();
	}
	
	
}
