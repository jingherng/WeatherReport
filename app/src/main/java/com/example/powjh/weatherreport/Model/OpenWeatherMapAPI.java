package com.example.powjh.weatherreport.Model;

import java.util.List;

/**
 * Created by Pow JH on 05-Sep-18.
 */

public class OpenWeatherMapAPI {
    private Clouds clouds;
    private Coord coord;
    private Main main;
    private List<Weather> weatherList;
    private String base;
    private Rain rain;
    private int dt;
    private Sys sys;
    private String name;
    private int cod;

    public OpenWeatherMapAPI(){

    }

    public OpenWeatherMapAPI(Clouds clouds, Coord coord, Main main, List<Weather> weatherList, String base, Rain rain, int dt, Sys sys, String name, int cod) {
        this.clouds = clouds;
        this.coord = coord;
        this.main = main;
        this.weatherList = weatherList;
        this.base = base;
        this.rain = rain;
        this.dt = dt;
        this.sys = sys;
        this.name = name;
        this.cod = cod;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public List<Weather> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
}
