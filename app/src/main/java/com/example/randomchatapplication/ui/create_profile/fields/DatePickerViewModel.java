package com.example.randomchatapplication.ui.create_profile.fields;

import android.util.Log;
import android.widget.NumberPicker;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.example.randomchatapplication.models.Field;
import com.example.randomchatapplication.viewmodels.FieldViewModel;

public class DatePickerViewModel extends FieldViewModel {


    public ObservableField<String>title = new ObservableField<>();
    public ObservableBoolean visibility = new ObservableBoolean(true);
    public ObservableField<String>note = new ObservableField<>();
    public ObservableInt minDayValue = new ObservableInt(1);
    public ObservableInt maxDayValue = new ObservableInt(31);
    public ObservableInt minMonthValue = new ObservableInt(1);
    public ObservableInt maxMonthValue = new ObservableInt(12);
    public ObservableInt minYearValue = new ObservableInt(1900);
    public ObservableInt maxYearValue = new ObservableInt(2002);
    public ObservableInt dayValue = new ObservableInt(14);
    public ObservableInt monthValue = new ObservableInt(4);
    public ObservableInt yearValue = new ObservableInt(2002);
    public ObservableField<NumberPicker.OnValueChangeListener> numberPickerListener = new ObservableField<>();

    public void init(Field field){
        title.set(field.getTitle());
        visibility.set(field.getNote().length() > 0);
        note.set(field.getNote());
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

}
