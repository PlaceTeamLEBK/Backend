package com.placeteam.backend.server;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SessionHolder {
	private static List<Session> sessionIds = new ArrayList<Session>();

	public static String createSessionId() {
		Session session;
		String sessionUuid;
		do {
			session = createSession();
			sessionUuid = session.getUuid();
		} while (contains(sessionUuid));
		sessionIds.add(session);
		return sessionUuid;
	}

	public static Session getSession(String uuid) {
		for (int i = 0; i < sessionIds.size(); i++) {
			Session session = sessionIds.get(i);
			if (!session.equals(uuid)) {
				continue;
			}
			return session;
		}
		return null;
	}

	public static boolean contains(String uuid) {
		return (getSession(uuid) != null);
	}

	private static Session createSession() {
		UUID uuid = UUID.randomUUID();
		Session session = new Session(uuid);
		return session;
	}
}
