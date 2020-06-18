package com.example.randomchatapplication.activites.profile_creation;

import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.viewpager.widget.ViewPager;

import com.example.randomchatapplication.activites.main.MainActivity;
import com.example.randomchatapplication.adapters.ViewPagerListAdapter;
import com.example.randomchatapplication.api.BaseCallback;
import com.example.randomchatapplication.api.MockyConnection;
import com.example.randomchatapplication.api.responses.FieldsResponse;
import com.example.randomchatapplication.base.BaseFragment;
import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.databinding.ActivityCreateProfileBinding;
import com.example.randomchatapplication.helpers.DimensionsHelper;
import com.example.randomchatapplication.helpers.ProgressDialogManager;
import com.example.randomchatapplication.ui.create_profile.fields.HeaderViewModel;
import com.example.randomchatapplication.ui.create_profile.fields.SearchViewModel;
import com.example.randomchatapplication.ui.create_profile.profile.CreateProfileFragment;

public class CreateProfileViewModel extends BaseViewModel {
    public ObservableInt dotsCount = new ObservableInt(5);
    private int dotPosition = 0;
    private ViewPagerListAdapter viewPagerListAdapter;
    public ObservableField<ViewPagerListAdapter> viewPagerAdapter = new ObservableField<>();
    public ObservableInt windowNavigationSize = new ObservableInt();
    public ObservableInt fabMarginBottom = new ObservableInt();
    private ObservableInt statusBarHeight = new ObservableInt();

    public ObservableField<ViewPager.OnPageChangeListener> listener = new ObservableField<>();


    public void init(int windowNavigationSize, int statusBarHeight) {
        this.windowNavigationSize.set(windowNavigationSize);
        this.statusBarHeight.set(statusBarHeight);
        this.fabMarginBottom.set((int) (windowNavigationSize + DimensionsHelper.convertDpToPixel(16, getActivity().getApplicationContext())));
        MockyConnection.get().getFields(callback);
        ProgressDialogManager.get().show();
    }

    private ViewPager.OnPageChangeListener viewPagerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            if (dotPosition < position) {
                moveRight();
            } else if (dotPosition > position) {
                moveLeft();
            }
            dotPosition = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    private BaseCallback<FieldsResponse> callback = new BaseCallback<FieldsResponse>() {
        @Override
        public void onSuccess(FieldsResponse response) {
            ProgressDialogManager.get().dismiss();
            viewPagerListAdapter = getNavigator().showCreateProfileFragments(response, statusBarHeight.get());
            viewPagerAdapter.set(viewPagerListAdapter);
            int size = response.getKroki().size();
            dotsCount.set(size);
            listener.set(viewPagerListener);
        }

        @Override
        public void onError(String message) {
            ProgressDialogManager.get().dismiss();
        }
    };

    public void onBackClick() {
        getActivity().finish();
        getActivity().onBackPressed();
    }

    private void moveLeft() {
        float xFromDelta;
        float xToDelta;
        xFromDelta = 24 * (dotPosition) * 2;
        xToDelta = xFromDelta - 24 * 2;
        translateAnimation(xFromDelta, xToDelta);
    }

    private void translateAnimation(float fromXDelta, float xToDelta) {
        ImageView dot = ((ActivityCreateProfileBinding) getBinding()).dotsView.getMainDot();
        Animation move = new TranslateAnimation(fromXDelta, xToDelta, 0, 0);
        move.setDuration(250);
        move.setFillEnabled(true);
        move.setFillAfter(true);
        dot.startAnimation(move);
    }

    private void moveRight() {
        float xFromDelta;
        float xToDelta;
        xFromDelta = 24 * (dotPosition) * 2;
        xToDelta = xFromDelta + 24 * 2;
        translateAnimation(xFromDelta, xToDelta);
    }

    public void onNextClick() {
        Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
        getActivity().startActivity(intent);
        getActivity().finish();
    }


}
