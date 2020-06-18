package com.example.randomchatapplication.ui.create_profile.profile;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;

import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.databinding.CreateProfileFragmentBinding;
import com.example.randomchatapplication.helpers.DimensionsHelper;
import com.example.randomchatapplication.helpers.FieldsHelper;
import com.example.randomchatapplication.models.Field;
import com.example.randomchatapplication.models.ViewInfo;
import com.example.randomchatapplication.ui.create_profile.fields.HeaderViewModel;
import com.example.randomchatapplication.ui.create_profile.fields.SearchViewModel;
import com.example.randomchatapplication.viewmodels.FieldViewModel;

import java.util.ArrayList;
import java.util.List;

public class CreateProfileFragmentViewModel extends BaseViewModel{
    // TODO: Implement the ViewModel

    public ObservableInt statusBarHeight = new ObservableInt();

    private List<FieldViewModel> viewModels =new ArrayList<>();
    private List<Field> fields = new ArrayList<>();
    private List<ViewInfo> fieldsViews = new ArrayList<>();
    private int step;
    public void init(int step, int statusBarHeight){
        this.step = step;
        this.statusBarHeight.set(statusBarHeight);
        fields.addAll(FieldsHelper.get().getFieldsForStep(step));
        LinearLayout fieldsContainer = ((CreateProfileFragmentBinding)getBinding()).fieldsContainer;
        fieldsViews.addAll(FieldsHelper.get().createViewsForStep(fields,fieldsContainer.getContext(),fieldsContainer, getActivity(),step));
        for (ViewInfo viewInfo: fieldsViews){
            if(fieldsViews.indexOf(viewInfo) +1 == fieldsViews.size()){
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(0,(int) DimensionsHelper.convertDpToPixel(24,getFragment().getContext())
                        ,0, (int) DimensionsHelper.convertDpToPixel(40,getFragment().getContext()));
                fieldsContainer.addView(viewInfo.getView(),layoutParams);
            }else{
                fieldsContainer.addView(viewInfo.getView());
            }
        }
    }

    public List<ViewInfo> getFieldsViews() {
        return fieldsViews;
    }

    public int getStep() {
        return step;
    }



    public ViewModel getSearchViewModel(){
        ViewModel viewModel = null;
        for (ViewInfo info :fieldsViews) {
            if(info.getViewModel() instanceof SearchViewModel)
                viewModel = info.getViewModel();
        }
        return viewModel;
    }
}
