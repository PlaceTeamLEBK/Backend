package com.placeteam.backend.model;

public class InitReturn {

	private Cooldown cooldown;

	private PaintData karte;

	public Cooldown getCooldown() {
		return cooldown;
	}

	public PaintData getKarte() {
		return karte;
	}

	public void setCooldown(Cooldown cooldown) {
		this.cooldown = cooldown;
	}

	public void setKarte(PaintData karte) {
		this.karte = karte;
	}
}
