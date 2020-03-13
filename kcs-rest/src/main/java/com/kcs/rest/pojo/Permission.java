package com.kcs.rest.pojo;

import java.io.Serializable;

public class Permission implements Serializable {
    private Integer PermissionID;
    private String PermissionName;
    private Integer PermissionNum;

    @Override
    public String toString() {
        return "Permission{" +
                "PermissionID=" + PermissionID +
                ", PermissionName='" + PermissionName + '\'' +
                ", PermissionNum=" + PermissionNum +
                '}';
    }

    public Integer getPermissionID() {
        return PermissionID;
    }

    public void setPermissionID(Integer permissionID) {
        PermissionID = permissionID;
    }

    public String getPermissionName() {
        return PermissionName;
    }

    public void setPermissionName(String permissionName) {
        PermissionName = permissionName;
    }

    public Integer getPermissionNum() {
        return PermissionNum;
    }

    public void setPermissionNum(Integer permissionNum) {
        PermissionNum = permissionNum;
    }
}
