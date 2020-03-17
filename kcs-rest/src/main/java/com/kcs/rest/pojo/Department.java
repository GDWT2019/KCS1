package com.kcs.rest.pojo;

import java.io.Serializable;

public class Department implements Serializable {
    private Integer DepartmentID;       //部门id

    private String DepartmentName;      //部门名称

    public Integer getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(Integer departmentID) {
        DepartmentID = departmentID;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "DepartmentID=" + DepartmentID +
                ", DepartmentName='" + DepartmentName + '\'' +
                '}';
    }
}