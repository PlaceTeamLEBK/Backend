package com.placeteam.backend.model;

public class InitReturn {

	private PaintData karte;
	
	private Cooldown cooldown;

	public Cooldown getCooldown() {
		return cooldown;
	}

	public void setCooldown(Cooldown cooldown) {
		this.cooldown = cooldown;
	}

	public PaintData getKarte() {
		return karte;
	}

	public void setKarte(PaintData karte) {
		this.karte = karte;
	}
}
