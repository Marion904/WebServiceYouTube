package com.example.wilder.webserviceyoutube;

/**
 * Created by wilder on 03/05/17.
 */

public class Video {
    private String title;
    private String description;
    private String link;
    private String picture;

    public Video(){

    }

    public Video(String title, String description, String link, String picture){
        this.title = title;
        this.description = description;
        this.link = link;
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
