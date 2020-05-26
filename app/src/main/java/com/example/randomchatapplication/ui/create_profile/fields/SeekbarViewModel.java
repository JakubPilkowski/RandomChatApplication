package com.example.randomchatapplication.ui.create_profile.fields;

import android.widget.SeekBar;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.example.randomchatapplication.models.Field;
import com.example.randomchatapplication.viewmodels.FieldViewModel;

public class SeekbarViewModel extends FieldViewModel {
    public ObservableBoolean visibility = new ObservableBoolean(true);
    public ObservableField<String>note = new ObservableField<>();
    public ObservableField<String> title = new ObservableField<>();
    public ObservableInt minValue = new ObservableInt();
    public ObservableInt maxValue = new ObservableInt();
    public ObservableField<String> minValueLabel = new ObservableField<>();
    public ObservableField<String> maxValueLabel = new ObservableField<>();
    public String stepType;
    private int min;
    private int max;
    public void init(Field field){
        stepType = (String) field.getOptions().get("stepType");
        min = (int)Math.round((Double) field.getOptions().get("minValue"));
        max = (int)Math.round((Double) field.getOptions().get("maxValue")-min);
        minValue.set(min);
        maxValue.set(max);
        value.set(min+stepType);
        minValueLabel.set(min+stepType);
        maxValueLabel.set((min+max)+stepType);
        title.set(field.getTitle());
        visibility.set(!field.getNote().isEmpty());
        note.set(field.getNote());
    }
    public void onValueChanged(SeekBar seekBar, final int progress, boolean fromUser) {
        value.set((progress+min)+stepType);
    }
}
