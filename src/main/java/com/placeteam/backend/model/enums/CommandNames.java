package com.placeteam.backend.model.enums;

public enum CommandNames {
	COOLDOWN,
	INIT,
	SET, UPDATE;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString().toLowerCase();
	}
}
