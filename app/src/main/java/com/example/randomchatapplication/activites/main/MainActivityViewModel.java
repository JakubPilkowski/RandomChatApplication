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
        View v = activity.getLayoutInflater().inflate(R.layout.menu_layout,null);
        View heartPopper = v.findViewById(R.id.heart_popper);
        View heart = new View(activity.getApplicationContext());
        activity.binding.navigationView.addView(v);
        Animation scaleDown = AnimationUtils.loadAnimation(activity.getApplicationContext(), R.anim.scale);
        heartPopper.startAnimation(scaleDown);
    }




    public void refreshToolbar() {
        switch (getFragment().getToolbarType()) {
            case 0:
                activity.setSupportActionBar(null);
//                visibility.set(View.GONE);
                break;
            case 1:
                activity.setSupportActionBar(((ActivityMainBinding) activity.getBinding()).mainToolbar);
                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
                activity.getSupportActionBar().setHomeAsUpIndicator(getFragment().getBurger());
                title.set(getFragment().getToolbarName());

                break;
            case 2:
                activity.setSupportActionBar(((ActivityMainBinding) activity.getBinding()).mainToolbar);
                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
                activity.getSupportActionBar().setHomeAsUpIndicator(getFragment().getBackPress());
                title.set(getFragment().getToolbarName());
                break;
            case 3:
                break;
        }

    }
}
