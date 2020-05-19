package com.example.randomchatapplication.api;

import com.example.randomchatapplication.api.responses.FieldsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MockyService {


    @GET("5ec4056830000091fe39c641")
    Call<FieldsResponse> getFields();

}
