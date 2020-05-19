package com.example.randomchatapplication.models;

import java.util.HashMap;

public class Field {

    private String title;
    private String type;
    private int step;
    private boolean required;
    private String note;
    private HashMap<Object, Object> options;
    private int priority;


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
