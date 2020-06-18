package com.example.randomchatapplication.ui.create_profile.fields;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;

import com.example.randomchatapplication.models.Step;

public class HeaderViewModel extends ViewModel {

    public ObservableField<String> stepTitle = new ObservableField<>();
    public ObservableField<String> stepNumber = new ObservableField<>();
    public ObservableInt stepsSize = new ObservableInt();
    public ObservableInt progress = new ObservableInt();

    public void init(Step step, int progress, int stepsSize) {
        this.stepsSize.set(stepsSize);
        stepTitle.set(step.getName());
        stepNumber.set("Krok " + progress + "/" + stepsSize);
        this.progress.set(progress);
    }


}
