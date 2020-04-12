package com.example.randomchatapplication.helpers;

import android.content.Context;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class BindingAdapter {


    @androidx.databinding.BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url)
    {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.1f)
                .into(imageView);
    }
@androidx.databinding.BindingAdapter("effectType")
    public static void setHtmlCode(final WebView webView, final String type){
    ViewTreeObserver viewTreeObserver = webView.getViewTreeObserver();

    if (viewTreeObserver.isAlive()) {
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                webView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                float widthPixels = DimensionsHelper.convertPixelsToDp(webView.getWidth(),webView.getContext());
                float heightPixels = 48.0F;
                String htmlCode;
                switch (type){
                    case WebViewHelper.MOVING_BORDER_TYPE:
                        htmlCode = WebViewHelper.getMovingBorderHtmlCode(widthPixels, heightPixels);
                        break;

                        default:
                            htmlCode = "";
                }
                webView.loadDataWithBaseURL(null,htmlCode,"text/html","utf-8",null);
            }
        });
    }

        webView.loadDataWithBaseURL(null,htmlCode,"text/html","utf-8",null);
}

}
