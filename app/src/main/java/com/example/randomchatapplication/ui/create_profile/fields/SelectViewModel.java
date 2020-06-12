package com.example.randomchatapplication.ui.create_profile.fields;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.example.randomchatapplication.helpers.SelectViewDialogManager;
import com.example.randomchatapplication.interfaces.SpinnerViewListener;
import com.example.randomchatapplication.models.Field;
import com.example.randomchatapplication.models.SpinnerItem;
import com.example.randomchatapplication.viewmodels.FieldViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SelectViewModel extends FieldViewModel implements SpinnerViewListener {

    public ObservableField<String> spinnerValue = new ObservableField<>();
    public ObservableField<String> title = new ObservableField<>();
    public ObservableBoolean visibility = new ObservableBoolean(true);
    public ObservableField<String> note = new ObservableField<>();
    private List<SpinnerItem> itemList = new ArrayList<>();

    public void init(Field field) {
        title.set(field.getTitle());
        visibility.set(field.getNote().length() > 0);
        note.set(field.getNote());
        for (Map.Entry<Object, Object> entry : field.getOptions().entrySet()) {
            itemList.add(new SpinnerItem((String) entry.getKey(), (String) entry.getValue()));
        }
        spinnerValue.set(itemList.get(0).getValue());
    }

    public void onSelectClick() {
        SelectViewDialogManager.get().show(itemList,this);
//                getNavigator().showSpinnerView(SpinnerFragment.newInstance(itemList, this), SpinnerFragment.TAG);
    }

    @Override
    public void onItemClick(SpinnerItem spinnerItem) {
        spinnerValue.set(spinnerItem.getValue());
    }
}
