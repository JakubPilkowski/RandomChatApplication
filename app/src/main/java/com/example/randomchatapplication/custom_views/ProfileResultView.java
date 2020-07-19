package com.example.randomchatapplication.custom_views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.randomchatapplication.R;

public class ProfileResultView extends View {

    public static final String TAG = "ProfileResultView";

    private Paint paint;
    private Path path;
    private Point point;
    private int size = 600;
    private int lastSize;
    private ResultListener resultListener;
    private float topCurveHeight;
    private Canvas mainCanvas;
    private float value;

    public static final int MODE_HEART_TRANSLATE = 1;
    public static final int MODE_HEART_SCALE = 2;
    public static final int MODE_CLOSE = 3;


    private int mode;
    private ValueAnimator heartTranslateAnimation;
    private ValueAnimator heartScaleAnimation;
    private ValueAnimator declineFirstBellAnimation;
    private ValueAnimator declineSecondBellAnimation;


    public void stopAnimations(){
        if(heartTranslateAnimation !=null)
            heartTranslateAnimation.cancel();
        if(heartScaleAnimation != null)
            heartScaleAnimation.cancel();
        if(declineFirstBellAnimation !=null)
            declineFirstBellAnimation.cancel();
        if(declineSecondBellAnimation != null)
            declineSecondBellAnimation.cancel();
    }

    @Override
    protected void onDetachedFromWindow() {
        stopAnimations();
        super.onDetachedFromWindow();
    }

    public interface ResultListener {
        void onResultAnimEnd();

        void onResultAnimStart();
    }


    public ProfileResultView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        path = new Path();
        point = new Point();
        paint.setColor(getResources().getColor(R.color.colorPrimary));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(100f);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }


    public void onDeclineDrawing() {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(100f);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        mode = MODE_CLOSE;
        setSize(600);

        point.set(getMeasuredWidth() /2-size/2 , getMeasuredHeight() / 2-size/2);
        path.reset();
        path.moveTo(point.x, point.y);

        setBackgroundColor(getResources().getColor(R.color.colorBlackOpacity));
        declineFirstBellAnimation = ValueAnimator.ofInt(0, size);
        declineFirstBellAnimation.setDuration(250);
        declineFirstBellAnimation.addUpdateListener(declineFirstBellUpdateListener);
        declineFirstBellAnimation.addListener(declineFirstBellEndAnimListener);
        declineSecondBellAnimation = ValueAnimator.ofInt(0, size);
        declineSecondBellAnimation.setDuration(250);
        declineSecondBellAnimation.addUpdateListener(declineSecondBellUpdateListener);
        declineSecondBellAnimation.addListener(declineSecondBellEndAnimListener);
        declineFirstBellAnimation.start();
        if (resultListener != null) {
            resultListener.onResultAnimStart();
        }
    }

    public void onAcceptDrawing(){
        paint.setStrokeWidth(50f);
        mode = MODE_HEART_TRANSLATE;
        setSize(750);
        topCurveHeight = (float) (size * 0.3);
        point.set(getMeasuredWidth() /2 , (int) (getMeasuredHeight() / 2 -size/3 -topCurveHeight));
        setBackgroundColor(getResources().getColor(R.color.colorBlackOpacity));
        path.reset();
        path.moveTo(point.x, point.y+topCurveHeight);
        path.cubicTo(point.x, point.y, point.x - size/2,point.y, point.x -size/2, point.y+topCurveHeight);
        path.cubicTo(point.x-size/2, point.y + (size+topCurveHeight)/2, point.x, point.y + (size+topCurveHeight)/2, point.x, point.y + size);
        path.cubicTo(point.x, point.y + (size+ topCurveHeight)/2, point.x+size/2, point.y + (size+topCurveHeight)/2, point.x+size/2, (float) (point.y + topCurveHeight));
        path.cubicTo(point.x+size/2, point.y, point.x, point.y, point.x, point.y+topCurveHeight);

        heartTranslateAnimation = ValueAnimator.ofFloat(getMeasuredHeight(), 0);
        heartTranslateAnimation.setDuration(400);
        heartTranslateAnimation.addUpdateListener(heartUpdateListener);
        heartTranslateAnimation.start();
        heartTranslateAnimation.addListener(translateEndListener);

        heartScaleAnimation = ValueAnimator.ofFloat(1, 10);
        heartScaleAnimation.setDuration(400);
        heartScaleAnimation.addUpdateListener(heartUpdateListener);
        heartScaleAnimation.addListener(scaleEndListener);
        if (resultListener != null) {
            resultListener.onResultAnimStart();
        }
    }



    public void setResultListener(ResultListener resultListener) {
        this.resultListener = resultListener;
    }

    public void setSize(int size) {
        this.size = size;
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (mode){
            case MODE_HEART_TRANSLATE:
                canvas.translate(0 , -value);
                break;
            case MODE_HEART_SCALE:
                canvas.scale(value,value,getMeasuredWidth()/2,getMeasuredHeight()/2);
                break;
        }
        canvas.drawPath(path, paint);
    }

    private ValueAnimator.AnimatorUpdateListener declineFirstBellUpdateListener = animation -> {
        int value = (int) animation.getAnimatedValue();
        path.lineTo(point.x + value, point.y + value);
        invalidate();
    };
    private AnimatorListenerAdapter declineFirstBellEndAnimListener = new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
            if (declineSecondBellAnimation != null) {
                path.moveTo(point.x + size, point.y);
                declineSecondBellAnimation.start();
            }
            super.onAnimationEnd(animation);
        }
    };
    private ValueAnimator.AnimatorUpdateListener declineSecondBellUpdateListener = animation -> {
        int value = (int) animation.getAnimatedValue();
        path.lineTo(point.x + size - value, point.y + value);
        invalidate();
    };

    private AnimatorListenerAdapter declineSecondBellEndAnimListener = new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
            path.close();
            if (resultListener != null)
                resultListener.onResultAnimEnd();
            new Handler().postDelayed(() -> {
                path.reset();
                setBackgroundColor(Color.TRANSPARENT);
                invalidate();
            }, 300);
            super.onAnimationEnd(animation);
        }
    };

    private ValueAnimator.AnimatorUpdateListener heartUpdateListener = animation -> {
            value = (float) animation.getAnimatedValue();
            invalidate();
    };

    private AnimatorListenerAdapter translateEndListener = new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
            mode = MODE_HEART_SCALE;
            paint.setStyle(Paint.Style.FILL);
            heartScaleAnimation.start();
            super.onAnimationEnd(animation);
        }
    };


    private AnimatorListenerAdapter scaleEndListener = new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
            path.close();
            new Handler().postDelayed(() -> {
                setBackgroundColor(Color.TRANSPARENT);
                path.reset();
                invalidate();
            },300);
            if(resultListener != null){
                resultListener.onResultAnimEnd();
            }
            super.onAnimationEnd(animation);
        }
    };

}
