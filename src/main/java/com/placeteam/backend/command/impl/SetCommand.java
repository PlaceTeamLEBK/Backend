package com.placeteam.backend.command.impl;

import java.sql.SQLException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.placeteam.backend.Bootstrap;
import com.placeteam.backend.command.BaseCommand;
import com.placeteam.backend.database.DatabaseConnector;
import com.placeteam.backend.database.DatabaseException;
import com.placeteam.backend.helper.CommandHelper;
import com.placeteam.backend.helper.ErrorUtils;
import com.placeteam.backend.model.Cooldown;
import com.placeteam.backend.model.Pixel;
import com.placeteam.backend.model.STD_VALUES;
import com.placeteam.backend.model.enums.CommandNames;
import jakarta.servlet.http.HttpSession;

public class SetCommand extends BaseCommand {


	public static final CommandNames NAME = CommandNames.SET;
	
	private final Pixel daten;

    private final String key;


    public SetCommand(@JsonProperty("data") Pixel daten, @JsonProperty("key") String key) {
        super(NAME);
        this.daten = daten;
        this.key = key;
    }

    @Override
    public void execute() {
        if (daten == null || daten.getPosition() == null || daten.getColor() == null || daten.getPosition().getX() == null || daten.getPosition().getY() == null) {
            ErrorUtils.sendNoDataError(getSession());
        } else {
            try {
                Cooldown cooldown = handlediffrentCooldown();
                returnCooldown(cooldown);
            } catch (SQLException | DatabaseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void returnCooldown(Cooldown cooldown) {
        CooldownCommand cooldownCommand = new CooldownCommand(cooldown);
        cooldownCommand.setSession(getSession());
        cooldownCommand.execute();
    }

    private Cooldown handlediffrentCooldown() throws SQLException, DatabaseException {
        int cooldownTime = CommandHelper.getCooldown(key);
        Cooldown cooldown = new Cooldown();
        System.out.printf("cooldownTime %d", cooldownTime);
        if (cooldownTime == 0) {
            System.out.print("JOO BIn hier");
            setData(cooldown);
        } else {
            cooldown.setCooldown(cooldownTime);
        }
        return cooldown;
    }

    private void setData(Cooldown cooldown) throws SQLException, DatabaseException {
        DatabaseConnector databaseConnector = Bootstrap.getDatabaseConnector();
    
        databaseConnector.setPixel(key, daten.getPosition().getX(), daten.getPosition().getY(), daten.getColor());
        new UpdateCommand(daten).execute();
        cooldown.setCooldown(STD_VALUES.COOLDOWN_EXITS);
        resetCooldown();
    }

    private void resetCooldown() {
        HttpSession httpSession = CommandHelper.getHttpSession(key);
        if (httpSession != null) {
            httpSession.setAttribute("lastSet", System.currentTimeMillis());
            httpSession.setAttribute("fresh", false);
        }
    }


}
