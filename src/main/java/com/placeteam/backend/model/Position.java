package com.placeteam.backend.model;

import com.fasterxml.jackson.annotation.JsonSetter;

public class Position  {

	private Integer x;

	private Integer y;

	public Integer getX() {
		return x;
	}

	public Integer getY() {
		return y;
	}

	@JsonSetter("x")
	public void setX(Integer x) {
		this.x = x;
	}

	@JsonSetter("y")
	public void setY(Integer y) {
		this.y = y;
	}

}
