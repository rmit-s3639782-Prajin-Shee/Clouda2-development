package com.example.s3639782.clouda2.view;

public class ListItem {

    private String heading;
    private String desc;
    private String address;
    private String user;
    private String LatLng;
    private String Date;
    private String Time;
    private String Lat;
    private String Long;

    public ListItem(String heading, String desc, String address, String user, String Lat, String Long, String Date, String Time) {
        this.heading = heading;
        this.desc = desc;
        this.address = address;
        this.user = user;
       // this.LatLng = latLng;
        this.Lat = Lat;
        this.Long = Long;
        this.Date = Date;
        this.Time = Time;
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

    public String getLat() {
        return Lat;
    }

    public void setLat(String lat) {
        Lat = lat;
    }

    public String getLong() {
        return Long;
    }

    public void setLong(String aLong) {
        Long = aLong;
    }

    public void setLatLng(String latLng) {
        LatLng = latLng;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
