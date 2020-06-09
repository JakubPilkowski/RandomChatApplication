package com.example.randomchatapplication.adapters.searchview_hobby;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.base.BaseAdapterViewModel;
import com.example.randomchatapplication.models.Hobby;

import java.security.PublicKey;

public class HobbySearchViewAdapterViewModel extends BaseAdapterViewModel {
    private Hobby hobby;
    public ObservableField<String> value = new ObservableField<>();
    public ObservableBoolean checked = new ObservableBoolean();
    @Override
    public void init(Object[] values) {
        hobby = (Hobby) values[0];
        value.set(hobby.getValue());
        checked.set(hobby.isChecked());
    }

    public void onClick(){
        hobby.setChecked(!hobby.isChecked());
        checked.set(hobby.isChecked());
    }

}
