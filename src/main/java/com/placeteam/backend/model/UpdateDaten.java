package com.placeteam.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class UpdateDaten  {

	
	private Pixel[] pixels;

	public Pixel[] getPixels() {
		return pixels;
	}

	@JsonSetter("pixels")
	public void setPixels(Pixel[] pixels) {
		this.pixels = pixels;
	}
}
