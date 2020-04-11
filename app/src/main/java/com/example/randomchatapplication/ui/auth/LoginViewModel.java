package com.example.randomchatapplication.ui.auth;

import android.webkit.WebView;

import androidx.lifecycle.ViewModel;

import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.databinding.LoginFragmentBinding;

public class LoginViewModel extends BaseViewModel {
    public void init(){

        WebView emailWebView = ((LoginFragmentBinding)getBinding()).webViewEmail;
        WebView passwordWebView = ((LoginFragmentBinding)getBinding()).webViewPassword;


        String htmlStart = "<html><head></style></head><body><div class=\"rainbow\"></div></body><style>";
        String css = "body {\n" +
                "\tdisplay: flex;\n" +
                "\tjustify-content: center;\n" +
                "\talign-items: center;\n" +
                "\theight: 100vh;\n" +
                "}\n" +
                "\n" +
                "*, *::before, *::after {\n" +
                "\tbox-sizing: border-box;\n" +
                "}\n" +
                "\n" +
                "@keyframes rotate {\n" +
                "\t100% {\n" +
                "\t\ttransform: rotate(1turn);\n" +
                "\t}\n" +
                "}\n" +
                "\n" +
                ".rainbow {\n" +
                "\tposition: relative;\n" +
                "\tz-index: 0;\n" +
                "\twidth: 400px;\n" +
                "\theight: 300px;\n" +
                "\tborder-radius: 10px;\n" +
                "\toverflow: hidden;\n" +
                "\tpadding: 2rem;\n" +
                "\t\n" +
                "\t&::before {\n" +
                "\t\tcontent: '';\n" +
                "\t\tposition: absolute;\n" +
                "\t\tz-index: -2;\n" +
                "\t\tleft: -50%;\n" +
                "\t\ttop: -50%;\n" +
                "\t\twidth: 200%;\n" +
                "\t\theight: 200%;\n" +
                "\t\tbackground-repeat: no-repeat;\n" +
                "\t\tbackground-size: 50% 50%, 50% 50%;\n" +
                "\t\tbackground-position: 0 0, 100% 0, 100% 100%, 0 100%;\n" +
                "\t\tbackground-image: linear-gradient(#399953, #399953), linear-gradient(#fff, #fff), linear-gradient(#fff, #fff), linear-gradient(#fff, #fff);\n" +
                "\t\tanimation: rotate 10s linear infinite;\n" +
                "\t}\n" +
                "\t\n" +
                "\t&::after {\n" +
                "\t\tcontent: '';\n" +
                "\t\tposition: absolute;\n" +
                "\t\tz-index: -1;\n" +
                "\t\tleft: 6px;\n" +
                "\t\ttop: 6px;\n" +
                "\t\twidth: calc(100% - 12px);\n" +
                "\t\theight: calc(100% - 12px);\n" +
                "\t\tbackground: white;\n" +
                "\t\tborder-radius: 5px;\n" +
                "\t}\n" +
                "}";
        String htmlEnd = "</style></html>";
        emailWebView.loadData(htmlStart+css+htmlEnd,"text/html",null);
        passwordWebView.loadData(htmlStart+css+htmlEnd, "text/html", null);
    }
}
