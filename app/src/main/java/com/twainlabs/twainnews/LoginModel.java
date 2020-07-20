package com.twainlabs.twainnews;

public class LoginModel {
    String Logintxt;
    int LoginImg;

    public LoginModel(String logintxt, int loginImg) {
        Logintxt = logintxt;
        LoginImg = loginImg;
    }

    public void setLogintxt(String logintxt) {
        Logintxt = logintxt;
    }

    public void setLoginImg(int loginImg) {
        LoginImg = loginImg;
    }

    public String getLogintxt() {
        return Logintxt;
    }

    public int getLoginImg() {
        return LoginImg;
    }
}
