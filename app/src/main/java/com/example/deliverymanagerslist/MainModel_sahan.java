package com.example.deliverymanagerslist;

public class MainModel_sahan {

    String name,location,contactno,pic;

    MainModel_sahan()
    {

    }
    public MainModel_sahan(String name, String location, String contactno, String pic) {
        this.name = name;
        this.location = location;
        this.contactno = contactno;
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
