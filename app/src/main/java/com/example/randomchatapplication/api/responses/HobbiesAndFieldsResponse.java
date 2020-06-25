package com.example.randomchatapplication.api.responses;

public class HobbiesAndFieldsResponse {
    private HobbiesResponse hobbiesResponse;
    private FieldsResponse fieldsResponse;

    public HobbiesAndFieldsResponse(HobbiesResponse hobbiesResponse, FieldsResponse fieldsResponse) {
        this.hobbiesResponse = hobbiesResponse;
        this.fieldsResponse = fieldsResponse;
    }

    public HobbiesResponse getHobbiesResponse() {
        return hobbiesResponse;
    }

    public FieldsResponse getFieldsResponse() {
        return fieldsResponse;
    }
}
