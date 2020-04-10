package com.example.randomchatapplication.interfaces;

import android.app.Activity;

import androidx.databinding.ViewDataBinding;

import com.example.randomchatapplication.base.BaseFragment;
import com.example.randomchatapplication.navigation.Navigator;


public interface Providers {
    Activity getActivity();
    Navigator getNavigator();
    ViewDataBinding getBinding();
    ViewDataBinding getActivityOrFragmentBinding();
    BaseFragment getFragment();
}
