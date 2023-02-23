package com.placeteam.backend.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CommandNames {
	COOLDOWN, INIT, SET, UPDATE, PAINT;

	@Override @JsonValue
	public String toString() {
		return super.toString().toLowerCase();
	}
}
