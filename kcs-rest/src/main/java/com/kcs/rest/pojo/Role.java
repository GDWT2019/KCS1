package com.kcs.rest.pojo;

public class Role {
    private int RoleID;

    private String RoleName;

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

    @Override
    public String toString() {
        return "Role{" +
                "RoleID=" + RoleID +
                ", RoleName='" + RoleName + '\'' +
                '}';
    }
}
