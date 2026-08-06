package com.gridweaver.dto;

public class BalanceRequest {

    private Long sourceBatteryId;
    private Long targetBatteryId;
    private double transferPower;

    public BalanceRequest() {
    }

    public Long getSourceBatteryId() {
        return sourceBatteryId;
    }

    public void setSourceBatteryId(Long sourceBatteryId) {
        this.sourceBatteryId = sourceBatteryId;
    }

    public Long getTargetBatteryId() {
        return targetBatteryId;
    }

    public void setTargetBatteryId(Long targetBatteryId) {
        this.targetBatteryId = targetBatteryId;
    }

    public double getTransferPower() {
        return transferPower;
    }

    public void setTransferPower(double transferPower) {
        this.transferPower = transferPower;
    }
}