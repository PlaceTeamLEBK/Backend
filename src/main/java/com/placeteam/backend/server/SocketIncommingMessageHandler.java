package com.placeteam.backend.server;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.json.JsonMapper.Builder;
import com.placeteam.backend.command.BaseCommand;
import com.placeteam.backend.command.impl.SetCommand;
import com.placeteam.backend.helper.CommandHelper;

public class SocketIncommingMessageHandler implements SocketMessaging {
	private static ObjectMapper getObjectMapper() {
		Builder builder = JsonMapper.builder();
		builder = builder.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true);
		builder = builder.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		JsonMapper buildMapper = builder.build();
		return buildMapper;
	}

	@Override
	public void incommingMessage(String message) {
		try {
			ObjectMapper mapper = getObjectMapper();
			CommandModel commandName = mapper.readValue(message, CommandModel.class);
			Class<?> commandClass = CommandHelper.getCommandByName(commandName.getCommand());
			BaseCommand command = (BaseCommand) mapper.readValue(message, commandClass);
			command.execute();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	private static class CommandModel {
		private String command;

		public String getCommand() {
			return command;
		}

		@JsonSetter("command")
		public void setCommand(String command) {
			this.command = command;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return getCommand();
		}

	}
}
