package com.example.shoponyourpocket;

public class UserID {
    private String name,address,email,password,mobilenumber,shoptype;
    private int DOB_day,DOB_month,DOB_year;

    UserID(){

    }
    UserID(String name, String email, String password, String mobilenumber,String shoptype, String address, int day, int month, int year){
        this.name = name;
        this.email = email;
        this.address = address;
        this.mobilenumber = mobilenumber;
        this.password = password;
        this.shoptype = shoptype;
        this.DOB_day = day;
        this.DOB_month = month;
        this.DOB_year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*
    public String getPassword() {
        return password;
    }*/

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getShoptype() {
        return shoptype;
    }

    public void setShoptype(String shoptype) {
        this.shoptype = shoptype;
    }

    public int getDOB_Day() {
        return DOB_day;
    }

    public void setDOB_Day(int day) {
        this.DOB_day = day;
    }

    public int getDOB_Month() {
        return DOB_month;
    }

    public void setDOB_Month(int month) {
        this.DOB_month = month;
    }

    public int getDOB_Year() {
        return DOB_year;
    }

    public void setDOB_Year(int year) {
        this.DOB_year = year;
    }
}

