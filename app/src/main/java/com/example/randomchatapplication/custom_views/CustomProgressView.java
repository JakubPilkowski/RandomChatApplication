package com.example.randomchatapplication.custom_views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.example.randomchatapplication.R;

public class CustomProgressView extends View {

    private static final float INDETERMINANT_MIN_SWEEP = 15f;
    public static final int RESETTING_ANGLE = 620;



    private int mSize;

    private Paint mPaint;
    private RectF mRect;

    //private float mThickness;
    private int mAnimDuration= 3000;
    private int mSteps = 3;
    private float mThickness = 15f;


    private float mIndeterminateSweep;
    private float mIndeterminateRotateOffset;
    private float mReturnSweep;
    private float mStartAngle;


    public CustomProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
     mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
     mRect = new RectF();
     mPaint.setColor(Color.RED);
     mPaint.setStyle(Paint.Style.STROKE);
     mPaint.setStrokeWidth(mThickness);
     mPaint.setStrokeCap(Paint.Cap.ROUND);

    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("startAngle", String.valueOf(mStartAngle));
        Log.d("endAngle", String.valueOf(mIndeterminateSweep-mReturnSweep));

        canvas.drawArc(mRect, mStartAngle,
                mIndeterminateSweep-mReturnSweep, false, mPaint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int xPad = getPaddingStart() + getPaddingEnd();
        int yPad = getPaddingTop() + getPaddingBottom();
        Log.d("progressViewWidthMeas", String.valueOf(widthMeasureSpec));
        Log.d("progressViewHeightMeas", String.valueOf(heightMeasureSpec));

        int width = MeasureSpec.getSize(widthMeasureSpec) - xPad;
        int height = MeasureSpec.getSize(heightMeasureSpec) - yPad;
        Log.d("progressViewOnWidth", String.valueOf(width));
        Log.d("progressViewOnHeight", String.valueOf(height));

        mSize = width < height ? width : height;
        Log.d("progressViewOnMeasure", String.valueOf(mSize));
        setMeasuredDimension(mSize + xPad,mSize + yPad);
        updateRectAngleBounds();
    }
    private void updateRectAngleBounds() {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        mRect.set(paddingLeft + mThickness, paddingTop + mThickness,
                mSize - paddingLeft - mThickness, mSize - paddingTop - mThickness);
    }
//    @Override
//    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        super.onLayout(changed, left, top, right, bottom);
//    }
//
//    @Override
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
//        mSize = (w<h) ? w : h;
//        updateRectAngleBounds();
//        Log.d("progressViewSizeChanged", String.valueOf(mSize));
//
//    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        animateArch();
    }

    private void animateArch() {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,360);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mStartAngle = (float) animation.getAnimatedValue();

//                Log.d("sweep", String.valueOf(mIndeterminateSweep));
                if(mIndeterminateSweep > 375){
                    mReturnSweep += 5;
//                    mIndeterminateSweep+=2;

                    if(mIndeterminateSweep - 15 < mReturnSweep)
                    {
                        mIndeterminateSweep = 15;
                        mReturnSweep = 0;
                    }
                }
                else{
                    mIndeterminateSweep+= 2;
                }
                invalidate();
            }
        });

        valueAnimator.start();
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                animateArch();
            }
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }


}
