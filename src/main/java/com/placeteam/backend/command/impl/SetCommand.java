package com.placeteam.backend.command.impl;

import java.sql.SQLException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.placeteam.backend.Bootstrap;
import com.placeteam.backend.command.BaseClientCommand;
import com.placeteam.backend.database.DatabaseConnector;
import com.placeteam.backend.database.DatabaseException;
import com.placeteam.backend.model.Cooldown;
import com.placeteam.backend.model.Pixel;
import com.placeteam.backend.model.enums.CommandNames;

public class SetCommand extends BaseClientCommand{

	public static final CommandNames NAME = CommandNames.SET;
	
	private final Pixel daten;

	public SetCommand(@JsonProperty("data") Pixel daten, @JsonProperty("timeStamp") long timeStamp,@JsonProperty("key") String key) {
		super(NAME, timeStamp, key);
		this.daten = daten;
	}

	@Override
	public void execute() {
		DatabaseConnector databaseConnector = Bootstrap.getDatabaseConnector();
		try {
			//get Last set
			///is session open
			databaseConnector.setPixel(getKey(), daten.getPosition().getX(), daten.getPosition().getY(), daten.getColor());
			new UpdateCommand(daten, System.currentTimeMillis()).execute();
			Cooldown cooldown = new Cooldown();
			new CooldownCommand(cooldown, getTimeStamp());
		} catch (SQLException | DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
