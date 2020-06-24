package com.example.randomchatapplication.api;

import com.example.randomchatapplication.api.responses.FieldsResponse;
import com.example.randomchatapplication.api.responses.HobbiesResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface MockyService {


    @GET("5dc3b3cc-223d-4faf-a367-c7739bea8d7b")
    Observable<FieldsResponse> getFields();


    @GET("7c75d6cc-41c3-4270-962e-85a5375c1415")
    Observable<HobbiesResponse> getHobbies();

}
