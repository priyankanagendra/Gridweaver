package com.gridweaver.dto;

public class GridNodeUpdateDTO {

    private Long nodeId;
    private String status;
    private Double powerOutput;

    public GridNodeUpdateDTO() {
    }

    public GridNodeUpdateDTO(
            Long nodeId,
            String status,
            Double powerOutput) {

        this.nodeId = nodeId;
        this.status = status;
        this.powerOutput = powerOutput;
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
}