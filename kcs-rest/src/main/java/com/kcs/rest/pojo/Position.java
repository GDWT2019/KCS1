package com.kcs.rest.pojo;

public class Position {
    private int PositionID;
    private String PositionName;

    @Override
    public String toString() {
        return "Position{" +
                "PositionID=" + PositionID +
                ", PositionName='" + PositionName + '\'' +
                '}';
    }

    public int getPositionID() {
        return PositionID;
    }

    public void setPositionID(int positionID) {
        PositionID = positionID;
    }

    public String getPositionName() {
        return PositionName;
    }

    public void setPositionName(String positionName) {
        PositionName = positionName;
    }
}
