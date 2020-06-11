package com.example.randomchatapplication.adapters.hobbies.hobbiesSearchViewAdapter;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.example.randomchatapplication.base.BaseAdapterViewModel;
import com.example.randomchatapplication.models.Hobby;

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
