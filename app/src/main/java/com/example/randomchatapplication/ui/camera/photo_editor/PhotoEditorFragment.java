package com.example.randomchatapplication.ui.camera.photo_editor;

import androidx.camera.core.ImageProxy;
import androidx.databinding.ViewDataBinding;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.activites.camera.CameraActivity;
import com.example.randomchatapplication.base.BaseFragment;
import com.example.randomchatapplication.databinding.PhotoEditorFragmentBinding;
import com.example.randomchatapplication.interfaces.Providers;
import com.example.randomchatapplication.navigation.Navigator;

public class PhotoEditorFragment extends BaseFragment<PhotoEditorFragmentBinding, PhotoEditorViewModel> implements Providers {

    public static final String TAG= "PhotoEditorFragment";
    private ImageProxy imageProxy;

    private void setImageProxy(ImageProxy imageProxy) {
        this.imageProxy = imageProxy;
    }

    public static PhotoEditorFragment newInstance(ImageProxy imageProxy) {
        PhotoEditorFragment fragment = new PhotoEditorFragment();
        fragment.setImageProxy(imageProxy);
        return fragment;
    }


    @Override
    public int getLayoutRes() {
        return R.layout.photo_editor_fragment;
    }

    @Override
    public Class<PhotoEditorViewModel> getViewModelClass() {
        return PhotoEditorViewModel.class;
    }

    @Override
    public void bindData(PhotoEditorFragmentBinding binding) {
        viewModel.setProviders(this);
        binding.setViewModel(viewModel);
        viewModel.init(imageProxy);
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
        return ((CameraActivity)getActivity()).navigator;
    }

    @Override
    public ViewDataBinding getBinding() {
        return binding;
    }

    @Override
    public ViewDataBinding getActivityOrFragmentBinding() {
        return ((CameraActivity)getActivity()).binding;
    }

    @Override
    public BaseFragment getFragment() {
        return this;
    }
}
