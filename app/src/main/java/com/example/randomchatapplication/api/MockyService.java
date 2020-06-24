package com.example.randomchatapplication.api;

import com.example.randomchatapplication.api.responses.FieldsResponse;
import com.example.randomchatapplication.api.responses.HobbiesResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MockyService {


    @GET("83c25d67-defe-41da-98d5-2977f0820969")
    Observable<FieldsResponse> getFields();


    @GET("7c75d6cc-41c3-4270-962e-85a5375c1415")
    Observable<HobbiesResponse> getHobbies();

}
