package com.example.randomchatapplication.helpers;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Build;
import android.text.TextWatcher;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.example.randomchatapplication.custom_views.CustomViewPager;
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
    @androidx.databinding.BindingAdapter("progressWithAnim")
    public static void setProgressWithAnim(ProgressBar progressBar, int progress){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            progressBar.setProgress(progress,true);

        }
        else{
            ObjectAnimator.ofInt(progressBar, "progress", progress)
                    .setDuration(1500)
                    .start();
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

    @androidx.databinding.BindingAdapter("swipeEnabled")
    public static void setSwipeEnabled(CustomViewPager viewPager, boolean swipeEnabled)
    {
        viewPager.setSwipeEnabled(swipeEnabled);
    }
    @androidx.databinding.BindingAdapter("onTextChangedListener")
    public static void onTextChangedListener(final EditText editText, TextWatcher textWatcher) {
        editText.addTextChangedListener(textWatcher);
    }

    @androidx.databinding.BindingAdapter("seekbarListener")
    public static void setSeekBarListener(CrystalRangeSeekbar seekbar, OnRangeSeekbarChangeListener listener)
    {
        seekbar.setOnRangeSeekbarChangeListener(listener);
    }
    @androidx.databinding.BindingAdapter("minNumberPickerValue")
    public static void setNumberPickerMinValue(NumberPicker numberPicker, int minValue)
    {
        numberPicker.setMinValue(minValue);
    }
    @androidx.databinding.BindingAdapter("maxNumberPickerValue")
    public static void setNumberPickerMaxValue(NumberPicker numberPicker, int maxValue)
    {
        numberPicker.setMaxValue(maxValue);
    }
    @androidx.databinding.BindingAdapter("numberPickerValue")
    public static void setNumberPickerValue(NumberPicker numberPicker, int value){
        numberPicker.setValue(value);
    }
    @androidx.databinding.BindingAdapter("numberPickerListener")
    public static void setNumberPickerListener(NumberPicker numberPicker, NumberPicker.OnValueChangeListener listener){
        numberPicker.setOnValueChangedListener(listener);
    }

    @androidx.databinding.BindingAdapter("recyclerViewAdapter")
    public static void setRecyclerViewAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @androidx.databinding.BindingAdapter("layoutManager")
    public static void setLayoutManager(RecyclerView recyclerView, String type) {
        switch (type){
            case "LinearLayoutManager":
                recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
                break;
            case "GridLayoutManager":
                recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 2));
                break;
        }
    }

}
