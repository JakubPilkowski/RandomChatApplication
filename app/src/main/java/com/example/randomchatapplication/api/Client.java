package com.example.randomchatapplication.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client<T> {

      public static final String LOGIN_BASE_URL = "https://reqres.in/";
      public static final String MOCKY_IO_URL = "https://run.mocky.io/v3/";


    protected Gson gson;
      private T service;


      public Client(Class<T> serviceClass, String url){
          gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
                  .create();
          Retrofit retrofit = new Retrofit.Builder()
                  .baseUrl(url)
                  .addConverterFactory(GsonConverterFactory.create())
                  .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                  .build();
          service = retrofit.create(serviceClass);
      }


      public T getService() {
            return service;
      }
}
