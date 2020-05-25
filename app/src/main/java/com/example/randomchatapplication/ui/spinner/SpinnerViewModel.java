package com.example.randomchatapplication.ui.spinner;

import androidx.databinding.ObservableField;

import com.example.randomchatapplication.adapters.spinneradapter.SpinnerViewAdapter;
import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.databinding.SpinnerFragmentBinding;
import com.example.randomchatapplication.helpers.SelectViewDialogManager;
import com.example.randomchatapplication.interfaces.DragViewListener;
import com.example.randomchatapplication.interfaces.SpinnerViewListener;
import com.example.randomchatapplication.models.SpinnerItem;

import java.util.List;

public class SpinnerViewModel extends BaseViewModel implements DragViewListener {

    public ObservableField<SpinnerViewAdapter> spinnerAdapter = new ObservableField<>();
    public ObservableField<String> layoutManager = new ObservableField<>();
    public ObservableField<DragViewListener> dragViewListener = new ObservableField<DragViewListener>(this);
    SpinnerViewAdapter adapter = new SpinnerViewAdapter();

    private SpinnerViewListener spinnerViewListener;

    public void setSpinnerViewListener(SpinnerViewListener spinnerViewListener) {
        this.spinnerViewListener = spinnerViewListener;
    }

    public void init(List<SpinnerItem> values){

        spinnerAdapter.set(adapter);
        layoutManager.set("LinearLayoutManager");
        adapter.setSpinnerViewListener(spinnerViewListener);
        adapter.setDragViewListener(this);
        adapter.setItems(values);
//        ((SpinnerFragmentBinding)getBinding()).dragView.setDragViewListener(this);
    }
    @Override
    public void onClose() {
        SelectViewDialogManager.get().dismiss();
    }

}
