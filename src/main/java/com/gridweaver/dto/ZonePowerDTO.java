package com.gridweaver.dto;

public class ZonePowerDTO {

    private String zoneName;
    private Double powerGeneration;
    private Double powerConsumption;

    public ZonePowerDTO() {
    }

    public ZonePowerDTO(
            String zoneName,
            Double powerGeneration,
            Double powerConsumption) {

        this.zoneName = zoneName;
        this.powerGeneration = powerGeneration;
        this.powerConsumption = powerConsumption;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public Double getPowerGeneration() {
        return powerGeneration;
    }

    public void setPowerGeneration(Double powerGeneration) {
        this.powerGeneration = powerGeneration;
    }

    public Double getPowerConsumption() {
        return powerConsumption;
    }

    public void setPowerConsumption(Double powerConsumption) {
        this.powerConsumption = powerConsumption;
    }

    public Double getNetPower() {
        return powerGeneration - powerConsumption;
    }
}