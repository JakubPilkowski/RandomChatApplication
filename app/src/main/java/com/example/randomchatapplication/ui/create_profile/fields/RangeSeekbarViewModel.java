package com.example.randomchatapplication.ui.create_profile.fields;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.example.randomchatapplication.models.Field;
import com.example.randomchatapplication.viewmodels.FieldViewModel;

public class RangeSeekbarViewModel extends FieldViewModel {

    public ObservableField<OnRangeSeekbarChangeListener> seekBarListener = new ObservableField<>();
    public ObservableBoolean visibility = new ObservableBoolean(true);
    public ObservableField<String>note = new ObservableField<>();
    public ObservableField<String> leftValue = new ObservableField<>("18 lat");
    public ObservableField<String> rightValue = new ObservableField<>("60 lat");
    public ObservableField<String> title = new ObservableField<>();

    public void init(Field field){
        title.set(field.getTitle());
        visibility.set(field.getNote().length() > 0);
        note.set(field.getNote());
        seekBarListener.set(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                leftValue.set(minValue + " lat");
                rightValue.set(maxValue + " lat");
            }
        });
    }

}
