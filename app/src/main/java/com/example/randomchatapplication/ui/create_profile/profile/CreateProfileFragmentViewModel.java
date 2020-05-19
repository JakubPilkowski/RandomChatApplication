package com.example.randomchatapplication.ui.create_profile.profile;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.activites.profile_creation.CreateProfileActivity;
import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.databinding.CreateProfileFragmentBinding;
import com.example.randomchatapplication.helpers.FieldsHelper;
import com.example.randomchatapplication.models.Field;
import com.example.randomchatapplication.models.ViewInfo;
import com.example.randomchatapplication.viewmodels.FieldViewModel;

import java.util.ArrayList;
import java.util.List;

public class CreateProfileFragmentViewModel extends BaseViewModel {
    // TODO: Implement the ViewModel

    private List<FieldViewModel> viewModels =new ArrayList<>();
    private List<Field> fields = new ArrayList<>();
    private List<ViewInfo> fieldsViews = new ArrayList<>();

    public void init(int step){
        CreateProfileActivity activity = (CreateProfileActivity) getActivity();
        Log.d(CreateProfileFragment.TAG,"pobranie fieldow");
        fields.addAll(FieldsHelper.get().getFieldsForStep(step));
        Log.d(CreateProfileFragment.TAG, String.valueOf(fields.size()));
        LinearLayout fieldsContainer = ((CreateProfileFragmentBinding)getBinding()).fieldsContainer;
        fieldsViews.addAll(FieldsHelper.get().createViewsForStep(fields,fieldsContainer.getContext(),fieldsContainer));
        Log.d(CreateProfileFragment.TAG, String.valueOf(fieldsViews.size()));

//        for (ViewInfo viewInfo: fieldsViews){
//            fieldsContainer.addView(viewInfo.getView());
//        }

    }



}
