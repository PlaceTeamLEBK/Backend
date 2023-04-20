package com.placeteam.backend.model;

import com.fasterxml.jackson.annotation.JsonSetter;

public class Pixel  {

	private String color;

	private Position position;


	public String getColor() {
		return color;
	}

	public Position getPosition() {
		return position;
	}

	@JsonSetter("color")
	public void setColor(String color) {
		this.color = color;
	}

	@JsonSetter("position")
	public void setPosition(Position position) {
		this.position = position;
	}

}
