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
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.randomchatapplication.R;

public class ProfileResultView extends View {

    public static final String TAG = "ProfileResultView";

    private Paint paint;
    private Path path;
    private Point point;
    private ValueAnimator declineFirstBellAnimation;
    private ValueAnimator declineSecondBellAnimation;
    private int size = 600;
    private int lastSize;
    private ResultListener resultListener;


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
        setBackgroundColor(getResources().getColor(R.color.colorBlackOpacity));
        declineFirstBellAnimation = ValueAnimator.ofInt(0, size);
        declineFirstBellAnimation.setDuration(300);
        path.reset();
        path.moveTo(point.x, point.y);
        declineFirstBellAnimation.addUpdateListener(declineFirstBellUpdateListener);
        declineFirstBellAnimation.addListener(declineFirstBellEndAnimListener);
        declineSecondBellAnimation = ValueAnimator.ofInt(0, size);
        declineSecondBellAnimation.setDuration(300);
        declineSecondBellAnimation.addUpdateListener(declineSecondBellUpdateListener);
        declineSecondBellAnimation.addListener(declineSecondBellEndAnimListener);
        declineFirstBellAnimation.start();
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
        canvas.drawPath(path, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        float measuredWidth = getMeasuredWidth();
        float measuredHeight = getMeasuredHeight();
        Log.d(TAG, "point " + point.x + " " + point.y);
        point.set((int) measuredWidth / 2 - size / 2, (int) measuredHeight / 2 - size / 2);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    private ValueAnimator.AnimatorUpdateListener declineFirstBellUpdateListener = animation -> {
        int value = (int) animation.getAnimatedValue();
        path.lineTo(point.x + value, point.y + value);
        path.moveTo(point.x + value, point.y + value);
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
        path.moveTo(point.x + size - value, point.y + value);
        invalidate();
    };

    private AnimatorListenerAdapter declineSecondBellEndAnimListener = new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
            path.close();

            new Handler().postDelayed(() -> {
                path.reset();
                setBackgroundColor(Color.TRANSPARENT);
                invalidate();
                if (resultListener != null)
                    resultListener.onResultAnimEnd();
            }, 300);
            super.onAnimationEnd(animation);
        }
    };

}
