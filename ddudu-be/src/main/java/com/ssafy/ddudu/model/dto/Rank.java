package com.ssafy.ddudu.model.dto;

public class Rank {
    private String userId;
    private String userName; // 추가
    private String bodyPart;
    private int totalWeight;

    // getters and setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() { // 추가
        return userName;
    }

    public void setUserName(String userName) { // 추가
        this.userName = userName;
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
}
