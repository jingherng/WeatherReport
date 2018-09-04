package com.example.powjh.weatherreport.Model;

/**
 * Created by Pow JH on 05-Sep-18.
 */

public class Main {
    private double temp, pressure, temp_min, temp_max;
    private int humidity;

    public Main(double temp, double pressure, double temp_max, double temp_min, int humidity){
        this.humidity = humidity;
        this.temp = temp;
        this.pressure = pressure;
        this.temp_max = temp_max;
        this.temp_min = temp_min;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}


