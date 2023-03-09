package com.placeteam.backend.command.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.placeteam.backend.Bootstrap;
import com.placeteam.backend.command.BaseCommand;
import com.placeteam.backend.database.DatabaseConnector;
import com.placeteam.backend.database.DatabaseException;
import com.placeteam.backend.helper.CommandHelper;
import com.placeteam.backend.model.Cooldown;
import com.placeteam.backend.model.Pixel;
import com.placeteam.backend.model.STD_VALUES;
import com.placeteam.backend.model.enums.CommandNames;
import com.placeteam.backend.server.HttpSessionConfig;
import com.placeteam.backend.server.SocketHandler;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.socket.WebSocketSession;

import static com.placeteam.backend.helper.CommandHelper.getCooldown;

public class SetCommand extends BaseCommand {

	public static final CommandNames NAME = CommandNames.SET;
	
	private Pixel daten;

	public SetCommand(@JsonProperty("data") Pixel daten) {
		super(NAME);
		this.daten = daten;
	}

	@Override
	public void execute() {
		try {
			DatabaseConnector databaseConnector = Bootstrap.getDatabaseConnector();
			Integer cooldownTime= CommandHelper.getCooldown(getSession());
			Cooldown cooldown = new Cooldown();
			if (cooldownTime != null) {
				if (cooldownTime == 0){
					databaseConnector.setPixel(CommandHelper.getKey(getSession()), daten.getPosition().getX(), daten.getPosition().getY(), daten.getColor());
					new UpdateCommand(daten).execute();
					cooldown.setCooldown(STD_VALUES.COOLDOWN_EXITS);
					resetCooldown();
				} else {
					cooldown.setCooldown(cooldownTime);
				}
			} else {
				cooldown.setCooldown(STD_VALUES.COOLDOWN_NOT_EXITS);
			}
			CooldownCommand cooldownCommand = new CooldownCommand(cooldown);
			cooldownCommand.setSession(getSession());
			cooldownCommand.execute();
		} catch (SQLException | DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void resetCooldown() {
		HttpSession httpSession = CommandHelper.getHttpSession(getSession());
		if (httpSession != null){
			httpSession.setAttribute("timestamp", System.currentTimeMillis());
			httpSession.setAttribute("fresh", false);
		}
	}




}
