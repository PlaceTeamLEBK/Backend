package com.placeteam.backend.helper;

import com.placeteam.backend.command.BaseCommand;
import com.placeteam.backend.command.impl.CooldownCommand;
import com.placeteam.backend.model.Cooldown;

public class CommandHelper {

	
	public static BaseCommand getCommandByName(String name) {
		return name.equals(CooldownCommand.NAME) ? CooldownCommand;
		
	}
}
