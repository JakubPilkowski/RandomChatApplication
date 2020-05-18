package com.example.randomchatapplication.ui.create_profile.fields;

import android.text.Editable;
import android.text.TextWatcher;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.example.randomchatapplication.models.Field;
import com.example.randomchatapplication.viewmodels.FieldViewModel;

public class EditTextViewModel extends FieldViewModel {

    public ObservableBoolean visibility = new ObservableBoolean(true);
    public ObservableField<String>title = new ObservableField<>();
    public ObservableField<String>note = new ObservableField<>();
    public ObservableField<String> textAmount = new ObservableField<>();
    public ObservableInt maxLength = new ObservableInt(30);
    public ObservableField<TextWatcher> textChangeListener = new ObservableField<>();


    public void init(Field field){
        textAmount.set("0/30");
        textChangeListener.set(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textAmount.set(s.length() + "/" +maxLength.get());
                value.set(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}
