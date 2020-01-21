package com.kcs.rest.pojo;

public class Permission {
    private int PermissionID;
    private String PermissionName;
    private int PermissionNum;

    @Override
    public String toString() {
        return "Permission{" +
                "PermissionID=" + PermissionID +
                ", PermissionName='" + PermissionName + '\'' +
                ", PermissionNum=" + PermissionNum +
                '}';
    }

    public int getPermissionID() {
        return PermissionID;
    }

    public void setPermissionID(int permissionID) {
        PermissionID = permissionID;
    }

    public String getPermissionName() {
        return PermissionName;
    }

    public void setPermissionName(String permissionName) {
        PermissionName = permissionName;
    }

    public int getPermissionNum() {
        return PermissionNum;
    }

    public void setPermissionNum(int permissionNum) {
        PermissionNum = permissionNum;
    }
}
