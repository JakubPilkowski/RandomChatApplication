package com.example.randomchatapplication.ui.create_profile.fields;

import android.widget.CompoundButton;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.example.randomchatapplication.models.Field;
import com.example.randomchatapplication.viewmodels.FieldViewModel;

public class CheckBoxViewModel extends FieldViewModel {
    public ObservableBoolean visibility = new ObservableBoolean();
    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> note = new ObservableField<>();
    public ObservableField<CompoundButton.OnCheckedChangeListener> listener = new ObservableField<>();
    public void init(Field field) {
        title.set(field.getTitle());
        note.set(field.getNote());
        visibility.set(field.getNote().length() > 0);
        listener.set(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                value.set(isChecked ? "true" : "false");
            }
        });
    }
}
