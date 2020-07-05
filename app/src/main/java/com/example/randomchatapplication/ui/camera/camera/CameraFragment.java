package com.example.randomchatapplication.ui.camera.camera;

import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.activites.camera.CameraActivity;
import com.example.randomchatapplication.base.BaseFragment;
import com.example.randomchatapplication.databinding.CameraFragmentBinding;
import com.example.randomchatapplication.helpers.ScreenHelper;
import com.example.randomchatapplication.interfaces.Providers;
import com.example.randomchatapplication.navigation.Navigator;
import com.example.randomchatapplication.ui.camera.photo_editor.PhotoEditorFragment;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

public class CameraFragment extends BaseFragment<CameraFragmentBinding, CameraFragmentViewModel> implements Providers {

    public static final String TAG = "CameraFragment";

    private ObservableInt lensFacing = new ObservableInt(CameraSelector.LENS_FACING_BACK);
    public ObservableInt flashMode = new ObservableInt(ImageCapture.FLASH_MODE_OFF);
    private PreviewView previewView;
    private ImageCapture imgCap;
    private Preview preview;
    private CameraSelector cameraSelector;
    private Timer timer;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static CameraFragment newInstance() {
        return new CameraFragment();
    }


    @SuppressLint("RestrictedApi")
    public void bindCamera() {
        ListenableFuture cameraProviderFuture = ProcessCameraProvider.getInstance(getActivity().getApplicationContext());
        cameraProviderFuture.addListener(() -> {
            try {
                previewView = binding.viewFinder;
                ProcessCameraProvider cameraProvider = (ProcessCameraProvider) cameraProviderFuture.get();
                cameraProvider.unbindAll();
                Size screen = new Size(previewView.getWidth(), previewView.getHeight());
                preview = new Preview.Builder().build();
                cameraSelector = new CameraSelector.Builder()
                        .requireLensFacing(lensFacing.get())
                        .build();
                imgCap = new ImageCapture.Builder()
                        .setFlashMode(flashMode.get())
                        .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                        .setTargetResolution(screen)
                        .setTargetRotation(getActivity().getWindowManager().getDefaultDisplay().getRotation())
                        .build();
                cameraProvider.bindToLifecycle(getViewLifecycleOwner(), cameraSelector, preview, imgCap);
                preview.setSurfaceProvider(previewView.createSurfaceProvider());
            } catch (InterruptedException | ExecutionException e) {
                Log.d("bindCamera: ", Objects.requireNonNull(e.getMessage()));
            }
        }, ContextCompat.getMainExecutor(getActivity()));
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (timer != null) {
            viewModel.delayTextVisibility.set(false);
            timer.cancel();
        }
    }

    @SuppressLint("RestrictedApi")
    void swapCamera() {
        lensFacing.set(lensFacing.get() == CameraSelector.LENS_FACING_FRONT ? CameraSelector.LENS_FACING_BACK : CameraSelector.LENS_FACING_FRONT);
        bindCamera();
    }

    void toggleFlash() {
        flashMode.set(flashMode.get() == ImageCapture.FLASH_MODE_ON ? ImageCapture.FLASH_MODE_OFF : ImageCapture.FLASH_MODE_ON);
        imgCap.setFlashMode(flashMode.get());
    }

    void takePhotoIntializer(int time) {
        File file = new File(Environment.getExternalStorageState() + "/" + System.currentTimeMillis() + ".png");
        if (time > 0) {
            viewModel.delayTextVisibility.set(true);
            timer = new Timer();
            timer.schedule(new TimerTask() {
                int counter = 0;

                @Override
                public void run() {
                    if (counter >= time / 1000) {
                        timer.cancel();
                        timer = null;
                        viewModel.delayTextVisibility.set(false);
                        takePhoto();
                    }
                    if (counter < time / 1000) {
                        viewModel.delayText.set(String.valueOf(time / 1000 - counter));
                    }
                    counter++;

                }
            }, 0, 1000);
        } else {
            takePhoto();
        }
//        imgCap.takePicture(file, new ImageCapture.OnImageSavedListener() {
//            @Override
//            public void onImageSaved(@NonNull File file) {
//
//            }
//
//            @Override
//            public void onError(@NonNull ImageCapture.UseCaseError useCaseError, @NonNull String message, @Nullable Throwable cause) {
//
//            }
//        });
    }

    @SuppressLint("RestrictedApi")
    private void takePhoto() {

        imgCap.takePicture(Runnable::run, new ImageCapture.OnImageCapturedCallback() {
            @Override
            public void onCaptureSuccess(@NonNull ImageProxy image) {
                if(viewModel.state.get())
                    viewModel.onSettingsClick();
                getNavigator().attach(PhotoEditorFragment.newInstance(image), PhotoEditorFragment.TAG);
            }

            @Override
            public void onError(@NonNull ImageCaptureException exception) {
                super.onError(exception);
            }
        });
    }


    @Override
    public Navigator getNavigator() {
        return ((CameraActivity) getActivity()).navigator;
    }

    @Override
    public ViewDataBinding getBinding() {
        return binding;
    }

    @Override
    public ViewDataBinding getActivityOrFragmentBinding() {
        return ((CameraActivity) getActivity()).binding;
    }

    @Override
    public BaseFragment getFragment() {
        return this;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.camera_fragment;
    }

    @Override
    public Class<CameraFragmentViewModel> getViewModelClass() {
        return CameraFragmentViewModel.class;
    }

    @Override
    public void bindData(CameraFragmentBinding binding) {
        binding.setViewModel(viewModel);
        viewModel.setProviders(this);
        bindCamera();
        viewModel.init(flashMode.get());
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
}
