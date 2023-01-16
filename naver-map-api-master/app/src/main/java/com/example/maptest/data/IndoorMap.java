package com.example.maptest.data;

import com.google.gson.annotations.SerializedName;

public class IndoorMap {
    @SerializedName("building")
    private String building;
    @SerializedName("name")
    private String name;
    @SerializedName("position")
    private String position;
    @SerializedName("floor")
    private String floor;

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }
}
