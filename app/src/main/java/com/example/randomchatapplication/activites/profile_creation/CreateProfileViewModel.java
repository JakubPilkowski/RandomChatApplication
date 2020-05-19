package com.example.randomchatapplication.activites.profile_creation;

import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ScrollView;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.randomchatapplication.adapters.ViewPagerListAdapter;
import com.example.randomchatapplication.api.BaseCallback;
import com.example.randomchatapplication.api.MockyConnection;
import com.example.randomchatapplication.api.responses.FieldsResponse;
import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.databinding.ActivityCreateProfileBinding;
import com.example.randomchatapplication.helpers.FieldsHelper;
import com.example.randomchatapplication.helpers.ProgressDialogManager;
import com.example.randomchatapplication.models.Field;
import com.example.randomchatapplication.ui.create_profile.profile.CreateProfileFragment;

public class CreateProfileViewModel extends BaseViewModel {

    public ObservableField<ViewPagerListAdapter> pageListAdapter = new ObservableField<>();
    public ObservableInt dotsCount = new ObservableInt();
    public ObservableInt step = new ObservableInt();
    public ObservableInt currentItem = new ObservableInt();
    public ObservableField<String> stepNumber = new ObservableField<>();
    public ObservableField<String> stepTitle = new ObservableField<>();
    public ObservableBoolean swipeEnabled = new ObservableBoolean(false);
    public ImageView dot;

    public void init() {

//        ViewPagerListAdapter pageListAdapter = new ViewPagerListAdapter(((CreateProfileActivity) getActivity()).getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
//        pageListAdapter.addFragment(CreateProfileFragment.newInstance());
//        pageListAdapter.addFragment(CreateProfileFragment.newInstance());
//        pageListAdapter.addFragment(CreateProfileFragment.newInstance());
//        pageListAdapter.addFragment(CreateProfileFragment.newInstance());
//        this.pageListAdapter.set(pageListAdapter);




        MockyConnection.get().getFields(callback);

        ProgressDialogManager.get().show();
    }

    private BaseCallback<FieldsResponse> callback = new BaseCallback<FieldsResponse>() {
        @Override
        public void onSuccess(FieldsResponse response) {
            ProgressDialogManager.get().dismiss();
            Log.d("response", response.getKroki().toString());
            FieldsHelper.init(response.getPola(), response.getKroki());
            int size = response.getKroki().size();
            for(int i =0; i<size;i++){
                getNavigator().addCreateProfileViewToBackStack(CreateProfileFragment.newInstance(i+1), CreateProfileFragment.TAG+(i+1));
            }

            dotsCount.set(size);
            step.set(1);
           getNavigator().showCreateProfile(CreateProfileFragment.TAG+"1");
            stepTitle.set(FieldsHelper.get().getSteps().get(step.get()-1).getName());
            stepNumber.set("Krok " + step.get() + "/" + dotsCount.get());
        }

        @Override
        public void onError(String message) {
            ProgressDialogManager.get().dismiss();
            Log.d("response", "error "+message);
        }
    };

    public void onBackClick() {
        if (step.get() > 1) {
            moveLeft();

            step.set(step.get() - 1);
            getNavigator().showCreateProfile(CreateProfileFragment.TAG+step.get());
            getNavigator().hideCreateProfile(CreateProfileFragment.TAG+(step.get()+1));
            //            currentItem.set(step.get() - 1);
            stepTitle.set(FieldsHelper.get().getSteps().get(step.get()-1).getName());
            stepNumber.set("Krok " + step.get() + "/" + dotsCount.get());

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
        dot = ((ActivityCreateProfileBinding)getBinding()).dotsView.getMainDot();
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
//        ScrollView scrollView =((CreateProfileHomeFragmentBinding)getBinding()).createProfileScrollView;
        if (step.get() < dotsCount.get()) {
//            scrollView.fullScroll(ScrollView.FOCUS_UP);
            moveRight();
            step.set(step.get() + 1);
            getNavigator().showCreateProfile(CreateProfileFragment.TAG+step.get());
            getNavigator().hideCreateProfile(CreateProfileFragment.TAG+(step.get()-1));
//            currentItem.set(step.get() - 1);
            stepTitle.set(FieldsHelper.get().getSteps().get(step.get()-1).getName());
            stepNumber.set("Krok " + step.get() + "/" + dotsCount.get());

        } else {
            //rozpocznij pobranie danych i przejscie do ekranu głównego
        }
    }


}
