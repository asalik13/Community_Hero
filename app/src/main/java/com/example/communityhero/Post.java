package com.example.communityhero;

public class Post {
    private int id;
    private String title;
    private String desc;
    private String contributors;

    public Post(int id, String title, String desc, String contributors) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.contributors = contributors;
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


}
