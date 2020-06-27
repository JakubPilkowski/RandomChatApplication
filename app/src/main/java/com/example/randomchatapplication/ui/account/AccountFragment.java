package com.example.randomchatapplication.ui.account;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.activites.main.MainActivity;
import com.example.randomchatapplication.base.BaseFragment;
import com.example.randomchatapplication.databinding.AccountFragmentBinding;
import com.example.randomchatapplication.interfaces.Providers;
import com.example.randomchatapplication.navigation.Navigator;

public class AccountFragment extends BaseFragment<AccountFragmentBinding,AccountViewModel> implements Providers {

    public static final String TAG = "AccountFragment";

    public static AccountFragment newInstance() {
        return new AccountFragment();
    }


    @Override
    public int getLayoutRes() {
        return R.layout.account_fragment;
    }

    @Override
    public Class<AccountViewModel> getViewModelClass() {
        return AccountViewModel.class;
    }

    @Override
    public void bindData(AccountFragmentBinding binding) {
        binding.setViewModel(viewModel);
        viewModel.setProviders(this);
        viewModel.init();
    }

    @Override
    public int getToolbarType() {
        return 0;
    }

    @Override
    public int getBackPressType() {
        return 0;
    }

    @Override
    public String getToolbarName() {
        return null;
    }

    @Override
    public float getToolbarFontSize() {
        return 0;
    }

    @Override
    public Navigator getNavigator() {
        return ((MainActivity)getActivity()).navigator;
    }

    @Override
    public ViewDataBinding getBinding() {
        return binding;
    }

    @Override
    public ViewDataBinding getActivityOrFragmentBinding() {
        return ((MainActivity)getActivity()).binding;
    }

    @Override
    public BaseFragment getFragment() {
        return this;
    }
}
