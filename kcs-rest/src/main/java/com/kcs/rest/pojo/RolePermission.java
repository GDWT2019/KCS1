package com.kcs.rest.pojo;

import java.io.Serializable;

public class RolePermission implements Serializable {
    int roleID;
    int permissionID;

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public int getPermissionID() {
        return permissionID;
    }

    public void setPermissionID(int permissionID) {
        this.permissionID = permissionID;
    }
}
