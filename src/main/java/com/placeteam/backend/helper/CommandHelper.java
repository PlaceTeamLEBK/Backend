package com.placeteam.backend.helper;

import com.placeteam.backend.command.impl.CooldownCommand;
import com.placeteam.backend.command.impl.InitCommand;
import com.placeteam.backend.command.impl.SetCommand;
import com.placeteam.backend.command.impl.UpdateCommand;
import com.placeteam.backend.model.STD_VALUES;
import com.placeteam.backend.model.enums.CommandNames;
import com.placeteam.backend.server.HttpSessionConfig;
import com.placeteam.backend.server.SocketHandler;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;
import java.util.Map;

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

	public static HttpSession getHttpSession(WebSocketSession session) {
		List<HttpSession> activeSessions = HttpSessionConfig.getActiveSessions();
		for (HttpSession httpSession : activeSessions) {
			if (httpSession.getId().equals(getKey(session))) {
				return httpSession;
			}
		}
		return null;
	}

	public static int getCooldown(WebSocketSession session) {

		HttpSession httpSession = getHttpSession(session);
		Long timestamp = (Long) httpSession.getAttribute("timestamp");
		long currentTimeMillis = System.currentTimeMillis();
		long diff = (currentTimeMillis - timestamp) / 1000;
		int cooldown = ((boolean) httpSession.getAttribute("fresh")) ? STD_VALUES.COOLDOWN_NOT_EXITS : STD_VALUES.COOLDOWN_EXITS;
		int result = cooldown - (int) diff;
		System.out.println("Cooldown: " + result + " is fresh: " + httpSession.getAttribute("fresh") + " diff: " + diff+ " cooldown: " + cooldown);
		return result > 0 ? result : 0;
	}

	public static String getKey(WebSocketSession session) {
		Map<String, WebSocketSession> assignedSessions = SocketHandler.getInstance().assignedSessions;
		for (String key : assignedSessions.keySet()) {
			if (assignedSessions.get(key).equals(session)) {
				return key;
			}
		}
		return null;
	}
}
