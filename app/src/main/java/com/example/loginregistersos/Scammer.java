package com.example.loginregistersos;

import android.widget.EditText;

public class  Scammer {
    String phone, name;


    public Scammer(String phone, String name) {
        this.phone = phone;
        this.name = name;

    }

    public Scammer() {

    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String reportname) {
        this.name = name;
    }

    public void setPhone(String reportphone) {
        this.phone = phone;
    }
}
