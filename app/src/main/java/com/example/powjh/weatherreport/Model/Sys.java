package com.example.powjh.weatherreport.Model;

/**
 * Created by Pow JH on 05-Sep-18.
 */

public class Sys {
    private int type, id;
    private double message, sunrise, sunset;
    private String ctry;

    public Sys(int type, int id, double message, double sunrise, double sunset, String ctry) {
        this.type = type;
        this.id = id;
        this.message = message;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.ctry = ctry;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public double getSunrise() {
        return sunrise;
    }

    public void setSunrise(double sunrise) {
        this.sunrise = sunrise;
    }

    public double getSunset() {
        return sunset;
    }

    public void setSunset(double sunset) {
        this.sunset = sunset;
    }

    public String getCtry() {
        return ctry;
    }

    public void setCtry(String ctry) {
        this.ctry = ctry;
    }
}
