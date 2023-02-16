package com.placeteam.backend.model;

public class Karte  {

	private String[][] canvasOfColour;

	public void setPixel(int x, int y, String colour) {
		canvasOfColour[x][y] = colour;
	}
	
}
