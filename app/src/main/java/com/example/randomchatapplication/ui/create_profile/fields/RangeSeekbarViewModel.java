package com.example.randomchatapplication.ui.create_profile.fields;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableFloat;
import androidx.databinding.ObservableInt;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.example.randomchatapplication.models.Field;
import com.example.randomchatapplication.viewmodels.FieldViewModel;

public class RangeSeekbarViewModel extends FieldViewModel {

    public ObservableField<OnRangeSeekbarChangeListener> seekBarListener = new ObservableField<>();
    public ObservableBoolean visibility = new ObservableBoolean(true);
    public ObservableField<String>note = new ObservableField<>();
    public ObservableInt minValue = new ObservableInt();
    public ObservableInt maxValue = new ObservableInt();
    public ObservableField<String> leftValue = new ObservableField<>("");
    public ObservableField<String> rightValue = new ObservableField<>("");
    public ObservableField<String> title = new ObservableField<>();
    public ObservableFloat step = new ObservableFloat();
    public String stepType;
    public void init(Field field){
        stepType = (String) field.getOptions().get("stepType");
        title.set(field.getTitle());
        visibility.set(field.getNote().length() > 0);
        note.set(field.getNote());
        minValue.set((int)Math.round((Double) field.getOptions().get("minValue")));
        maxValue.set((int)Math.round((Double) field.getOptions().get("maxValue")));
        double stepValue = (Double)field.getOptions().get("step");
        step.set((float)stepValue);
        leftValue.set(minValue.get()+stepType);
        rightValue.set(maxValue.get()+stepType);
        seekBarListener.set(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                leftValue.set(minValue + stepType);
                rightValue.set(maxValue + stepType);
            }
        });
    }

}
