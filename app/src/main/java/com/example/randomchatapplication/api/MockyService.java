package com.example.randomchatapplication.api;

import com.example.randomchatapplication.api.responses.FieldsResponse;
import com.example.randomchatapplication.api.responses.HobbiesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MockyService {


    @GET("a0e2b1c6-4dc9-43a9-b607-7b00cf9ad56a")
    Call<FieldsResponse> getFields();


    @GET("7c75d6cc-41c3-4270-962e-85a5375c1415")
    Call<HobbiesResponse> getHobbies();

}
