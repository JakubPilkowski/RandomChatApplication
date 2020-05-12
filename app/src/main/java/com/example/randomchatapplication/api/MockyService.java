package com.example.randomchatapplication.api;

import com.example.randomchatapplication.api.responses.FieldsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MockyService {


    @GET("/5eb44a430e00005e00081af7")
    Call<FieldsResponse> getFields();

}
