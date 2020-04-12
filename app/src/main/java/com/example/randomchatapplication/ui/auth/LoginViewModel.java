package com.example.randomchatapplication.ui.auth;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.databinding.LoginFragmentBinding;

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
                    float widthPixels = convertPixelsToDp(dpWidth[0],getActivity().getApplicationContext());
                    Float heightPixels = 48.0F;
                    String htmlCode = "<head>\n" +
                            "    <meta charset=\"UTF-8\">\n" +
                            "    <style type=\"text/css\">\n" +
                            "           html, body, div {\n" +
                            "            margin: 0;\n" +
                            "            padding: 0;\n" +
                            "            border: 0;\n" +
                            "        }" +
                            "        polygon {\n" +
                            "            stroke: #bc1e1b;\n" +
                            "            stroke-width: 4;\n" +
                            "            stroke-dasharray: "+widthPixels +","+ (widthPixels*2+heightPixels*2-widthPixels) +";\n" +
                            "            stroke-dashoffset: 0;\n" +
                            "            fill: none;\n" +
                            "            animation: border 4s linear infinite;\n" +
                            "        }\n" +
                            "        @keyframes border {\n" +
                            "            to {\n" +
                            "                stroke-dashoffset: -"+(widthPixels*2+heightPixels*2) +";\n" +
                            "            }\n" +
                            "        }\n" +
                            "\n" +
                            "\n" +
                            "    </style>\n" +
                            "</head>\n" +
                            "<body >\n" +
                            "<div>\n" +
                            "    <svg width=\""+widthPixels +"\" height=\""+heightPixels +"\">\n" +
                            "        <polygon points=\"0,0 "+ (widthPixels) +",0 "+ (widthPixels)+","+(heightPixels)+ " 0,"+(heightPixels)+"\" />\n" +
                            "    </svg>\n" +
                            "</div>\n" +
                            "</body>\n" +
                            "\n" +
                            "</html>";
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

    }

    public float convertDpToPx(Context context, float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }
    public static float convertPixelsToDp(float px, Context context){
        return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
}
