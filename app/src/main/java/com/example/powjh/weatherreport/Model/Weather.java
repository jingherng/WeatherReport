package com.example.powjh.weatherreport.Model;

/**
 * Created by Pow JH on 05-Sep-18.
 */

public class Weather {
    private int id;
    private String main, description, icon;

    public Weather(int id, String main, String description, String icon){
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }


    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    public String getMain() {
        return main;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setMain(String main) {
        this.main = main;
    }
}
