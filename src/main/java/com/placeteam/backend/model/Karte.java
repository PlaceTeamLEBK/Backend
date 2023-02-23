package com.placeteam.backend.model;

public class Karte  {

	private String[][] canvasOfColour;

	public void setPixel(int x, int y, String colour) {
		canvasOfColour[x][y] = colour;
	}

	public String getPixel(int x, int y) {
		return canvasOfColour[x][y];
	}

	public Karte() {
		int size = STD_VALUES.CANVAS_WIDTH;
		this.canvasOfColour = new String[size][size];
	}

	public String[][] getCanvasOfColour() {
		return canvasOfColour;
	}
}
