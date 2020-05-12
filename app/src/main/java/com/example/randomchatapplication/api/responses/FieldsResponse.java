package com.example.randomchatapplication.api.responses;

import com.example.randomchatapplication.models.Field;
import com.example.randomchatapplication.models.Step;

import java.util.ArrayList;
import java.util.List;

public class FieldsResponse {
    ArrayList<Field> pola;
    ArrayList<Step> kroki;

    public ArrayList<Field> getPola() {
        return pola;
    }

    public ArrayList<Step> getKroki() {
        return kroki;
    }
}
