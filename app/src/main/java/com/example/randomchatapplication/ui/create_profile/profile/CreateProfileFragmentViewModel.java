package com.example.randomchatapplication.ui.create_profile.profile;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.activites.profile_creation.CreateProfileActivity;
import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.databinding.CreateProfileFragmentBinding;
import com.example.randomchatapplication.helpers.DimensionsHelper;
import com.example.randomchatapplication.helpers.FieldsHelper;
import com.example.randomchatapplication.interfaces.SelectViewListener;
import com.example.randomchatapplication.models.Field;
import com.example.randomchatapplication.models.SpinnerItem;
import com.example.randomchatapplication.models.ViewInfo;
import com.example.randomchatapplication.ui.spinner.SpinnerFragment;
import com.example.randomchatapplication.viewmodels.FieldViewModel;

import java.util.ArrayList;
import java.util.List;

public class CreateProfileFragmentViewModel extends BaseViewModel implements SelectViewListener {
    // TODO: Implement the ViewModel

    private List<FieldViewModel> viewModels =new ArrayList<>();
    private List<Field> fields = new ArrayList<>();
    private List<ViewInfo> fieldsViews = new ArrayList<>();

    public void init(int step){
        CreateProfileActivity activity = (CreateProfileActivity) getActivity();
        fields.addAll(FieldsHelper.get().getFieldsForStep(step));
        LinearLayout fieldsContainer = ((CreateProfileFragmentBinding)getBinding()).fieldsContainer;
        fieldsViews.addAll(FieldsHelper.get().createViewsForStep(fields,fieldsContainer.getContext(),fieldsContainer));
        for (ViewInfo viewInfo: fieldsViews){
            if(fieldsViews.indexOf(viewInfo) +1 == fieldsViews.size()){
                Log.d("halo", "ostatni index");
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(0,(int) DimensionsHelper.convertDpToPixel(24,getFragment().getContext())
                        ,0, (int) DimensionsHelper.convertDpToPixel(40,getFragment().getContext()));
                fieldsContainer.addView(viewInfo.getView(),layoutParams);
            }else{
                fieldsContainer.addView(viewInfo.getView());
            }
        }

    }
    public void onValueChanged(SeekBar seekBar, final int progress, boolean fromUser) {

    }

    @Override
    public void onOpen(List<SpinnerItem> items) {
//        getNavigator().showSpinnerView(SpinnerFragment.newInstance(items, this), SpinnerFragment.TAG);
    }
}
