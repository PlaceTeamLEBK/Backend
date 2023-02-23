package com.placeteam.backend.model;

public class InitReturn {

	private Karte karte;
	
	private Cooldown cooldown;

	public Cooldown getCooldown() {
		return cooldown;
	}

	public void setCooldown(Cooldown cooldown) {
		this.cooldown = cooldown;
	}

	public Karte getKarte() {
		return karte;
	}

	public void setKarte(Karte karte) {
		this.karte = karte;
	}
}
