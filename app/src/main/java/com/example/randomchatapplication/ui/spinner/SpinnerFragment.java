package com.example.randomchatapplication.ui.spinner;

import androidx.databinding.ViewDataBinding;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.activites.profile_creation.CreateProfileActivity;
import com.example.randomchatapplication.base.BaseFragment;
import com.example.randomchatapplication.databinding.SpinnerFragmentBinding;
import com.example.randomchatapplication.interfaces.Providers;
import com.example.randomchatapplication.interfaces.SpinnerViewListener;
import com.example.randomchatapplication.models.SpinnerItem;
import com.example.randomchatapplication.navigation.Navigator;

import java.util.List;

public class SpinnerFragment extends BaseFragment<SpinnerFragmentBinding, SpinnerViewModel> implements Providers {

    public static final String TAG = "SpinnerFragment";
    private List<SpinnerItem> values;
    private SpinnerViewListener spinnerViewListener;
    public static SpinnerFragment newInstance(List<SpinnerItem> values, SpinnerViewListener spinnerViewListener) {
        SpinnerFragment fragment = new SpinnerFragment();
        fragment.setValues(values);
        fragment.setSpinnerViewListener(spinnerViewListener);
        return fragment;
    }

    private void setSpinnerViewListener(SpinnerViewListener spinnerViewListener) {
        this.spinnerViewListener = spinnerViewListener;
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
//        viewModel.setDialogFragment(this);
        binding.setViewModel(viewModel);
        viewModel.setSpinnerViewListener(spinnerViewListener);
        viewModel.init(values);
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

