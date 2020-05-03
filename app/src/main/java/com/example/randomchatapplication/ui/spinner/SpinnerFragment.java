package com.example.randomchatapplication.ui.spinner;

import androidx.appcompat.widget.Toolbar;
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
import com.example.randomchatapplication.activites.profile_creation.CreateProfileActivity;
import com.example.randomchatapplication.base.BaseFragment;
import com.example.randomchatapplication.databinding.SpinnerFragmentBinding;
import com.example.randomchatapplication.interfaces.Providers;
import com.example.randomchatapplication.models.SpinnerItem;
import com.example.randomchatapplication.navigation.Navigator;

import java.util.HashMap;
import java.util.List;

public class SpinnerFragment extends BaseFragment<SpinnerFragmentBinding, SpinnerViewModel> implements Providers {

    public static final String TAG = "SpinnerFragment";
    private List<SpinnerItem> values;

    public static SpinnerFragment newInstance(List<SpinnerItem> values) {
        SpinnerFragment fragment = new SpinnerFragment();
        fragment.setValues(values);
        return fragment;
    }

    private void setValues(List<SpinnerItem> values) {
        this.values = values;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.spinner_fragment;
    }

    @Override
    public Class<SpinnerViewModel> getViewModelClass() {
        return SpinnerViewModel.class;
    }

    @Override
    public void bindData(SpinnerFragmentBinding binding) {
        viewModel.setProviders(this);
        binding.setViewModel(viewModel);
        viewModel.init(values);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Toolbar toolbar = view.findViewById(R.id.toolbar);
//        ((CreateProfileActivity) getActivity()).setSupportActionBar(toolbar);
//        ((CreateProfileActivity) getActivity()).getSupportActionBar().setTitle("Orientacja");
//        ((CreateProfileActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        ((CreateProfileActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
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
        return ((CreateProfileActivity) getActivity()).navigator;
    }

    @Override
    public ViewDataBinding getBinding() {
        return binding;
    }

    @Override
    public ViewDataBinding getActivityOrFragmentBinding() {
        return ((CreateProfileActivity) getActivity()).binding;
    }

    @Override
    public BaseFragment getFragment() {
        return this;
    }
}
