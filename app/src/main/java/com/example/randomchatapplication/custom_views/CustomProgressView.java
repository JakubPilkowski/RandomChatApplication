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
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
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
    private float mThickness = 17f;


    private float mIndeterminateSweep;
    private float mReturnSweep;
    private float mStartAngle;


    public CustomProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
     mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
     mRect = new RectF();
     mPaint.setColor(getResources().getColor(R.color.colorPrimary));
     mPaint.setStyle(Paint.Style.STROKE);
     mPaint.setStrokeWidth(mThickness);
     mPaint.setStrokeCap(Paint.Cap.ROUND);

    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(mRect, mStartAngle+mReturnSweep,
                mIndeterminateSweep-mReturnSweep, false, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int xPad = getPaddingStart() + getPaddingEnd();
        int yPad = getPaddingTop() + getPaddingBottom();
        int width = MeasureSpec.getSize(widthMeasureSpec) - xPad;
        int height = MeasureSpec.getSize(heightMeasureSpec) - yPad;
        mSize = width < height ? width : height;
        setMeasuredDimension(mSize + xPad,mSize + yPad);
        updateRectAngleBounds();
    }
    private void updateRectAngleBounds() {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        mRect.set(paddingLeft + mThickness, paddingTop + mThickness,
                mSize - paddingLeft - mThickness, mSize - paddingTop - mThickness);
    }

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
                if(mIndeterminateSweep > 360){

                    mReturnSweep += 2.5;

                    if(mIndeterminateSweep - 1 <= mReturnSweep)
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
