package com.example.randomchatapplication.models;

import java.util.HashMap;

public class Field {

    public String title;
    public String type;
    public int step;
    public boolean required;
    public String note;
    public HashMap<Object, Object> options;
    public int priority;


    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public int getStep() {
        return step;
    }

    public boolean isRequired() {
        return required;
    }

    public String getNote() {
        return note;
    }

    public HashMap<Object, Object> getOptions() {
        return options;
    }

    public int getPriority() {
        return priority;
    }
}
