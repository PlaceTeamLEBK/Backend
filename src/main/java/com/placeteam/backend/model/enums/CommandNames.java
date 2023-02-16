package com.placeteam.backend.model.enums;

public enum CommandNames {
	COOLDOWN,
	INIT,
	SET, UPDATE, PAINT;
	
	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}
}
