package com.example.randomchatapplication.custom_views;

import android.content.Context;
import android.media.Image;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.randomchatapplication.R;

public class DotsView extends RelativeLayout {
    LinearLayout dotsContainer;
    LinearLayout mainDotContainer;
    RelativeLayout dotsMainView;
    int dotsCount;
    public ImageView [] dots;
    public ImageView mainDot;
    public DotsView(Context context, AttributeSet attr){
        super(context,attr);
        initControl(context);
    }
    private void initControl(Context context)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(getLayoutRes(), this);
        assignUiElements();
    }
    public int getLayoutRes() {
        return R.layout.dots_view;
    }

    public void assignUiElements() {
        dotsContainer = findViewById(R.id.dots_container);
        mainDotContainer = findViewById(R.id.main_dot_container);
        dotsMainView = findViewById(R.id.dots_main_view);
    }

    public void setDotsCount(int dotsCount) {
        this.dotsCount = dotsCount;
        setUpAdapter();
    }
    public void setUpAdapter() {

        dots = new ImageView[dotsCount];
        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(dotsContainer.getContext());
            dots[i].setImageDrawable(dotsContainer.getResources().getDrawable(R.drawable.dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(6, 0, 6, 0);
            dotsContainer.addView(dots[i], params);
        }
        //        int dotsContainerWidth = dotsContainerParams.width;
//        dotsContainer.setLayoutParams(new LayoutParams(dotsContainerWidth,LayoutParams.WRAP_CONTENT));
        Log.d("width", String.valueOf(dotsContainer.getWidth()));
        ViewTreeObserver viewTreeObserver = dotsContainer.getViewTreeObserver();
        if(viewTreeObserver.isAlive()){
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    dotsContainer.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    Log.d("width", String.valueOf(dotsContainer.getWidth()));

                    mainDotContainer.setLayoutParams(new LayoutParams(dotsContainer.getWidth(), LayoutParams.WRAP_CONTENT));
                    mainDot = new ImageView(mainDotContainer.getContext());
                    mainDot.setImageDrawable(mainDotContainer.getResources().getDrawable(R.drawable.active_dot));
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                    params.setMargins(6, 0, 6, 0);
                    mainDotContainer.addView(mainDot, params);
                    RelativeLayout.LayoutParams relativeParams = (LayoutParams) mainDotContainer.getLayoutParams();
                    relativeParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
                    mainDotContainer.setLayoutParams(relativeParams);
                }
            });
        }
    }

    public ImageView getMainDot() {
        return mainDot;
    }
}
