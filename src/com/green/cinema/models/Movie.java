package com.green.cinema.models;

import java.util.Objects;

public class Movie {
    private String id;
    private String name;
    private String quoc_gia;
    private String thoi_luong;
    private String the_loai;
    private String dien_vien;
    private String ngay_chieu;
    private int so_Luong_Ve;

    public Movie() {
    }

    public Movie(String id, String name, String quoc_gia, String thoi_luong, String the_loai, String dien_vien, String ngay_chieu, int so_Luong_Ve) {
        this.id = id;
        this.name = name;
        this.quoc_gia = quoc_gia;
        this.thoi_luong = thoi_luong;
        this.the_loai = the_loai;
        this.dien_vien = dien_vien;
        this.ngay_chieu = ngay_chieu;
        this.so_Luong_Ve = so_Luong_Ve;
    }

    public int getSo_Luong_Ve() {
        return so_Luong_Ve;
    }

    public void setSo_Luong_Ve(int so_Luong_Ve) {
        this.so_Luong_Ve = so_Luong_Ve;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuoc_gia() {
        return quoc_gia;
    }

    public void setQuoc_gia(String quoc_gia) {
        this.quoc_gia = quoc_gia;
    }

    public String getThoi_luong() {
        return thoi_luong;
    }

    public void setThoi_luong(String thoi_luong) {
        this.thoi_luong = thoi_luong;
    }

    public String getThe_loai() {
        return the_loai;
    }

    public void setThe_loai(String the_loai) {
        this.the_loai = the_loai;
    }

    public String getDien_vien() {
        return dien_vien;
    }

    public void setDien_vien(String dien_vien) {
        this.dien_vien = dien_vien;
    }

    public String getNgay_chieu() {
        return ngay_chieu;
    }

    public void setNgay_chieu(String ngay_chieu) {
        this.ngay_chieu = ngay_chieu;
    }
}
