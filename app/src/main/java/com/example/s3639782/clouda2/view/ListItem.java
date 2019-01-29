package com.example.s3639782.clouda2.view;

public class ListItem {

    private String heading;
    private String desc;
    private String address;
    private String user;
    private String LatLng;
    private String Date;

    public ListItem(String heading, String desc, String address, String user, String latLng, String Date) {
        this.heading = heading;
        this.desc = desc;
        this.address = address;
        this.user = user;
        this.LatLng = latLng;
        this.Date = Date;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLatLng() {
        return LatLng;
    }

    public void setLatLng(String latLng) {
        LatLng = latLng;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
