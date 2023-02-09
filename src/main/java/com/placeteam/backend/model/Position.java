package com.placeteam.backend.model;

import com.fasterxml.jackson.annotation.JsonSetter;

public class Position  {
	
	private int x;
	
	private int y;

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@JsonSetter("x")
	public void setX(int x) {
		this.x = x;
	}

	@JsonSetter("y")
	public void setY(int y) {
		this.y = y;
	}

}
