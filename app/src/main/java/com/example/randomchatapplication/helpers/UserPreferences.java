package com.example.randomchatapplication.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.randomchatapplication.api.responses.LoginResponse;
import com.google.gson.reflect.TypeToken;

public class UserPreferences {
    private static final String TAG = "UserPreferences";
    private static final String FILENAME = TAG + "_RandomChatApp";
    private static final String AUTH_RESPONSE = TAG + "Response";

    private static UserPreferences INSTANCE;
    private SharedPreferences preferences;

    private UserPreferences(Context context){
        preferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
    }

    public static UserPreferences getINSTANCE(){
        return INSTANCE;
    }

    public static void initInstance(Context context){
        INSTANCE = new UserPreferences(context);
    }

    public void save(LoginResponse response){
        String json = JsonTranslator.getJsonFromObject(response);
        preferences.edit().putString(AUTH_RESPONSE, json).apply();
    }
    public String getAuthToken(){
        String json = preferences.getString(AUTH_RESPONSE, null);
        if (json == null)
            return null;
        return JsonTranslator.getObjectFromJson(json, new TypeToken<LoginResponse>(){}).getToken();
    }
    public void clear(){
        preferences.edit().putString(AUTH_RESPONSE, null).apply();
    }
    public boolean hasUser(){
        return getAuthToken() !=null && !getAuthToken().isEmpty();
    }
}
