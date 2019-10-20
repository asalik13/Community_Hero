package com.example.communityhero;

import java.util.Date;

public class Post {
    private int id;
    private String title;
    private String desc;
    private String contributors;
    private String date;
    private double latitude;
    private double longitude;

    // Class to contain all the post related information
    public Post(int id, String title, String desc, String contributors, String date, double lat, double lon) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.contributors = contributors;
        this.date = date;
        this.latitude = lat;
        this.longitude = lon;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getContributors() {
        return contributors;
    }

    public String getDate() {return date;}

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

}
