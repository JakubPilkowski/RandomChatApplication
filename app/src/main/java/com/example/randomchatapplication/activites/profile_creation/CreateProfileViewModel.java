package com.example.randomchatapplication.activites.profile_creation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.viewpager.widget.ViewPager;

import com.example.randomchatapplication.activites.main.MainActivity;
import com.example.randomchatapplication.adapters.ViewPagerListAdapter;
import com.example.randomchatapplication.api.BaseCallback;
import com.example.randomchatapplication.api.MockyConnection;
import com.example.randomchatapplication.api.RxJavaCallback;
import com.example.randomchatapplication.api.responses.FieldsResponse;
import com.example.randomchatapplication.api.responses.HobbiesAndFieldsResponse;
import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.databinding.ActivityCreateProfileBinding;
import com.example.randomchatapplication.helpers.DimensionsHelper;
import com.example.randomchatapplication.helpers.HobbiesHelper;
import com.example.randomchatapplication.helpers.ProgressDialogManager;
import com.example.randomchatapplication.models.Hobby;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class CreateProfileViewModel extends BaseViewModel {
    public ObservableInt dotsCount = new ObservableInt(5);
    private int dotPosition = 0;
    private ViewPagerListAdapter viewPagerListAdapter;
    public ObservableBoolean visibility = new ObservableBoolean(false);
    public ObservableField<ViewPagerListAdapter> viewPagerAdapter = new ObservableField<>();
    public ObservableInt windowNavigationSize = new ObservableInt();
    public ObservableInt fabMarginBottom = new ObservableInt();
    private ObservableInt statusBarHeight = new ObservableInt();

    public ObservableField<ViewPager.OnPageChangeListener> listener = new ObservableField<>();


    public void init(int windowNavigationSize, int statusBarHeight) {
        this.windowNavigationSize.set(windowNavigationSize);
        this.statusBarHeight.set(statusBarHeight);
        this.fabMarginBottom.set((int) (windowNavigationSize + DimensionsHelper.convertDpToPixel(12, getActivity().getApplicationContext())));
        MockyConnection.get().getFieldsAndHobbies(callback);
        ProgressDialogManager.get().show();
    }

    private ViewPager.OnPageChangeListener viewPagerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            ((ActivityCreateProfileBinding) getBinding()).createProfileContainer.getParent().requestDisallowInterceptTouchEvent(true);
        }

        @Override
        public void onPageSelected(int position) {
            if (dotPosition < position) {
                moveRight();
            } else if (dotPosition > position) {
                moveLeft();
            }
            dotPosition = position;
            if(position+1==dotsCount.get())
                if(!visibility.get())
                    visibility.set(true);

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private RxJavaCallback<HobbiesAndFieldsResponse> callback = new RxJavaCallback<HobbiesAndFieldsResponse>() {
        @Override
        protected void subscribeActual(@NonNull Observer<? super HobbiesAndFieldsResponse> observer) {

        }

        @Override
        public void onSuccess(HobbiesAndFieldsResponse hobbiesAndFieldsResponse) {
            Log.d("onSuccess:", "udało się");
            HobbiesHelper.init(hobbiesAndFieldsResponse.getHobbiesResponse().getZainteresowania());
            ProgressDialogManager.get().dismiss();
            viewPagerListAdapter = getNavigator().showCreateProfileFragments(hobbiesAndFieldsResponse.getFieldsResponse(), statusBarHeight.get());
            viewPagerAdapter.set(viewPagerListAdapter);
            int size = hobbiesAndFieldsResponse.getFieldsResponse().getKroki().size();
            dotsCount.set(size);
            listener.set(viewPagerListener);
        }

        @Override
        public void onSmthWrong(String message) {
            Log.d("onSmthWrong: ", message);
        }

    };


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
