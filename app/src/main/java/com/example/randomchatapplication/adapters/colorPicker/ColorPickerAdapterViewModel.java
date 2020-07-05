package com.example.randomchatapplication.adapters.colorPicker;

import android.util.Log;

import androidx.databinding.ObservableInt;

import com.example.randomchatapplication.base.BaseAdapterViewModel;
import com.example.randomchatapplication.helpers.ColorPickerAlert;
import com.example.randomchatapplication.interfaces.ColorPickerListener;

public class ColorPickerAdapterViewModel extends BaseAdapterViewModel {

    public ObservableInt color = new ObservableInt();
    private ColorPickerListener listener;
    @Override
    public void init(Object[] values) {
        color.set((int) values[0]);
        listener = (ColorPickerListener) values[1];
    }

    public void onClick(){
        ColorPickerAlert.get().dismiss();
        Log.d( "onClick: ", String.valueOf(color.get()));
        listener.onColorPicked(color.get());
    }
}
