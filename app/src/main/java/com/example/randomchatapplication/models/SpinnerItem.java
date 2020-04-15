package com.example.randomchatapplication.models;

public class SpinnerItem {
    private String key;
    private String value;
    private String imgUrl;

    public SpinnerItem(String key, String value, String imgUrl) {
        this.key = key;
        this.value = value;
        this.imgUrl = imgUrl;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
