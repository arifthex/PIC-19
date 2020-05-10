package com.example.picovid_19.Model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class NewsModel {

    private String nama;
    private String email;
    private String desk;
    private String key;

    public NewsModel(){
    }

    public NewsModel(String nama, String email, String desk, String key) {
        this.nama = nama;
        this.email = email;
        this.desk = desk;
        this.key = key;
    }

    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesk() {
        return desk;
    }
    public void setDesk(String desk) {
        this.desk = desk;
    }

    public String getKey() {
        return key;
    }
    public void setKey(String key) { this.key = key; }
}
