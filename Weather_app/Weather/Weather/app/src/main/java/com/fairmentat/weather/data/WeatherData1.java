package com.fairmentat.weather.data;

public class WeatherData1 {

    private Coord coord;

    private Main main;

    private String name;

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(final Coord coord) {
        this.coord = coord;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(final Main main) {
        this.main = main;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
