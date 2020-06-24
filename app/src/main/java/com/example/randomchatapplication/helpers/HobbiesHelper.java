package com.example.randomchatapplication.helpers;

import com.example.randomchatapplication.models.Hobby;

import java.util.ArrayList;


public class HobbiesHelper {

    public static HobbiesHelper INSTANCE ;
    private ArrayList<Hobby> hobbies;

    public HobbiesHelper(ArrayList<Hobby>hobbies){
        this.hobbies = hobbies;
    }

    public static void init(ArrayList<Hobby>hobbies){
        INSTANCE = new HobbiesHelper(hobbies);
    }

    public static HobbiesHelper get(){
        return INSTANCE;
    }

    public ArrayList<Hobby> getHobbies() {
        return hobbies;
    }
}
