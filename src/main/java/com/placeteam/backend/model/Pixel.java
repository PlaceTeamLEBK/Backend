package com.placeteam.backend.model;

import com.fasterxml.jackson.annotation.JsonSetter;

public class Pixel  {
	
	private Position position;
	
	private String farbe;


	public String getFarbe() {
		return farbe;
	}

	public Position getPosition() {
		return position;
	}

	@JsonSetter("position")
	public void setPosition(Position position) {
		this.position = position;
	}

	@JsonSetter("color")
	public void setFarbe(String farbe) {
		this.farbe = farbe;
	}

}
