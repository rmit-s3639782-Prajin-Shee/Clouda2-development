package com.example.s3639782.clouda2.view;

public class ListItem {

    private String heading;
    private String desc;

    public ListItem(String heading, String desc) {
        this.heading = heading;
        this.desc = desc;
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
}
