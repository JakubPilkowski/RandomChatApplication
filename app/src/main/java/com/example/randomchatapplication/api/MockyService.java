package com.example.randomchatapplication.api;

import com.example.randomchatapplication.api.responses.FieldsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MockyService {


    @GET("5ecd4e0a3200008b002368ab")
    Call<FieldsResponse> getFields();

}
