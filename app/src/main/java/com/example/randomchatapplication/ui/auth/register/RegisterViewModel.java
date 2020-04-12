package com.example.randomchatapplication.ui.auth.register;

import androidx.databinding.ObservableField;
import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.helpers.WebViewHelper;

public class RegisterViewModel extends BaseViewModel {
    // TODO: Implement the ViewModel
    public ObservableField<String> htmlEffect = new ObservableField<>(WebViewHelper.MOVING_BORDER_TYPE);

    public void init(){

    }

    public void onNextClick(){

    }
    public void onBackClick(){
        getActivity().onBackPressed();
    }
}
