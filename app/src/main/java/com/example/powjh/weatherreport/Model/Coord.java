package com.example.powjh.weatherreport.Model;

/**
 * Created by Pow JH on 05-Sep-18.
 */

public class Coord {

    private double lat,lon;

    public Coord(double lat, double lon){
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
