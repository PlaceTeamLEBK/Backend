package com.placeteam.backend.helper;

import com.placeteam.backend.command.impl.CooldownCommand;
import com.placeteam.backend.command.impl.InitCommand;
import com.placeteam.backend.command.impl.SetCommand;
import com.placeteam.backend.command.impl.UpdateCommand;
import com.placeteam.backend.model.enums.CommandNames;

public class CommandHelper {

	public static Class<?> getCommandByName(String name) {
		if (name != null) {
			if (name.equals(CommandNames.COOLDOWN.toString())) {
				return CooldownCommand.class;
			}
			if (name.equals(CommandNames.INIT.toString())) {
				return InitCommand.class;
			}
			if (name.equals(CommandNames.SET.toString())) {
				return SetCommand.class;
			}
			if (name.equals(CommandNames.UPDATE.toString())) {
				return UpdateCommand.class;
			}
		}
		return null;
	}
}
