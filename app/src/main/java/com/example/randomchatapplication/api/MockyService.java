package com.example.randomchatapplication.api;

import com.example.randomchatapplication.api.responses.FieldsResponse;
import com.example.randomchatapplication.api.responses.HobbiesResponse;
import com.example.randomchatapplication.api.responses.ProfilesResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MockyService {


    @GET("83db642b-1357-4ac9-bc93-1fc0c16353d8")
    Observable<FieldsResponse> getFields();


    @GET("7c75d6cc-41c3-4270-962e-85a5375c1415")
    Observable<HobbiesResponse> getHobbies();

    @GET("0cdea54f-1f71-4afb-8d7e-f1ba4943644b")
    Call<ProfilesResponse> getProfiles();

}
