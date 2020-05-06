package com.example.randomchatapplication.ui.create_profile.profile;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.NumberPicker;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.example.randomchatapplication.activites.profile_creation.CreateProfileActivity;
import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.databinding.ActivityCreateProfileBinding;
import com.example.randomchatapplication.databinding.CreateProfileFragmentBinding;
import com.example.randomchatapplication.interfaces.SpinnerViewListener;
import com.example.randomchatapplication.models.SpinnerItem;
import com.example.randomchatapplication.ui.spinner.SpinnerFragment;

import java.util.ArrayList;
import java.util.List;

public class CreateProfileFragmentViewModel extends BaseViewModel implements SpinnerViewListener {
    // TODO: Implement the ViewModel
    public ObservableBoolean touchEnabled = new ObservableBoolean(true);
    public ObservableField<String> textAmount = new ObservableField<>();
    public ObservableInt maxLength = new ObservableInt(30);
    public ObservableField<TextWatcher> textChangeListener = new ObservableField<>();
    public ObservableField<OnRangeSeekbarChangeListener> seekBarListener = new ObservableField<>();
    public ObservableField<String> leftValue = new ObservableField<>("18 lat");
    public ObservableField<String> rightValue = new ObservableField<>("60 lat");
    public ObservableInt minDayValue = new ObservableInt(1);
    public ObservableInt maxDayValue = new ObservableInt(31);
    public ObservableInt minMonthValue = new ObservableInt(1);
    public ObservableInt maxMonthValue = new ObservableInt(12);
    public ObservableInt minYearValue = new ObservableInt(1900);
    public ObservableInt maxYearValue = new ObservableInt(2002);
    public ObservableInt dayValue = new ObservableInt(14);
    public ObservableInt monthValue = new ObservableInt(4);
    public ObservableInt yearValue = new ObservableInt(2002);
    public ObservableBoolean wrapSelector = new ObservableBoolean(true);
    public ObservableField<NumberPicker.OnValueChangeListener> numberPickerListener = new ObservableField<>();
    public ObservableField<String> spinnerValue = new ObservableField<>();

    public void init(){
        textAmount.set("0/30");
        spinnerValue.set("heteroseksualny");
        textChangeListener.set(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    textAmount.set(s.length() + "/" +maxLength.get());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        seekBarListener.set(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                leftValue.set(minValue + " lat");
                rightValue.set(maxValue + " lat");
            }
        });
        numberPickerListener.set(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Log.d("rok", String.valueOf(yearValue.get()));
                Log.d("miesiac", String.valueOf(monthValue.get()));
                Log.d("dzien", String.valueOf(dayValue.get()));
                switch ((String) picker.getTag()){
                    case "MonthPicker":
                        monthValue.set(newVal);
                        switch (picker.getValue()){
                            case 1:
                            case 3:
                            case 5:
                            case 7:
                            case 8:
                            case 10:
                            case 12:
                                maxDayValue.set(31);
                                break;
                            case 2:
                                if(yearValue.get() % 4 == 0)
                                    maxDayValue.set(29);
                                else
                                    maxDayValue.set(28);
                                break;
                            case 4:
                            case 6:
                            case 9:
                            case 11:
                                maxDayValue.set(30);
                                break;

                        }
                        break;
                    case "YearPicker":
                        yearValue.set(newVal);
                        if(picker.getValue() % 4 ==0 && monthValue.get()==2){
                            maxDayValue.set(29);
                        }
                        if(picker.getValue() % 4!= 0 && monthValue.get() ==2){
                            maxDayValue.set(28);
                        }
                        break;
                    case "DayPicker":
                        dayValue.set(newVal);
                        break;
                }
            }
        });
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
        Log.d("onItemClick", "click");
        spinnerValue.set(spinnerItem.getValue());
    }
}
