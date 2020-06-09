package com.example.randomchatapplication.api;

import com.example.randomchatapplication.api.responses.FieldsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MockyService {


    @GET("a0e2b1c6-4dc9-43a9-b607-7b00cf9ad56a")
    Call<FieldsResponse> getFields();




}
