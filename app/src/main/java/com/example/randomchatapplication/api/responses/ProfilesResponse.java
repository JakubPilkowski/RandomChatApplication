package com.example.randomchatapplication.api.responses;

import com.example.randomchatapplication.models.Profile;

import java.util.ArrayList;

public class ProfilesResponse {
    private ArrayList<Profile> profile;
    public ArrayList<Profile> getProfiles() {
        return profile;
    }
}
