package com.green.cinema.models;


import java.sql.Date;

public class StaffManager {
    private int id;
    private String hoTen;
    private String email;
    private String address;
    private int phone;
    private String birth;
    private String position;

    public StaffManager() {
    }

    public StaffManager(int id, String hoTen, String email, String address, int phone, String birth, String position) {
        this.id = id;
        this.hoTen = hoTen;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.birth = birth;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
