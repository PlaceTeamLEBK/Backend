package com.placeteam.backend.model;

public class Karte  {

	private String[][] canvasOfColour;

	public void setPixel(int x, int y, String colour) {
		canvasOfColour[x][y] = colour;
	}

	public String getPixel(int x, int y) {
		return canvasOfColour[x][y];
	}

	public String[][] getCanvasOfColour() {
		return canvasOfColour;
	}
}
