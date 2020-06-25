package com.example.randomchatapplication.ui.create_profile.fields;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.example.randomchatapplication.models.Field;
import com.example.randomchatapplication.viewmodels.FieldViewModel;

public class EditTextViewModel extends FieldViewModel {
    public ObservableField<String>title = new ObservableField<>();
    public ObservableBoolean visibility = new ObservableBoolean(true);
    public ObservableField<String>note = new ObservableField<>();
    public ObservableField<String> textAmount = new ObservableField<>();
    public ObservableInt lines = new ObservableInt();
    public ObservableInt maxLength = new ObservableInt();
    public ObservableInt maxLines = new ObservableInt();
    public ObservableField<String> imeOption = new ObservableField<>();
    public ObservableField<String> inputType = new ObservableField<>();
    public ObservableField<TextWatcher> textChangeListener = new ObservableField<>();


    public void init(Field field){
        title.set(field.getTitle());
        visibility.set(field.getNote().length() > 0);
        note.set(field.getNote());
        imeOption.set((String) field.getOptions().get("imeOption"));
        inputType.set((String) field.getOptions().get("inputType"));
        maxLines.set((int)Math.round((Double)field.getOptions().get("maxLines")));
        lines.set((int)Math.round((Double)field.getOptions().get("lines")));
        textAmount.set("0/"+Math.round((Double) field.getOptions().get("maxLength")));
        maxLength.set((int) Math.round((Double)field.getOptions().get("maxLength")));
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
