package com.example.randomchatapplication.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.http.GET;

public class Client {

      public static final String LOGIN_BASE_URL = "";
      public static final String MOCKY_IO_URL = "http://www.mocky.io/v2";

      protected Gson gson;

      public Client(){
          gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
                  .create();


      }

      public void createLoginAdapter(){

      }

      public void createMockyAdapter(){

      }

}
