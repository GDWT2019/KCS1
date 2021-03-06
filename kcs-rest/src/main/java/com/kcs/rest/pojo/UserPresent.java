package com.kcs.rest.pojo;

import java.io.Serializable;

public class UserPresent implements Serializable {
    private int UserID;
    private String PositionID;
    private String PositionName;        //职位名称
    private String DepartmentID;
    private String DepartmentName;      //部门名称
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

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    private boolean Status;

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getPositionID() {
        return PositionID;
    }

    public void setPositionID(String positionID) {
        PositionID = positionID;
    }

    public String getPositionName() {
        return PositionName;
    }

    public void setPositionName(String positionName) {
        PositionName = positionName;
    }

    public String getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(String departmentID) {
        DepartmentID = departmentID;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
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
