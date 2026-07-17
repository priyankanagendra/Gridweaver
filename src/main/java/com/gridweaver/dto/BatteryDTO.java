package com.gridweaver.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
public class BatteryDTO {

    private Long id;
    @NotBlank(message = "Battery name cannot be blank")
    private String batteryName;

    @NotBlank(message = "Battery type cannot be blank")
    private String batteryType;

    @Positive(message = "Capacity must be greater than zero")
    private double capacity;

    @Positive(message = "Voltage must be greater than zero")
    private double voltage;
    
    private Long customerId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

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

    public String getBatteryType() {
        return batteryType;
    }

    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getVoltage() {
        return voltage;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }
}