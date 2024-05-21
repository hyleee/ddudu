package com.ssafy.ddudu.model.dto;

public class WeeklyStat {
    private String bodyPart;
    private int totalWeight;

    // Getters and Setters
    public String getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(String bodyPart) {
        this.bodyPart = bodyPart;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }
}
