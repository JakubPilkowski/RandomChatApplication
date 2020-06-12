package com.example.randomchatapplication.api;

import com.example.randomchatapplication.api.requests.AuthRequest;
import com.example.randomchatapplication.api.responses.AuthResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("api/register")
    Call<AuthResponse> register(
            @Body AuthRequest authRequest
    );

    @POST("api/login")
    Call<AuthResponse> login(
            @Body AuthRequest authRequest
    );

}
