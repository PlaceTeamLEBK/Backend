package com.placeteam.backend.server;

import java.util.UUID;

public class Session {
	private final UUID uuid;
	private final long created;

	public Session(UUID uuid) {
		this.uuid = uuid;
		this.created = System.currentTimeMillis();
	}

	public boolean isSessionXOld(int minutes) {
		long currentMillis = System.currentTimeMillis();
		long diff = currentMillis - created;
		int milliCalc = 1000 * 60 * minutes;
		if (diff > milliCalc) {
			return true;
		}
		return false;
	}
	
	public String getUuid() {
		String uuidString = uuid.toString();
		return uuidString;
	}
	
	public boolean equals(Session session) {
		String sessionUuid = session.getUuid();
		boolean out = sessionUuid.equals(getUuid());
		return out;
	}
	
	public boolean equals(String sessionUuid) {
		boolean out = sessionUuid.equals(getUuid());
		return out;
	}

}
