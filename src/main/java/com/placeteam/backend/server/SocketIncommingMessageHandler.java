package com.placeteam.backend.server;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.json.JsonMapper.Builder;

public class SocketIncommingMessageHandler implements SocketMessaging {
	private static ObjectMapper getObjectMapper() {
		Builder builder = JsonMapper.builder();
		Builder configuredBuilder = builder.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true);
		JsonMapper buildMapper = configuredBuilder.build();
		return buildMapper;
	}

	@Override
	public void incommingMessage(String messgae) {
		try {
			ObjectMapper mapper = getObjectMapper();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
