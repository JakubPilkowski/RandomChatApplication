package com.example.randomchatapplication.activites.profile_creation;

import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.randomchatapplication.adapters.ViewPagerListAdapter;
import com.example.randomchatapplication.base.BaseFragment;
import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.databinding.ActivityCreateProfileBinding;
import com.example.randomchatapplication.helpers.DimensionsHelper;
import com.example.randomchatapplication.ui.create_profile.CreateProfileFragment;

import java.util.List;

public class CreateProfileViewModel extends BaseViewModel {

    public ObservableField<ViewPagerListAdapter> pageListAdapter = new ObservableField<>();
    public ObservableInt dotsCount = new ObservableInt();
    public ObservableInt step = new ObservableInt();
    public ObservableInt currentItem = new ObservableInt();
    public ObservableField<String> stepTitle = new ObservableField<>();
    public ObservableField<ViewPager.OnPageChangeListener> pageChangedListener = new ObservableField<>();
    public ImageView dot;

    public void init() {

        ViewPagerListAdapter pageListAdapter = new ViewPagerListAdapter(((CreateProfileActivity) getActivity()).getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        pageListAdapter.addFragment(CreateProfileFragment.newInstance());
        pageListAdapter.addFragment(CreateProfileFragment.newInstance());
        pageListAdapter.addFragment(CreateProfileFragment.newInstance());
        pageListAdapter.addFragment(CreateProfileFragment.newInstance());
        this.pageListAdapter.set(pageListAdapter);
        dotsCount.set(pageListAdapter.getFragments().size());
        step.set(1);
        stepTitle.set("Krok " + step.get() + "/" + dotsCount.get());
//        initViewPagerListener();
    }

    public void initViewPagerListener() {
        ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }

    public void onBackClick() {
        if (step.get() > 1) {
            moveLeft();
            step.set(step.get() - 1);
            currentItem.set(step.get() - 1);
            stepTitle.set("Krok " + step.get() + "/" + dotsCount.get());

        } else {
            getActivity().finish();
            getActivity().onBackPressed();
            //wroc do ekranu rejestracji
        }
    }

    private void moveLeft() {
        float xFromDelta;
        float xToDelta;
        xFromDelta = 24 * (step.get() -1) *2;
        xToDelta = xFromDelta - 24 *2;
        translateAnimation(xFromDelta, xToDelta);
    }

    private void translateAnimation(float fromXDelta, float xToDelta) {
        Log.d("dane", fromXDelta + " " +xToDelta);
        dot = ((ActivityCreateProfileBinding) getBinding()).dotsView.getMainDot();
        Animation move = new TranslateAnimation(fromXDelta, xToDelta, 0, 0);
        move.setDuration(250);
        move.setFillEnabled(true);
        move.setFillAfter(true);
        dot.startAnimation(move);
    }

    private void moveRight() {
        float xFromDelta;
        float xToDelta;
        xFromDelta = 24 * (step.get()-1) *2;
        xToDelta = xFromDelta + 24 *2;
        translateAnimation(xFromDelta, xToDelta);
    }

    public void onNextClick() {
        if (step.get() < dotsCount.get()) {
            moveRight();
            step.set(step.get() + 1);
            currentItem.set(step.get() - 1);
            stepTitle.set("Krok " + step.get() + "/" + dotsCount.get());

        } else {
            //rozpocznij pobranie danych i przejscie do ekranu głównego
        }
    }


}
