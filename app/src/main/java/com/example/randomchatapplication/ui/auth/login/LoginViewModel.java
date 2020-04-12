package com.example.randomchatapplication.ui.auth.login;

import android.view.ViewTreeObserver;
import android.webkit.WebView;

import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.databinding.LoginFragmentBinding;
import com.example.randomchatapplication.helpers.DimensionsHelper;
import com.example.randomchatapplication.helpers.WebViewHelper;
import com.example.randomchatapplication.ui.auth.register.RegisterFragment;

public class LoginViewModel extends BaseViewModel {
    public void init(){
        final int[] dpWidth = new int[1];
        final WebView emailWebView = ((LoginFragmentBinding)getBinding()).webViewEmail;
        final WebView passwordWebView = ((LoginFragmentBinding)getBinding()).webViewPassword;
        ViewTreeObserver viewTreeObserver = emailWebView.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    emailWebView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    dpWidth[0] = emailWebView.getWidth();
                    float widthPixels = DimensionsHelper.convertPixelsToDp(dpWidth[0],getActivity().getApplicationContext());
                    float heightPixels = 48.0F;
                    String htmlCode = WebViewHelper.getMovingBorderHtmlCode(widthPixels, heightPixels);
                    emailWebView.loadDataWithBaseURL(null,htmlCode,"text/html","utf-8",null);
                    passwordWebView.loadDataWithBaseURL(null,htmlCode,"text/html", "utf-8", null);
                }
            });
        }

//        passwordWebView.loadData(htmlStart+css+htmlEnd, "text/html", null);
    }

    public void onLoginClick(){

    }
    public void onRegisterClick(){
        getNavigator().attach(RegisterFragment.newInstance(), RegisterFragment.TAG);
    }



}
