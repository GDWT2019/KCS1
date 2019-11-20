package com.kcs.rest.pojo;

public class User {
    private Integer userid;

    private Integer positionid;

    private Integer departmentid;

    private String loginname;

    private String username;

    private String password;

    private Boolean sex;

    private String tel;

    private String email;

    private String photo;

    private String note;

    private Boolean warehousemark;

    private Boolean listermark;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getPositionid() {
        return positionid;
    }

    public void setPositionid(Integer positionid) {
        this.positionid = positionid;
    }

    public Integer getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(Integer departmentid) {
        this.departmentid = departmentid;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Boolean getWarehousemark() {
        return warehousemark;
    }

    public void setWarehousemark(Boolean warehousemark) {
        this.warehousemark = warehousemark;
    }

    public Boolean getListermark() {
        return listermark;
    }

    public void setListermark(Boolean listermark) {
        this.listermark = listermark;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", positionid=" + positionid +
                ", departmentid=" + departmentid +
                ", loginname='" + loginname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", photo='" + photo + '\'' +
                ", note='" + note + '\'' +
                ", warehousemark=" + warehousemark +
                ", listermark=" + listermark +
                '}';
    }
}