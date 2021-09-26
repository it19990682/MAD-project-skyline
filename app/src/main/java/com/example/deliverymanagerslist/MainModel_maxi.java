package com.example.deliverymanagerslist;

public class MainModel_maxi {

    String name, company, tel, date, image;

    MainModel_maxi(){


    }

    public MainModel_maxi(String name, String company, String tel, String date, String image) {
        this.name = name;
        this.company = company;
        this.tel = tel;
        this.date = date;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
