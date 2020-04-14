package com.example.randomchatapplication.ui.create_profile;

import android.text.Editable;
import android.text.TextWatcher;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.example.randomchatapplication.base.BaseViewModel;

public class CreateProfileFragmentViewModel extends BaseViewModel {
    // TODO: Implement the ViewModel
    public ObservableField<String> textAmount = new ObservableField<>();
    public ObservableInt maxLength = new ObservableInt(30);
    public ObservableField<TextWatcher> textChangeListener = new ObservableField<>();
    public ObservableField<OnRangeSeekbarChangeListener> seekBarListener = new ObservableField<>();
    public ObservableField<String> leftValue = new ObservableField<>("18 lat");
    public ObservableField<String> rightValue = new ObservableField<>("60 lat");
    public void init(){
        textAmount.set("0/30");
        textChangeListener.set(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    textAmount.set(s.length() + "/" +maxLength.get());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        seekBarListener.set(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                leftValue.set(minValue + " lat");
                rightValue.set(maxValue + " lat");
            }
        });
    }

}
