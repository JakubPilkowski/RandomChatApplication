package com.example.randomchatapplication.helpers;

import android.content.Context;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.widget.ImageView;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.randomchatapplication.custom_views.ArrayView;
import com.example.randomchatapplication.custom_views.DotsView;

public class BindingAdapter {


    @androidx.databinding.BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.1f)
                .into(imageView);
    }

    @androidx.databinding.BindingAdapter("effectType")
    public static void setHtmlCode(final WebView webView, final String type) {
        ViewTreeObserver viewTreeObserver = webView.getViewTreeObserver();

        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    webView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    float widthPixels = DimensionsHelper.convertPixelsToDp(webView.getWidth(), webView.getContext());
                    float heightPixels = 48.0F;
                    String htmlCode;
                    switch (type) {
                        case WebViewHelper.MOVING_BORDER_TYPE:
                            htmlCode = WebViewHelper.getMovingBorderHtmlCode(widthPixels, heightPixels);
                            break;

                        default:
                            htmlCode = "";
                    }
                    webView.loadDataWithBaseURL(null, htmlCode, "text/html", "utf-8", null);
                }
            });
        }
    }
    @androidx.databinding.BindingAdapter("viewPagerAdapter")
    public static void setViewPagerAdapter(ViewPager viewPager, FragmentPagerAdapter fragmentPagerAdapter) {
        viewPager.setAdapter(fragmentPagerAdapter);
    }


    @androidx.databinding.BindingAdapter("dotsCount")
    public static void setArrayViewItems(DotsView dotsView, int count)
    {
        dotsView.setDotsCount(count);
    }

    @androidx.databinding.BindingAdapter("currentItem")
    public static void setViewPagerCurrentItem(ViewPager viewPager, int currentItem){
        viewPager.setCurrentItem(currentItem);

    }
    @androidx.databinding.BindingAdapter("setOffScreenPageLimit")
    public static void setOffScreenPageLimit(ViewPager viewPager, int pageLimit){
        viewPager.setOffscreenPageLimit(pageLimit);
    }

    @androidx.databinding.BindingAdapter("onPageChangedListener")
    public static void setOnPageChangedListener(ViewPager viewPager, ViewPager.OnPageChangeListener listener)
    {
        viewPager.addOnPageChangeListener(listener);
    }
}
