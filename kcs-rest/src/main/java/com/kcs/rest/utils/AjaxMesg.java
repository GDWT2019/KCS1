package com.kcs.rest.utils;

public class AjaxMesg {
    private boolean flag;
    private String mesg;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMesg() {
        return mesg;
    }

    public void setMesg(String mesg) {
        this.mesg = mesg;
    }

    public AjaxMesg() {
    }

    public AjaxMesg(boolean flag) {
        this.flag = flag;
    }

    public AjaxMesg(boolean flag, String mesg) {
        this.flag = flag;
        this.mesg = mesg;
    }
}
