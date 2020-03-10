package com.kcs.rest.pojo;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private Integer UserID;
    private String PositionID;
    private String DepartmentID;
    private String LoginName;
    private boolean Sex;
    private String UserName;
    private String Password;
    private String Tel;
    private String Email;
    private String Photo;
    private String Note;
    private boolean WarehouseMark;
    private boolean ListerMark;
    private boolean Status;
    private List<Role> roles;


    @Override
    public String toString() {
        return "User{" +
                "UserID=" + UserID +
                ", PositionID='" + PositionID + '\'' +
                ", DepartmentID='" + DepartmentID + '\'' +
                ", LoginName='" + LoginName + '\'' +
                ", Sex=" + Sex +
                ", UserName='" + UserName + '\'' +
                ", Password='" + Password + '\'' +
                ", Tel='" + Tel + '\'' +
                ", Email='" + Email + '\'' +
                ", Photo='" + Photo + '\'' +
                ", Note='" + Note + '\'' +
                ", WarehouseMark=" + WarehouseMark +
                ", ListerMark=" + ListerMark +
                ", Status=" + Status +
                '}';
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }


    public String getPositionID() {
        return PositionID;
    }

    public void setPositionID(String positionID) {
        PositionID = positionID;
    }

    public String getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(String departmentID) {
        DepartmentID = departmentID;
    }

    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String loginName) {
        LoginName = loginName;
    }

    public boolean isSex() {
        return Sex;
    }

    public void setSex(boolean sex) {
        Sex = sex;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public boolean isWarehouseMark() {
        return WarehouseMark;
    }

    public void setWarehouseMark(boolean warehouseMark) {
        WarehouseMark = warehouseMark;
    }

    public boolean isListerMark() {
        return ListerMark;
    }

    public void setListerMark(boolean listerMark) {
        ListerMark = listerMark;
    }
}