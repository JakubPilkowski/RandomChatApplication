package com.example.randomchatapplication.custom_views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;

import com.example.randomchatapplication.R;

import java.security.MessageDigest;

public class DragView extends ViewGroup {

    private ViewDragHelper mDragHelper;
    private View belka;
    private View dragParent;

    private float mInitialMotionX;
    private float mInitialMotionY;

    private int mDragRange;
    private int mTop;
    private float mDragOffset;
    private View recyclerView;
    private View container;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        belka = findViewById(R.id.bell);
        recyclerView = findViewById(R.id.drag_recycler_view);
        container = findViewById(R.id.drag_container);
    }

    public DragView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mDragHelper = ViewDragHelper.create(this, 1.0f, new DragHelperCallback());
//        initControl(context);

    }

    private void initControl(Context context)
    {
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        inflater.inflate(getLayoutRes(), this);
//        assignUiElements();
    }

   // public int getLayoutRes(){
     //   return R.layout.spinner_fragment;
    //}

//    public void assignUiElements(){
//        belka = findViewById(R.id.belka);
//        fakeRecyclerView = findViewById(R.id.recycler_fake_view);
//        dragParent = findViewById(R.id.drag_parent);
//    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        final int action = ev.getAction();
        if(action!=MotionEvent.ACTION_DOWN){
            mDragHelper.cancel();
            return super.onInterceptTouchEvent(ev);
        }
        else{
            final float y = ev.getY();
            mInitialMotionY = y;
            return mDragHelper.isViewUnder(belka, (int) ev.getY(), (int) y);

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);

        final int action = event.getAction();
        final float y = event.getY();

//        boolean isHeaderViewUnder = mDragHelper.isViewUnder(belka, (int) event.getX(), (int) y);
        switch (action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: {
                mInitialMotionY = y;
                break;
            }

            case MotionEvent.ACTION_UP: {
                final float dy = y - mInitialMotionY;
                final int slop = mDragHelper.getTouchSlop();
                Log.d("youtubeLayout", "dy" + dy);
                Log.d("youtubeLayout", "slop" + slop);
                if (dy > (slop * 5)) {
                    Log.d("youtubeLayout", "w dół");
                        smoothSlideTo(1f);
                }
                else{
                    smoothSlideTo(0f);
                    Log.d("youtubeLayout", "w góre");
                }
                break;
            }
        }


        return true;
    }
    public void maximize(){
        smoothSlideTo(0f);
    }
    boolean smoothSlideTo(float slideOffset){
        final int topBound = getPaddingTop();
        int y = (int) (topBound + slideOffset * mDragRange);
        if(mDragHelper.smoothSlideViewTo(container,container.getLeft(),y)){
            ViewCompat.postInvalidateOnAnimation(this);
            return true;
        }
        return false;
    }
    private class DragHelperCallback extends ViewDragHelper.Callback{

        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
            return child == container;
        }

        @Override
        public void onViewPositionChanged(@NonNull View changedView, int left, int top, int dx, int dy) {
            mTop = top;

            mDragOffset = (float) top / mDragRange;
//
//            mHeaderView.setPivotX(mHeaderView.getWidth());
//            mHeaderView.setPivotY(mHeaderView.getHeight());
//            mHeaderView.setScaleX(1 - mDragOffset / 2);
//            mHeaderView.setScaleY(1 - mDragOffset / 2);
//
//            mDescView.setAlpha(1 - mDragOffset);

            requestLayout();

        }

        @Override
        public int getViewVerticalDragRange(@NonNull View child) {
            return mDragRange;
        }

        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
            final int topBound = getPaddingTop();
            final int bottomBound = getHeight();
            return Math.min(Math.max(top, topBound), bottomBound);
        }

        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
            int top = getPaddingTop();
            if (yvel > 0 || (yvel == 0 && mDragOffset > 0.5f)) {
                top += mDragRange;
            }
            mDragHelper.settleCapturedViewAt(releasedChild.getLeft(), top);
        }
    }

    @Override
    public void computeScroll() {
        if(mDragHelper.continueSettling(true)){
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int maxWidth = MeasureSpec.getSize(widthMeasureSpec);
        int maxHeight = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(resolveSizeAndState(maxWidth, widthMeasureSpec, MeasureSpec.UNSPECIFIED),
                resolveSizeAndState(maxHeight, heightMeasureSpec, MeasureSpec.UNSPECIFIED));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mDragRange = getHeight();

        container.layout(
                0,
                mTop,
                r,
                mTop + container.getMeasuredHeight()
        );

//        belka.layout(
//                0,
//                mTop,
//                r,
//                mTop + belka.getMeasuredHeight()
//        );
//        recyclerView.layout(
//                0,
//                mTop + belka.getMeasuredHeight(),
//                r,
//                mTop + b
//        );



    }

}
