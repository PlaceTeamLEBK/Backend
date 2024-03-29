package com.placeteam.backend.helper;

import java.util.List;

import com.placeteam.backend.command.impl.CooldownCommand;
import com.placeteam.backend.command.impl.InitCommand;
import com.placeteam.backend.command.impl.SetCommand;
import com.placeteam.backend.model.STD_VALUES;
import com.placeteam.backend.model.enums.CommandNames;
import com.placeteam.backend.server.HttpSessionConfig;

import jakarta.servlet.http.HttpSession;

public class CommandHelper {

	public static Class<?> getCommandByName(String name) {
		if (name != null) {
			if (name.equals(CommandNames.INIT.toString())) {
				return InitCommand.class;
			}
			if (name.equals(CommandNames.SET.toString())) {
				return SetCommand.class;
			}
			if (name.equals(CommandNames.COOLDOWN.toString())) {
				return CooldownCommand.class;
			}
		}
		return null;
	}

	public static int getCooldown(String key) {

		HttpSession httpSession = getHttpSession(key);
		if(httpSession == null) {
			return STD_VALUES.COOLDOWN_NOT_EXITS;
		}
		Long timestamp = (Long) httpSession.getAttribute("lastSet");
		if (timestamp == null) {
			timestamp = System.currentTimeMillis();
			httpSession.setAttribute("lastSet", timestamp);
		}

		long currentTimeMillis = System.currentTimeMillis();
		long diff = (currentTimeMillis - timestamp) / 1000;
		int cooldown = ((boolean) httpSession.getAttribute("fresh")) ? STD_VALUES.COOLDOWN_NOT_EXITS : STD_VALUES.COOLDOWN_EXITS;
		int result = cooldown - (int) diff;
		System.out.println("Cooldown: " + result + " is fresh: " + httpSession.getAttribute("fresh") + " diff: " + diff+ " cooldown: " + cooldown);
		return result > 0 ? result : 0;
	}

	public static HttpSession getHttpSession(String key) {
		List<HttpSession> activeSessions = HttpSessionConfig.getActiveSessions();
		for (HttpSession httpSession : activeSessions) {
			if (httpSession.getId().equals(key)) {
				return httpSession;
			}
		}
		return null;
	}
}
