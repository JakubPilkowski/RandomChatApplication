package com.example.randomchatapplication.ui.auth.register;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Toast;

import androidx.databinding.ObservableField;

import com.example.randomchatapplication.activites.main.MainActivity;
import com.example.randomchatapplication.activites.profile_creation.CreateProfileActivity;
import com.example.randomchatapplication.api.BaseCallback;
import com.example.randomchatapplication.api.LoginConnection;
import com.example.randomchatapplication.api.requests.AuthRequest;
import com.example.randomchatapplication.api.responses.AuthResponse;
import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.helpers.Hashing;
import com.example.randomchatapplication.helpers.ProgressDialogManager;
import com.example.randomchatapplication.helpers.UserPreferences;
import com.example.randomchatapplication.helpers.Validator;
import com.example.randomchatapplication.helpers.WebViewHelper;

public class RegisterViewModel extends BaseViewModel {
    // TODO: Implement the ViewModel
    public ObservableField<String> htmlEffect = new ObservableField<>(WebViewHelper.MOVING_BORDER_TYPE);
    private String email;
    private String password;

    public ObservableField<TextWatcher> emailListener = new ObservableField<>();
    public ObservableField<TextWatcher> passwordListener = new ObservableField<>();

    public void init() {
        emailListener.set(emailChangedListener);
        passwordListener.set(passwordChangedListener);
    }

    private TextWatcher emailChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            email = s.toString();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private TextWatcher passwordChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            password = s.toString();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private BaseCallback<AuthResponse> callback = new BaseCallback<AuthResponse>() {
        @Override
        public void onSuccess(AuthResponse response) {
            UserPreferences.getINSTANCE().save(response);
            ProgressDialogManager.get().dismiss();
            getActivity().startActivity(new Intent(getActivity().getApplicationContext(), CreateProfileActivity.class));
        }

        @Override
        public void onError(String message) {
            ProgressDialogManager.get().dismiss();
            Toast.makeText(getActivity().getApplicationContext(), "Autentykacja nie przeszła pomyślnie", Toast.LENGTH_LONG).show();
        }
    };

    public void onNextClick() {
        if (Validator.emailValidator(email) && Validator.passwordValidator(password)) {
            if (UserPreferences.getINSTANCE().hasUser()) {
                getActivity().startActivity(new Intent(getActivity().getApplicationContext(), CreateProfileActivity.class));
            } else {
                ProgressDialogManager.get().show();
                String hashedPassword = Hashing.PBKDF2Hashing(password);
                LoginConnection.get().login(callback, new AuthRequest(email, hashedPassword));
            }
        } else {
            Toast.makeText(getActivity().getApplicationContext(), "Pola niepoprawnie wypełnione", Toast.LENGTH_LONG).show();
        }
    }

    public void onBackClick() {
        getActivity().onBackPressed();
    }
}
