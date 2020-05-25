package com.example.randomchatapplication.adapters.spinneradapter;

import androidx.databinding.ObservableField;

import com.example.randomchatapplication.base.BaseAdapterViewModel;
import com.example.randomchatapplication.interfaces.DragViewListener;
import com.example.randomchatapplication.interfaces.SpinnerViewListener;
import com.example.randomchatapplication.models.SpinnerItem;

public class SpinnerAdapterViewModel extends BaseAdapterViewModel {

    private SpinnerItem item;
    public ObservableField<String> value = new ObservableField<>();
    private SpinnerViewListener spinnerViewListener;
    private DragViewListener dragViewListener;

    //    ObservableField<String> imgUrl = new ObservableField<>();
    @Override
    public void init(Object[] values) {
        item = (SpinnerItem) values[0];
        value.set(item.getValue());
    }

    public void setSpinnerViewListener(SpinnerViewListener spinnerViewListener) {
        this.spinnerViewListener = spinnerViewListener;
    }

    public void onClick(){
        spinnerViewListener.onItemClick(item);
        dragViewListener.onClose();
    }

    public void setDragViewListener(DragViewListener dragViewListener) {
        this.dragViewListener = dragViewListener;
    }
}
