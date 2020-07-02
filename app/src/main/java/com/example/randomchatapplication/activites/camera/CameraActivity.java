package com.example.randomchatapplication.activites.camera;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.Size;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.ViewDataBinding;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.base.BaseActivity;
import com.example.randomchatapplication.base.BaseFragment;
import com.example.randomchatapplication.databinding.ActivityCameraBinding;
import com.example.randomchatapplication.helpers.ScreenHelper;
import com.example.randomchatapplication.interfaces.Providers;
import com.example.randomchatapplication.navigation.Navigator;
import com.example.randomchatapplication.ui.camera.camera.CameraFragment;
import com.example.randomchatapplication.ui.camera.photo_editor.PhotoEditorFragment;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

public class CameraActivity extends BaseActivity<ActivityCameraBinding, CameraViewModel> implements Providers {

    private int REQUEST_CODE_PERMISSIONS = 1002;
//    private int lensFacing = CameraSelector.LENS_FACING_BACK;
//    private int flashMode = ImageCapture.FLASH_MODE_OFF;
//    private PreviewView previewView;
//    private ImageCapture imgCap;
//    private Preview preview;
//    private CameraSelector cameraSelector;
    private final String[] REQUIRED_PERMISSIONS = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
//    private int windowNavigationSize;
//    private int statusBarHeight;
//    private Timer timer;

    @Override
    public boolean lightStatusBar() {
        return true;
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (allPermissionsGranted()) {
            navigator.attach(CameraFragment.newInstance(), CameraFragment.TAG);
//            bindCamera();
        } else {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        BaseFragment fragment = getCurrentFragment();
        if(fragment instanceof CameraFragment){
            ((CameraFragment)fragment).viewModel.init(((CameraFragment)fragment).flashMode.get());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                bindCamera();
                navigator.attach(CameraFragment.newInstance(), CameraFragment.TAG);
            } else {
                Toast.makeText(this, "Permissions not granted", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

//    @SuppressLint("RestrictedApi")
//    private void bindCamera() {
//        ListenableFuture cameraProviderFuture = ProcessCameraProvider.getInstance(this);
//        cameraProviderFuture.addListener(() -> {
//            try {
//                previewView = binding.viewFinder;
//                ProcessCameraProvider cameraProvider = (ProcessCameraProvider) cameraProviderFuture.get();
//                cameraProvider.unbindAll();
//                Size screen = new Size(previewView.getWidth(), previewView.getHeight());
//                preview = new Preview.Builder().build();
//                cameraSelector = new CameraSelector.Builder()
//                        .requireLensFacing(lensFacing)
//                        .build();
//                imgCap = new ImageCapture.Builder()
//                        .setFlashMode(flashMode)
//                        .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
//                        .setTargetResolution(screen)
//                        .setTargetRotation(getWindowManager().getDefaultDisplay().getRotation())
//                        .build();
//
//                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imgCap);
//                preview.setSurfaceProvider(previewView.createSurfaceProvider());
//            } catch (InterruptedException | ExecutionException e) {
//                Log.d("bindCamera: ", Objects.requireNonNull(e.getMessage()));
//            }
//        }, ContextCompat.getMainExecutor(this));
//
//    }

//    @SuppressLint("RestrictedApi")
//    public void swapCamera() {
//        lensFacing = lensFacing == CameraSelector.LENS_FACING_FRONT ? CameraSelector.LENS_FACING_BACK : CameraSelector.LENS_FACING_FRONT;
//        //            CameraX.getCameraWithLensFacing(lensFacing);
//        bindCamera();
//    }
//
//    public void toggleFlash() {
//        flashMode = flashMode == ImageCapture.FLASH_MODE_ON ? ImageCapture.FLASH_MODE_OFF : ImageCapture.FLASH_MODE_ON;
//        imgCap.setFlashMode(flashMode);
//        //        bindCamera();
//    }

//    public void takePhotoIntializer(int time) {
//        File file = new File(Environment.getExternalStorageState() + "/" + System.currentTimeMillis() + ".png");
//
//        if (time > 0) {
//            viewModel.delayTextVisibility.set(true);
//            timer = new Timer();
//            timer.schedule(new TimerTask() {
//                int counter = 0;
//
//                @Override
//                public void run() {
//                    if (counter >= time / 1000) {
//                        timer.cancel();
//                        viewModel.delayTextVisibility.set(false);
//                        takePhoto();
//                    }
//                    if (counter < time / 1000) {
//                        viewModel.delayText.set(String.valueOf(time / 1000 - counter));
//                    }
//                    counter++;
//
//                }
//            }, 0, 1000);
//        } else {
//            takePhoto();
//        }
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
//    }

//    @SuppressLint("RestrictedApi")
//    public void takePhoto() {
//        imgCap.takePicture(Runnable::run, new ImageCapture.OnImageCapturedCallback() {
//            @Override
//            public void onCaptureSuccess(@NonNull ImageProxy imageProxy) {
//                imageProxy.close();
//                Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
//                navigator.attach(PhotoEditorFragment.newInstance(), PhotoEditorFragment.TAG);
//
//                super.onCaptureSuccess(imageProxy);
//            }
//
//            @Override
//            public void onError(@NonNull ImageCaptureException exception) {
//                Toast.makeText(getApplicationContext(), "blad" + exception.getMessage(), Toast.LENGTH_SHORT).show();
//                super.onError(exception);
//            }
//        });
//    }




    private boolean allPermissionsGranted() {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void initActivity(ActivityCameraBinding binding) {
//        windowNavigationSize = ScreenHelper.getNavBarHeight(getApplicationContext());
//        statusBarHeight = ScreenHelper.getStatusBarHeight(getApplicationContext());
        binding.setViewModel(viewModel);
        viewModel.setProviders(this);
//        viewModel.init(windowNavigationSize, statusBarHeight);
    }

    @Override
    protected Class<CameraViewModel> getViewModel() {
        return CameraViewModel.class;
    }

    @Override
    public int getIdFragmentContainer() {
        return R.id.camera_activity_container;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_camera;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public Navigator getNavigator() {
        return navigator;
    }

    @Override
    public ViewDataBinding getBinding() {
        return binding;
    }

    @Override
    public ViewDataBinding getActivityOrFragmentBinding() {
        return getCurrentFragment().binding;
    }

    @Override
    public BaseFragment getFragment() {
        return null;
    }
}
