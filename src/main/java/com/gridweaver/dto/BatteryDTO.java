package com.gridweaver.dto;

public class BatteryDTO {

    private Long id;
    private String batteryName;
    
    private String location;
    private double power;
    private String state;

    public BatteryDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBatteryName() {
        return batteryName;
    }

    public void setBatteryName(String batteryName) {
        this.batteryName = batteryName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}