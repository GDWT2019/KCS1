package com.kcs.rest.pojo;

import java.io.Serializable;

public class RolePresent implements Serializable {
    private int RoleID;
    private String RoleName;
    private int PermissionID;
    private String PermissionName;
    private int PermissionNum;

    @Override
    public String toString() {
        return "RolePresent{" +
                "RoleID=" + RoleID +
                ", RoleName='" + RoleName + '\'' +
                ", PermissionID=" + PermissionID +
                ", PermissionName='" + PermissionName + '\'' +
                ", PermissionNum=" + PermissionNum +
                '}';
    }

    public int getRoleID() {
        return RoleID;
    }

    public void setRoleID(int roleID) {
        RoleID = roleID;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
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
