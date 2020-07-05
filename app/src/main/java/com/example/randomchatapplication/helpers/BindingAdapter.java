package com.example.randomchatapplication.helpers;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.TooltipCompat;
import androidx.camera.core.ImageProxy;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.example.randomchatapplication.R;
import com.example.randomchatapplication.custom_views.CustomRangeSeekbar;
import com.example.randomchatapplication.custom_views.CustomViewPager;
import com.example.randomchatapplication.custom_views.DotsView;
import com.example.randomchatapplication.custom_views.DragView;
import com.example.randomchatapplication.databinding.ActivitySearchViewBinding;
import com.example.randomchatapplication.databinding.HobbyItemBinding;
import com.example.randomchatapplication.interfaces.DragViewListener;
import com.example.randomchatapplication.models.Hobby;
import com.example.randomchatapplication.ui.create_profile.fields.SearchViewModel;
import com.example.randomchatapplication.viewmodels.HobbyViewModel;

import java.nio.ByteBuffer;
import java.util.List;

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
    public static void setProgressWithAnim(ProgressBar progressBar, int progress) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            progressBar.setProgress(progress, true);

        } else {
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
    public static void setArrayViewItems(DotsView dotsView, int count) {
        dotsView.setDotsCount(count);
    }

    @androidx.databinding.BindingAdapter("currentItem")
    public static void setViewPagerCurrentItem(ViewPager viewPager, int currentItem) {
        viewPager.setCurrentItem(currentItem);

    }

    @androidx.databinding.BindingAdapter("pageChangedListener")
    public static void setPageChangedListener(ViewPager viewPager, ViewPager.OnPageChangeListener listener) {
        viewPager.addOnPageChangeListener(listener);
    }


    @androidx.databinding.BindingAdapter("setOffScreenPageLimit")
    public static void setOffScreenPageLimit(ViewPager viewPager, int pageLimit) {
        viewPager.setOffscreenPageLimit(pageLimit);
    }

    @androidx.databinding.BindingAdapter("swipeEnabled")
    public static void setSwipeEnabled(CustomViewPager viewPager, boolean swipeEnabled) {
        viewPager.setSwipeEnabled(swipeEnabled);
    }

    @androidx.databinding.BindingAdapter("onTextChangedListener")
    public static void onTextChangedListener(final EditText editText, TextWatcher textWatcher) {
        editText.addTextChangedListener(textWatcher);
    }

    @androidx.databinding.BindingAdapter("seekbarListener")
    public static void setSeekBarListener(CrystalRangeSeekbar seekbar, OnRangeSeekbarChangeListener listener) {
        seekbar.setOnRangeSeekbarChangeListener(listener);
    }

    @androidx.databinding.BindingAdapter("minNumberPickerValue")
    public static void setNumberPickerMinValue(NumberPicker numberPicker, int minValue) {
        numberPicker.setMinValue(minValue);
    }

    @androidx.databinding.BindingAdapter("maxNumberPickerValue")
    public static void setNumberPickerMaxValue(NumberPicker numberPicker, int maxValue) {
        numberPicker.setMaxValue(maxValue);
    }

    @androidx.databinding.BindingAdapter("numberPickerValue")
    public static void setNumberPickerValue(NumberPicker numberPicker, int value) {
        numberPicker.setValue(value);
    }

    @androidx.databinding.BindingAdapter("numberPickerListener")
    public static void setNumberPickerListener(NumberPicker numberPicker, NumberPicker.OnValueChangeListener listener) {
        numberPicker.setOnValueChangedListener(listener);
    }

    @androidx.databinding.BindingAdapter("recyclerViewAdapter")
    public static void setRecyclerViewAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @androidx.databinding.BindingAdapter("layoutManager")
    public static void setLayoutManager(RecyclerView recyclerView, String type) {
        switch (type) {
            case "LinearLayoutManager":
                recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
                break;
            case "GridLayoutManager":
                recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 2));
                break;
        }
    }

    @androidx.databinding.BindingAdapter("visibility")
    public static void setVisibility(View view, boolean visibility) {
        view.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    @androidx.databinding.BindingAdapter("dragViewListener")
    public static void setDragViewListener(DragView view, DragViewListener listener) {
        view.setDragViewListener(listener);
    }

    @androidx.databinding.BindingAdapter("rangeSeekbarGap")
    public static void setRangeSeekbarGap(CustomRangeSeekbar seekbar, float gap) {
        seekbar.setGap(gap * 2);
    }

    @androidx.databinding.BindingAdapter("rangeSeekbarMin")
    public static void setRangeSeekbarMinValue(CustomRangeSeekbar seekbar, float min) {
        seekbar.setMinValue(min);
    }

    @androidx.databinding.BindingAdapter("rangeSeekbarMax")
    public static void setRangeSeekbarMaxValue(CustomRangeSeekbar seekbar, float max) {
        seekbar.setMaxValue(max);
    }

    @androidx.databinding.BindingAdapter("checkChangedListener")
    public static void setOnCheckChangedListener(CheckBox checkBox, CompoundButton.OnCheckedChangeListener listener) {
        checkBox.setOnCheckedChangeListener(listener);
    }

    @androidx.databinding.BindingAdapter("onQueryTextListener")
    public static void setOnQueryTextListener(SearchView searchView, SearchView.OnQueryTextListener listener) {
        searchView.setOnQueryTextListener(listener);
    }

    private static boolean isAnimated = false;

    @androidx.databinding.BindingAdapter("searchButtonAnimation")
    public static void setSearchButtonAnimation(RecyclerView recyclerView, ViewDataBinding binding) {
        final Button button = ((ActivitySearchViewBinding) binding).hobbiesSearchButton;
        final Animation[] animation = new Animation[1];
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy < -12 && !button.isShown() && !isAnimated) {
                    animation[0] = AnimationUtils.loadAnimation(recyclerView.getContext(), R.anim.translate_up);
                    animation[0].setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            button.setVisibility(View.VISIBLE);
                            isAnimated = true;
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            isAnimated = false;
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            onAnimationEnd(animation);
                        }
                    });
                    button.startAnimation(animation[0]);
                } else if (dy > 12 && button.isShown() && !isAnimated) {
                    animation[0] = AnimationUtils.loadAnimation(recyclerView.getContext(), R.anim.translate_down);
                    animation[0].setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            isAnimated = true;
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            button.setVisibility(View.GONE);
                            isAnimated = false;
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            onAnimationEnd(animation);
                        }
                    });
                    button.startAnimation(animation[0]);
                }
            }
        });
    }

    @androidx.databinding.BindingAdapter("drawableEndVisibility")
    public static void setDrawableEndVisibility(TextView textView, boolean visibility) {
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, visibility ? R.drawable.ic_checked : 0, 0);
    }

    @androidx.databinding.BindingAdapter({"updateHobbies", "searchViewModel"})
    public static void setUpdateHobbies(LinearLayout linearLayout, List<Hobby> hobbies, SearchViewModel searchViewModel) {
        if (hobbies.size() > 0) {
            linearLayout.removeAllViews();
            for (Hobby hobby : hobbies) {
                View hobbyView = LayoutInflater.from(linearLayout.getContext()).inflate(R.layout.hobby_item, linearLayout, false);
                HobbyItemBinding binding = HobbyItemBinding.bind(hobbyView);
                HobbyViewModel viewModel = new HobbyViewModel();
                binding.setViewModel(viewModel);
                viewModel.init(hobby, hobbies, searchViewModel);
                linearLayout.addView(hobbyView);
            }
        } else {
            linearLayout.removeAllViews();
        }
    }

    @androidx.databinding.BindingAdapter("marginBottom")
    public static void setLayoutMarginBottom(View view, int value) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        layoutParams.bottomMargin = value;
    }

    @androidx.databinding.BindingAdapter("marginTop")
    public static void setLayoutMarginTop(View view, int value) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        layoutParams.topMargin = value;
    }


    @androidx.databinding.BindingAdapter("windowStatusBarPadding")
    public static void setWindowStatusBarPadding(View view, int top) {
        view.setPadding(0, top, 0, 0);
    }


    @androidx.databinding.BindingAdapter("measuredHeight")
    public static void setViewHeight(View view, int height) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = height;
    }

    @androidx.databinding.BindingAdapter("iconified")
    public static void setIconified(SearchView view, boolean iconified) {
        Log.d("iconified", "halo halo");
        view.setIconified(iconified);
    }

    @androidx.databinding.BindingAdapter("query")
    public static void setQuery(SearchView view, String query) {
        view.setQuery(query, false);
    }

    @androidx.databinding.BindingAdapter("cancelListener")
    public static void setCancelListenr(SearchView view, SearchView.OnCloseListener listener) {
        view.setOnCloseListener(listener);
    }

    @androidx.databinding.BindingAdapter("imeOption")
    public static void setImeOption(EditText editText, String option) {
        switch (option) {
            case "done":
                editText.setImeOptions(EditorInfo.IME_ACTION_DONE);
                break;
            case "next":
                editText.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                break;
            default:
                break;
        }
    }

    @androidx.databinding.BindingAdapter("keyboardType")
    public static void setKeyboardType(EditText editText, String keyboardType) {
        switch (keyboardType) {
            case "multiline":
                editText.setInputType(InputType.TYPE_TEXT_FLAG_IME_MULTI_LINE);
                break;
            case "text":
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            default:
                break;
        }
    }

    @androidx.databinding.BindingAdapter("backgroundDrawable")
    public static void setBackgroundDrawable(View view, Drawable drawable) {
        view.setBackground(drawable);
    }

    //Button extends TextView
    @androidx.databinding.BindingAdapter("textColor")
    public static void setTextColor(TextView view, int color) {
        view.setTextColor(view.getResources().getColor(color));
    }

    @androidx.databinding.BindingAdapter("tooltipTextProvider")
    public static void setTooltipText(View view, int text) {
        view.setOnLongClickListener(v -> {
            if (Build.VERSION.SDK_INT >= 26)
                view.setTooltipText(view.getResources().getString(text));
            else
                TooltipCompat.setTooltipText(view, view.getResources().getString(text));
            return false;
        });
    }

    @androidx.databinding.BindingAdapter("image")
    public static void setImage(ImageView view, ImageProxy image) {

        ByteBuffer buffer = image.getImage().getPlanes()[0].getBuffer();
        byte[] bytes = new byte[buffer.capacity()];
        buffer.get(bytes);
        Bitmap bitmapImage = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, null);
        bitmapImage = ImageHelper.rotateImageFromImageProxy(bitmapImage, image);
        Glide.with(view.getContext())
                .load(bitmapImage)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .thumbnail(0.5f)
                .centerCrop()
                .into(view);
    }

    @androidx.databinding.BindingAdapter("backgroundTintAsInt")
    public static void setBackgroundTintAsInt(View view, int color) {
            DrawableCompat.setTint(
                    DrawableCompat.wrap(view.getBackground()),
                    ContextCompat.getColor(view.getContext(), color)
            );
    }

    @androidx.databinding.BindingAdapter({"roundedImage"})
    public static void setRoundedImage(ImageView view, String imageUrl)
    {
        Context context = view.getContext();
        Glide.with(context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.1f)
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        view.setImageDrawable(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
    }
}
