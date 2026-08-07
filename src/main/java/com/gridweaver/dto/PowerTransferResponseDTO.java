package com.gridweaver.dto;

public class PowerTransferResponseDTO {

    private String sourceZone;
    private String targetZone;

    private Double sourceSurplus;
    private Double targetDeficit;

    private Double transferredPower;

    private String status;


    public PowerTransferResponseDTO() {
    }


    public PowerTransferResponseDTO(
            String sourceZone,
            String targetZone,
            Double sourceSurplus,
            Double targetDeficit,
            Double transferredPower,
            String status) {

        this.sourceZone = sourceZone;
        this.targetZone = targetZone;
        this.sourceSurplus = sourceSurplus;
        this.targetDeficit = targetDeficit;
        this.transferredPower = transferredPower;
        this.status = status;
    }


    public String getSourceZone() {
        return sourceZone;
    }

    public void setSourceZone(String sourceZone) {
        this.sourceZone = sourceZone;
    }


    public String getTargetZone() {
        return targetZone;
    }

    public void setTargetZone(String targetZone) {
        this.targetZone = targetZone;
    }


    public Double getSourceSurplus() {
        return sourceSurplus;
    }

    public void setSourceSurplus(Double sourceSurplus) {
        this.sourceSurplus = sourceSurplus;
    }


    public Double getTargetDeficit() {
        return targetDeficit;
    }

    public void setTargetDeficit(Double targetDeficit) {
        this.targetDeficit = targetDeficit;
    }


    public Double getTransferredPower() {
        return transferredPower;
    }

    public void setTransferredPower(Double transferredPower) {
        this.transferredPower = transferredPower;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}