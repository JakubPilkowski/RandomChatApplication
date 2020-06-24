package com.example.randomchatapplication.models;

public class Hobby {

    private String value;
    private boolean checked;

    public Hobby(String value) {
        this.value = value;
        checked = false;
    }

    public String getValue() {
        return value;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
