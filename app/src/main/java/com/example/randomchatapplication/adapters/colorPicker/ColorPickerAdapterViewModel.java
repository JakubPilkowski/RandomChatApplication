package com.example.randomchatapplication.adapters.colorPicker;

import androidx.databinding.ObservableInt;

import com.example.randomchatapplication.base.BaseAdapterViewModel;
import com.example.randomchatapplication.helpers.ColorPickerAlert;

public class ColorPickerAdapterViewModel extends BaseAdapterViewModel {

    public ObservableInt color = new ObservableInt();
    @Override
    public void init(Object[] values) {
        color.set((int) values[0]);
    }

    public void onClick(){
        ColorPickerAlert.get().dismiss();
    }
}
