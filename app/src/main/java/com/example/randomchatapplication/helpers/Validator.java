package com.example.randomchatapplication.helpers;

import android.util.Patterns;

import com.example.randomchatapplication.viewmodels.FieldViewModel;

import java.util.List;
import java.util.regex.Pattern;

public class Validator {

    public static boolean emailValidator(String email){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        if(email == null ||email.isEmpty())
            return false;
        else
            return pattern.matcher(email).matches();
    }
    public static boolean passwordValidator(String password){
        if(password == null || password.isEmpty())
            return false;
        else return password.trim().length() >= 8;
    }
    public static boolean fieldValidator(List<FieldViewModel> viewModelList){
        return true;
    }


}
