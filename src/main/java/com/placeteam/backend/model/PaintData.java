package com.placeteam.backend.model;

import com.fasterxml.jackson.annotation.JsonGetter;

public class PaintData  {
	
	private int cooldown;

	private String[][] canvasOfColour;

	public void setPixel(int x, int y, String colour) {
		canvasOfColour[x][y] = colour;
	}

	public String getPixel(int x, int y) {
		return canvasOfColour[x][y];
	}

	public PaintData() {
		int size = STD_VALUES.CANVAS_WIDTH;
		this.canvasOfColour = new String[size][size];
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	@JsonGetter("pixels")
	public String[][] getCanvasOfColour() {
		return canvasOfColour;
	}
	
	@JsonGetter("cooldown")
	public int getCooldown() {
		return cooldown;
	}
}
