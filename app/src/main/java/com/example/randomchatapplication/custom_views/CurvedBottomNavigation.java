package com.example.randomchatapplication.custom_views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.helpers.DimensionsHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CurvedBottomNavigation extends BottomNavigationView {

    private Path mPath;
    private Paint mPaint;

    private final int BUTTON_DIMENSION = (int) DimensionsHelper.convertDpToPixel(56, getContext());
    private final int BUTTON_MARGIN = (int) DimensionsHelper.convertDpToPixel(18, getContext());
    private final int BUTTON_SPACE = (int) DimensionsHelper.convertDpToPixel(4, getContext());

    private int mNavigationWidth;
    private int mNavigationHeight;

    private Point mFirstCurveStartPoint = new Point();
    private Point mFirstCurveEndPoint = new Point();
    private Point mFirstCurveControlPoint1 = new Point();
    private Point mFirstCurveControlPoint2 = new Point();

    //the coordinates of the second curve
    private Point mSecondCurveStartPoint = new Point();
    private Point mSecondCurveEndPoint = new Point();
    private Point mSecondCurveControlPoint1 = new Point();
    private Point mSecondCurveControlPoint2 = new Point();

    public CurvedBottomNavigation(@NonNull Context context) {
        super(context);
        init();
    }

    public CurvedBottomNavigation(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CurvedBottomNavigation(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.LTGRAY);
        setBackgroundColor(Color.TRANSPARENT);
        getMenu().getItem(2).setEnabled(false);
        setItemIconTintList(null);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mNavigationHeight = getHeight();
        mNavigationWidth = getWidth();

        mFirstCurveStartPoint.set((mNavigationWidth/2) - BUTTON_DIMENSION/2 - BUTTON_MARGIN*2, 0);
        mFirstCurveEndPoint.set(mNavigationWidth/2, BUTTON_DIMENSION/2 + BUTTON_SPACE*3/2 );

        mSecondCurveStartPoint = mFirstCurveEndPoint;
        mSecondCurveEndPoint.set((mNavigationWidth/2) + BUTTON_DIMENSION/2 + BUTTON_MARGIN*2, 0);

        mFirstCurveControlPoint1.set(mFirstCurveStartPoint.x + (BUTTON_DIMENSION/2), mFirstCurveStartPoint.y);
        mFirstCurveControlPoint2.set(mFirstCurveEndPoint.x - BUTTON_DIMENSION/2  , mFirstCurveEndPoint.y);

        mSecondCurveControlPoint1.set(mSecondCurveStartPoint.x + (BUTTON_DIMENSION/2) , mSecondCurveStartPoint.y );
        mSecondCurveControlPoint2.set(mSecondCurveEndPoint.x - BUTTON_DIMENSION/2 , mSecondCurveEndPoint.y);

        mPath.reset();
        mPath.moveTo(0,0);
        mPath.lineTo(mFirstCurveStartPoint.x, mFirstCurveStartPoint.y);
        mPath.cubicTo(mFirstCurveControlPoint1.x, mFirstCurveControlPoint1.y,
                    mFirstCurveControlPoint2.x, mFirstCurveControlPoint2.y,
                    mFirstCurveEndPoint.x, mFirstCurveEndPoint.y
                );
        mPath.cubicTo(mSecondCurveControlPoint1.x, mSecondCurveControlPoint1.y,
                    mSecondCurveControlPoint2.x, mSecondCurveControlPoint2.y,
                    mSecondCurveEndPoint.x, mSecondCurveEndPoint.y
                );
        mPath.lineTo(mNavigationWidth,0);
        mPath.lineTo(mNavigationWidth, mNavigationHeight);
        mPath.lineTo(0, mNavigationHeight);
        mPath.close();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }
}
