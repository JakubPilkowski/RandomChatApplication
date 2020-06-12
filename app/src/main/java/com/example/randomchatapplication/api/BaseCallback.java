package com.example.randomchatapplication.api;


import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class BaseCallback<T> implements Callback<T> {

    public abstract void onSuccess(T response);

    public abstract void onError(String message);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        T body = response.body();
        int code = response.code();

        if(code == 400){
            onError("Bad request");
        }

        if(code == 401){
            onError("Unauthorized");
        }
        if (code == 500)
        {
            onError("Something went wrong");
        }

        if(body==null){
            return;
        }

        if(response.isSuccessful()){
            onSuccess(body);
        }else{
            onError(response.message());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
            if(t.getMessage() != null){
                onError(t.getMessage());
            }
    }
}
