package com.example.randomchatapplication.ui.create_profile.fields;

import android.util.Log;

import androidx.databinding.ObservableField;

import com.example.randomchatapplication.interfaces.SpinnerViewListener;
import com.example.randomchatapplication.models.Field;
import com.example.randomchatapplication.models.SpinnerItem;
import com.example.randomchatapplication.ui.spinner.SpinnerFragment;
import com.example.randomchatapplication.viewmodels.FieldViewModel;

import java.util.ArrayList;
import java.util.List;

public class SelectViewModel extends FieldViewModel implements SpinnerViewListener {

    public ObservableField<String> spinnerValue = new ObservableField<>();
    public ObservableField<String> title = new ObservableField<>();

    public void init(Field field){

    }
    public void onSelectClick(){
        List<SpinnerItem> itemList = new ArrayList<>();

        itemList.add(new SpinnerItem("hetero", "heteroseksualny","https://image.flaticon.com/icons/svg/2012/2012064.svg"));
        itemList.add(new SpinnerItem("homo", "homoseksualny","https://image.flaticon.com/icons/svg/458/458860.svg"));
        itemList.add(new SpinnerItem("bi", "biseksualny","https://image.flaticon.com/icons/svg/1864/1864625.svg"));
        itemList.add(new SpinnerItem("tajemnica", "tajemnica", "https://image.flaticon.com/icons/svg/583/583673.svg"));
        itemList.add(new SpinnerItem("cos innego","cos innego", "https://image.flaticon.com/icons/svg/724/724979.svg"));
        itemList.add(new SpinnerItem("cos innego","cos innego", "https://image.flaticon.com/icons/svg/724/724979.svg"));
        itemList.add(new SpinnerItem("cos innego","cos innego", "https://image.flaticon.com/icons/svg/724/724979.svg"));
        itemList.add(new SpinnerItem("cos innego","cos innego", "https://image.flaticon.com/icons/svg/724/724979.svg"));
        itemList.add(new SpinnerItem("cos innego","cos innego", "https://image.flaticon.com/icons/svg/724/724979.svg"));
        itemList.add(new SpinnerItem("cos innego","cos innego", "https://image.flaticon.com/icons/svg/724/724979.svg"));
        itemList.add(new SpinnerItem("cos innego","cos innego", "https://image.flaticon.com/icons/svg/724/724979.svg"));
        itemList.add(new SpinnerItem("cos innego","cos innego", "https://image.flaticon.com/icons/svg/724/724979.svg"));
        itemList.add(new SpinnerItem("cos innego","cos innego", "https://image.flaticon.com/icons/svg/724/724979.svg"));
        getNavigator().showSpinnerView(SpinnerFragment.newInstance(itemList,this),SpinnerFragment.TAG);
//        touchEnabled.set(false);


    }
    @Override
    public void onItemClick(SpinnerItem spinnerItem) {

        spinnerValue.set(spinnerItem.getValue());
    }
}
