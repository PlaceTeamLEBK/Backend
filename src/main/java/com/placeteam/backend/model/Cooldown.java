package com.placeteam.backend.model;

import com.fasterxml.jackson.annotation.JsonSetter;

public class Cooldown  {

	private int cooldown;

	public int getCooldown() {
		return cooldown;
	}

	@JsonSetter("seconds")
	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

}
