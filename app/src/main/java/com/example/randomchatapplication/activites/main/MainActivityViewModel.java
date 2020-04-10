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
    //    public ObservableInt visibility = new ObservableInt();
//    public ObservableField<String> title = new ObservableField<>();
//    public ObservableField<Drawable> background = new ObservableField<>();
//    public ObservableFloat textSize = new ObservableFloat();
//    public ObservableInt textColor = new ObservableInt();

//    private BaseFragment fragment = ((MainActivity) getActivity()).getCurrentFragment();
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
//        switch (fragment.getToolbarType()) {
//            case 0:
//                activity.setSupportActionBar(null);
//                visibility.set(View.GONE);
//                break;
//            case 1:
//                activity.setSupportActionBar(((ActivityMainBinding) activity.getBinding()).mainToolbar);
//                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//                activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
//                activity.getSupportActionBar().setHomeAsUpIndicator(fragment.getBlackBurger());
//                title.set(fragment.getToolbarName());
//                background.set(fragment.getContext().getDrawable(R.drawable.white_background));
//                textSize.set(TextHelper.getPixels(TypedValue.COMPLEX_UNIT_SP, fragment.getToolbarFontSize()));
//                textColor.set(ContextCompat.getColor(fragment.getContext(), R.color.colorBlack));
//                visibility.set(View.VISIBLE);
//                break;
//            case 2:
//                activity.setSupportActionBar(((ActivityMainBinding) activity.getBinding()).toolbar);
//                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//                activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
//                activity.getSupportActionBar().setHomeAsUpIndicator(fragment.getWhiteBurger());
//                title.set(fragment.getToolbarName());
//                background.set(fragment.getContext().getDrawable(R.drawable.black_background));
//                textSize.set(TextHelper.getPixels(TypedValue.COMPLEX_UNIT_SP,fragment.getToolbarFontSize()));
//                textColor.set(ContextCompat.getColor(fragment.getContext(),R.color.colorWhite));
//                visibility.set(View.VISIBLE);
//                break;
//            case 3:
//                break;
//        }

    }
}
