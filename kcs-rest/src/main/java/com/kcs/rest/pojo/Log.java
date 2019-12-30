package com.kcs.rest.pojo;

public class Log {
    private int LogID;      //日志id

    private int UserID;     //用户id

    private String Time;        //操作时间

    private String Operation;       //操作内容

    private String Result;          //结果

    public int getLogID() {
        return LogID;
    }

    public void setLogID(int logID) {
        LogID = logID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getOperation() {
        return Operation;
    }

    public void setOperation(String operation) {
        Operation = operation;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    @Override
    public String toString() {
        return "Log{" +
                "LogID=" + LogID +
                ", UserID=" + UserID +
                ", Time='" + Time + '\'' +
                ", Operation='" + Operation + '\'' +
                ", Result='" + Result + '\'' +
                '}';
    }
}
