package com.example.randomchatapplication.models;

import java.util.ArrayList;

public class Profile {
    private String name;
    private int age;
    private String description;
    private ArrayList<Photo> photos;
    private String miasto;
    private String województwo;
    private String płeć;
    private String orientacja;
    private String cel;
    private String motto;
    private ArrayList<Hobby>zainteresowania;

    public Profile(String name, int age, String description, ArrayList<Photo> photos, String miasto, String województwo, String płeć, String orientacja, String cel, String motto, ArrayList<Hobby> zainteresowania) {
        this.name = name;
        this.age = age;
        this.description = description;
        this.photos = photos;
        this.miasto = miasto;
        this.województwo = województwo;
        this.płeć = płeć;
        this.orientacja = orientacja;
        this.cel = cel;
        this.motto = motto;
        this.zainteresowania = zainteresowania;
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

    public String getMiasto() {
        return miasto;
    }

    public String getWojewództwo() {
        return województwo;
    }

    public String getPłeć() {
        return płeć;
    }

    public String getOrientacja() {
        return orientacja;
    }

    public String getCel() {
        return cel;
    }

    public String getMotto() {
        return motto;
    }

    public ArrayList<Hobby> getZainteresowania() {
        return zainteresowania;
    }
}
