package com.example.randomchatapplication.api;

import com.example.randomchatapplication.api.requests.AuthRequest;
import com.example.randomchatapplication.api.responses.AuthResponse;

import retrofit2.Call;

public class LoginConnection {


    private static LoginConnection INSTANCE;

    protected Client<LoginService> loginClient;

    public static void init(){
        INSTANCE = new LoginConnection();
    }

    public LoginConnection(){
        loginClient = new Client<>(LoginService.class, Client.LOGIN_BASE_URL);
    }

    public static LoginConnection get(){
        return INSTANCE;
    }


    public void login(BaseCallback<AuthResponse> callback, AuthRequest authRequest){
        Call<AuthResponse> call = loginClient.getService().login(authRequest);
        call.enqueue(callback);

    }



}
