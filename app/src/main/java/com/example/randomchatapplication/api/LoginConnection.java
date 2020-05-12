package com.example.randomchatapplication.api;

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


    public void validate(){

    }
    public void login(){

    }
    public void register(){

    }


}
