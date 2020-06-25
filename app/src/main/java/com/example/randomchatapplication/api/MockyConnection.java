package com.example.randomchatapplication.api;

import android.util.Log;

import com.example.randomchatapplication.api.responses.FieldsResponse;
import com.example.randomchatapplication.api.responses.HobbiesAndFieldsResponse;
import com.example.randomchatapplication.api.responses.HobbiesResponse;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.Subject;
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


    public void getFieldsAndHobbies(RxJavaCallback<HobbiesAndFieldsResponse> callback){
        Observable<FieldsResponse> fieldsObservable = mockyClient.getService().getFields().subscribeOn(Schedulers.newThread()).observeOn(Schedulers.computation());
        Observable<HobbiesResponse> hobbiesObservable = mockyClient.getService().getHobbies().subscribeOn(Schedulers.newThread()).observeOn(Schedulers.computation());
        Observable<HobbiesAndFieldsResponse> combined = Observable.zip(hobbiesObservable,fieldsObservable , HobbiesAndFieldsResponse::new);
        combined.subscribe(callback);
    }

//    public void getHobbies(BaseCallback<HobbiesResponse> callback){
//        Call<HobbiesResponse> call = mockyClient.getService().getHobbies();
//        call.enqueue(callback);
//    }


}
