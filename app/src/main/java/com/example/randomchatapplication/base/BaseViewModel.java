package com.example.randomchatapplication.base;

import android.app.Activity;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;

import com.example.randomchatapplication.interfaces.Providers;
import com.example.randomchatapplication.navigation.Navigator;


public abstract class BaseViewModel extends ViewModel {
    private Providers providers;

    public void setProviders(Providers providers) {
        this.providers = providers;
    }

    public Activity getActivity() {
        return providers.getActivity();
    }
    protected Navigator getNavigator(){
        return providers.getNavigator();
    }
    public ViewDataBinding getBinding(){
        return providers.getBinding();
    }
    public BaseFragment getFragment(){return providers.getFragment();}
    public ViewDataBinding getFragmentBinding(){
        return providers.getActivityOrFragmentBinding();
    }
}
