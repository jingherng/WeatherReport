package com.example.powjh.weatherreport.Model;

/**
 * Created by Pow JH on 05-Sep-18.
 */

public class Wind {

    private double speed, deg;

    public Wind(double speed, double deg){
        this.deg = deg;
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDeg() {
        return deg;
    }

    public void setDeg(double deg) {
        this.deg = deg;
    }
}
