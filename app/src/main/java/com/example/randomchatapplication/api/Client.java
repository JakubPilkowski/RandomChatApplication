package com.example.randomchatapplication.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class Client<T> {

      public static final String LOGIN_BASE_URL = "";
      public static final String MOCKY_IO_URL = "http://www.mocky.io/v2";

      private static Retrofit retrofit;


      protected Gson gson;
      private T service;


      public Client(Class<T> serviceClass, String url){
          gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
                  .create();
          retrofit = new Retrofit.Builder()
                  .baseUrl(url)
                  .addConverterFactory(GsonConverterFactory.create(gson))
                  .build();
          service = retrofit.create(serviceClass);
      }


      public T getService() {
            return service;
      }
}
