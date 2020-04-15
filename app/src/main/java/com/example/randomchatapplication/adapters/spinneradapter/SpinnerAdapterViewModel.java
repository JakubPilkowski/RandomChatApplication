package com.example.randomchatapplication.adapters.spinneradapter;

import androidx.databinding.ObservableField;

import com.example.randomchatapplication.base.BaseAdapterViewModel;
import com.example.randomchatapplication.models.SpinnerItem;

import java.util.HashMap;

public class SpinnerAdapterViewModel extends BaseAdapterViewModel {

    private SpinnerItem item;
    public ObservableField<String> value = new ObservableField<>();
//    ObservableField<String> imgUrl = new ObservableField<>();
    @Override
    public void init(Object[] values) {
        item = (SpinnerItem) values[0];
        value.set(item.getValue());
    }

    public void onClick(){

    }
}
