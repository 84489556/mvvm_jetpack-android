package com.yuanda.usercenter.bean;

import androidx.lifecycle.LiveData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Account {

    private String phone;
    private String pwd;
    private String lastTime;
    private int type;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getLastTime() {
        return sdf.format(new Date());
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
