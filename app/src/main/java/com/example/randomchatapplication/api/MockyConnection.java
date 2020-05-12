package com.example.randomchatapplication.api;

import com.example.randomchatapplication.api.responses.FieldsResponse;

import retrofit2.Call;

public class MockyConnection {


    public static MockyConnection INSTANCE;

    protected Client<MockyService> mockyClient;
    public static void init(){
        INSTANCE = new MockyConnection();
    }

    public MockyConnection(){
        mockyClient = new Client<>(MockyService.class, Client.MOCKY_IO_URL);
    }

    public static MockyConnection get(){
        return INSTANCE;
    }


    public void getFields(BaseCallback<FieldsResponse> callback){
       Call<FieldsResponse> call = mockyClient.getService().getFields();
       call.enqueue(callback);
    }

}
