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

    @GET("ed8938c6-a537-4771-9ce3-f8fb8aaadb0f")
    Observable<HobbiesResponse> getHobbies();

    @GET("ac7a43e5-edba-4c95-be17-2d615210c78d")
    Call<ProfilesResponse> getProfiles();

}
