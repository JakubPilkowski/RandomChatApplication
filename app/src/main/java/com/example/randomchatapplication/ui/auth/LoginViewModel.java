package com.example.randomchatapplication.ui.auth;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.lifecycle.ViewModel;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.databinding.LoginFragmentBinding;

public class LoginViewModel extends BaseViewModel {
    public void init(){
        final int[] dpWidth = new int[1];
        final WebView emailWebView = ((LoginFragmentBinding)getBinding()).webViewEmail;
//        WebView passwordWebView = ((LoginFragmentBinding)getBinding()).webViewPassword;
        ViewTreeObserver viewTreeObserver = emailWebView.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    emailWebView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    dpWidth[0] = emailWebView.getWidth();
                    emailWebView.measure(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                    Float widthPixels = (float) dpWidth[0];
                    Float heightPixels = 48.0F;
                    Log.d("width", String.valueOf(widthPixels));
                    Log.d("height" , String.valueOf(heightPixels));
                    String htmlCode = "<head>\n" +
                            "    <meta charset=\"UTF-8\">\n" +
                            "    <style type=\"text/css\">\n" +
                            "        polygon {\n" +
                            "            stroke: red;\n" +
                            "            stroke-width: 3;\n" +
                            "            stroke-dasharray: 70, 910;\n" +
                            "            stroke-dashoffset: 0;\n" +
                            "            fill: none;\n" +
                            "            animation: border 5s linear infinite;\n" +
                            "        }\n" +
                            "        @keyframes border {\n" +
                            "            to {\n" +
                            "                stroke-dashoffset: -980;\n" +
                            "            }\n" +
                            "        }\n" +
                            "\n" +
                            "\n" +
                            "    </style>\n" +
                            "</head>\n" +
                            "<body>\n" +
                            "<div>\n" +
                            "    <svg width=\""+widthPixels +"\" height=\""+heightPixels +"\">\n" +
                            "        <polygon points=\"5,5 "+ (widthPixels-5) +",5 "+ (widthPixels-5)+","+(heightPixels-5)+ " 5, "+(heightPixels-5)+"\" />\n" +
                            "    </svg>\n" +
                            "</div>\n" +
                            "</body>\n" +
                            "\n" +
                            "</html>";
                    Log.d("cos"," <svg width=\""+widthPixels +"\" height=\""+heightPixels +"\">\n" +
                            "        <polygon points=\"5,5 "+ (widthPixels-5) +",5 "+ (widthPixels-5)+","+(heightPixels-5)+ " 5, "+(heightPixels-5)+"\" />\n" +
                            "    </svg>\n");
                    emailWebView.loadDataWithBaseURL(null,htmlCode,"text/html","utf-8",null);
                }
            });
        }

//        passwordWebView.loadData(htmlStart+css+htmlEnd, "text/html", null);
    }
    public float convertDpToPx(Context context, float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }
}
