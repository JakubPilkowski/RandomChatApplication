package com.example.randomchatapplication.activites.profile_creation;

import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.example.randomchatapplication.activites.main.MainActivity;
import com.example.randomchatapplication.api.BaseCallback;
import com.example.randomchatapplication.api.MockyConnection;
import com.example.randomchatapplication.api.responses.FieldsResponse;
import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.databinding.ActivityCreateProfileBinding;
import com.example.randomchatapplication.helpers.FieldsHelper;
import com.example.randomchatapplication.helpers.ProgressDialogManager;
import com.example.randomchatapplication.ui.create_profile.profile.CreateProfileFragment;

public class CreateProfileViewModel extends BaseViewModel {
    public ObservableInt dotsCount = new ObservableInt();
    public ObservableInt step = new ObservableInt();
    public ObservableField<String> stepNumber = new ObservableField<>();
    public ObservableField<String> stepTitle = new ObservableField<>();
    private ImageView dot;

    public void init() {
        MockyConnection.get().getFields(callback);
        ProgressDialogManager.get().show();
    }

    private BaseCallback<FieldsResponse> callback = new BaseCallback<FieldsResponse>() {
        @Override
        public void onSuccess(FieldsResponse response) {
            ProgressDialogManager.get().dismiss();
            FieldsHelper.init(response.getPola(), response.getKroki());
            int size = response.getKroki().size();
            for (int i = 0; i < size; i++) {
                getNavigator().addCreateProfileViewToBackStack(CreateProfileFragment.newInstance(i + 1), CreateProfileFragment.TAG + (i + 1));
            }
            dotsCount.set(size);
            step.set(1);
            getNavigator().showCreateProfile(CreateProfileFragment.TAG + "1");
            stepTitle.set(FieldsHelper.get().getSteps().get(step.get() - 1).getName());
            stepNumber.set("Krok " + step.get() + "/" + dotsCount.get());
        }

        @Override
        public void onError(String message) {
            ProgressDialogManager.get().dismiss();
        }
    };

    public void onBackClick() {
        if (step.get() > 1) {
            moveLeft();
            step.set(step.get() - 1);
            getNavigator().showCreateProfile(CreateProfileFragment.TAG + step.get());
            getNavigator().hideCreateProfile(CreateProfileFragment.TAG + (step.get() + 1));
            stepTitle.set(FieldsHelper.get().getSteps().get(step.get() - 1).getName());
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
        xFromDelta = 24 * (step.get() - 1) * 2;
        xToDelta = xFromDelta - 24 * 2;
        translateAnimation(xFromDelta, xToDelta);
    }

    private void translateAnimation(float fromXDelta, float xToDelta) {
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
        xFromDelta = 24 * (step.get() - 1) * 2;
        xToDelta = xFromDelta + 24 * 2;
        translateAnimation(xFromDelta, xToDelta);
    }

    public void onNextClick() {
        if (step.get() < dotsCount.get()) {
            moveRight();
            step.set(step.get() + 1);
            getNavigator().showCreateProfile(CreateProfileFragment.TAG + step.get());
            getNavigator().hideCreateProfile(CreateProfileFragment.TAG + (step.get() - 1));
            stepTitle.set(FieldsHelper.get().getSteps().get(step.get() - 1).getName());
            stepNumber.set("Krok " + step.get() + "/" + dotsCount.get());
        } else {
            Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
            getActivity().startActivity(intent);
            getActivity().finish();
            //rozpocznij pobranie danych i przejscie do ekranu głównego
        }
    }


}
