package com.ssafy.ddudu.model.dto;

public class Rank {
    private String bodyPart;
    private int totalWeight;

    public Rank() {}

    public Rank(String bodyPart, int totalWeight) {
        this.bodyPart = bodyPart;
        this.totalWeight = totalWeight;
    }

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

    @Override
    public String toString() {
        return "Rank [bodyPart=" + bodyPart + ", totalWeight=" + totalWeight + "]";
    }
}
