package com.example.randomchatapplication.ui.spinner;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.randomchatapplication.adapters.spinneradapter.SpinnerViewAdapter;
import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.databinding.SpinnerFragmentBinding;
import com.example.randomchatapplication.models.SpinnerItem;

import java.util.HashMap;
import java.util.List;

public class SpinnerViewModel extends BaseViewModel {

    public ObservableField<SpinnerViewAdapter> spinnerAdapter = new ObservableField<>();
    public ObservableField<String> layoutManager = new ObservableField<>();
    SpinnerViewAdapter adapter = new SpinnerViewAdapter();

    public void init(List<SpinnerItem> values){

        spinnerAdapter.set(adapter);
        layoutManager.set("LinearLayoutManager");
        adapter.setItems(values);
    }
}
