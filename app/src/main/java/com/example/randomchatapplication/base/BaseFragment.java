package com.example.randomchatapplication.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.interfaces.Providers;


public abstract class BaseFragment<B extends ViewDataBinding, VM extends BaseViewModel> extends Fragment implements Providers {
    public B binding;
    public VM viewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,getLayoutRes(),container,false);
        viewModel = ViewModelProviders.of(this).get(getViewModelClass());
//        ((MainActivity)getActivity()).refreshToolbar();
        bindData(binding);
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @LayoutRes
    public abstract int getLayoutRes();

    public abstract Class<VM> getViewModelClass();

    public abstract void bindData(B binding);

    public abstract int getToolbarType();
    // 0 - brak
    // 1 - toolbar
    // 2 - back press

    public abstract int getBackPressType();
    // 0 - cofnij o jeden
    // 1 - cofnij do menu głównego

    public int getBurger(){
        return R.drawable.ic_burger_24dp;
    }
    public int getBackPress(){
        return R.drawable.ic_arrow_back;
    }
//    public int getBlackBurger(){
//        return R.drawable.ic_burger_ciemny;
//    }
    public abstract String getToolbarName();
    public abstract float getToolbarFontSize();

}
