package com.gridweaver.dto;

public class GridNodeUpdateDTO {

    private Long nodeId;

    private String status;

    private Double powerOutput;

    private Double powerConsumption;

    private Double powerGeneration;


    public GridNodeUpdateDTO() {
    }


    public GridNodeUpdateDTO(
            Long nodeId,
            String status,
            Double powerOutput,
            Double powerConsumption,
            Double powerGeneration) {

        this.nodeId = nodeId;
        this.status = status;
        this.powerOutput = powerOutput;
        this.powerConsumption = powerConsumption;
        this.powerGeneration = powerGeneration;
    }


    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Double getPowerOutput() {
        return powerOutput;
    }

    public void setPowerOutput(Double powerOutput) {
        this.powerOutput = powerOutput;
    }


    public Double getPowerConsumption() {
        return powerConsumption;
    }

    public void setPowerConsumption(Double powerConsumption) {
        this.powerConsumption = powerConsumption;
    }


    public Double getPowerGeneration() {
        return powerGeneration;
    }

    public void setPowerGeneration(Double powerGeneration) {
        this.powerGeneration = powerGeneration;
    }
}