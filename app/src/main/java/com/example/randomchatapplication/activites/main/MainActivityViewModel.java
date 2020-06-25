package com.example.randomchatapplication.activites.main;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableFloat;
import androidx.databinding.ObservableInt;

import com.example.randomchatapplication.R;

import com.example.randomchatapplication.base.BaseFragment;
import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.databinding.ActivityMainBinding;


public class MainActivityViewModel extends BaseViewModel {
    public ObservableField<String> title = new ObservableField<>();

    private MainActivity activity ;
    public void init() {
        activity = ((MainActivity) getActivity());
    }
}
