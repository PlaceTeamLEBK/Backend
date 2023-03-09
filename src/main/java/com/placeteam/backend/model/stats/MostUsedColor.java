package com.placeteam.backend.model.stats;

public class MostUsedColor {
    private String color;
    private int count;

    public MostUsedColor(String color, int count) {
        this.color = color;
        this.count = count;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
