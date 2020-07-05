package com.example.randomchatapplication.models;

import java.util.ArrayList;

public class Profile {
    private String name;
    private int age;
    private String description;
    private ArrayList<Photo> photos;

    public Profile(String name, int age, String description, ArrayList<Photo> photos) {
        this.name = name;
        this.age = age;
        this.description = description;
        this.photos = photos;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDescription() {
        return description;
    }



    public ArrayList<Photo> getPhotos() {
        return photos;
    }
}
