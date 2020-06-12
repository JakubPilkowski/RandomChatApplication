package com.example.randomchatapplication.custom_views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.interfaces.DragViewListener;

public class DragView extends ViewGroup {

    private ViewDragHelper mDragHelper;
    private View bellContainer;
    private View bell;
    private View bell_active;
    private View dragParent;

    private float mInitialMotionX;
    private float mInitialMotionY;

    private int mDragRange;
    private int mTop;
    private float mDragOffset;
    private View container;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        bellContainer = findViewById(R.id.bell_container);
        bell= findViewById(R.id.bell);
        bell_active = findViewById(R.id.bell_active);
        container = findViewById(R.id.drag_container);
    }

    public DragView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mDragHelper = ViewDragHelper.create(this, 1.0f, new DragHelperCallback());
    }
    private DragViewListener dragViewListener;
    public void setDragViewListener(DragViewListener dragViewListener){
        this.dragViewListener = dragViewListener;
    }

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
            return mDragHelper.isViewUnder(bellContainer, (int) ev.getY(), (int) y);

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);

        final int action = event.getAction();
        final float y = event.getY();

//        boolean isHeaderViewUnder = mDragHelper.isViewUnder(bellContainer, (int) event.getX(), (int) y);
        switch (action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: {
                mInitialMotionY = y;
                break;
            }

            case MotionEvent.ACTION_UP: {
                final float dy = y - mInitialMotionY;
                final int slop = mDragHelper.getTouchSlop();

                if (dy > (slop * 10)) {
                        smoothSlideTo(1f);
                }
                else{
                    smoothSlideTo(0f);
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

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        return super.onCreateDrawableState(extraSpace);
    }

    private class DragHelperCallback extends ViewDragHelper.Callback{

        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
            return child == container;
        }

        @Override
        public void onViewPositionChanged(@NonNull View changedView, int left, int top, int dx, int dy) {
            bell_active.setVisibility(VISIBLE);
//            bell.setVisibility(INVISIBLE);
            mTop = top;

            mDragOffset = (float) top / mDragRange;
//
            container.setPivotX(container.getWidth()/2);
            container.setPivotY(container.getHeight()/2);
            container.setScaleX(1 - mDragOffset / 10);

//

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
        public void onViewDragStateChanged(int state) {
            Log.d("onViewDragStateChanged", String.valueOf(state));
            if(state==0)
                bell_active.setVisibility(INVISIBLE);
            if(state==0 && mTop > 0){
                dragViewListener.onClose();
            }
            super.onViewDragStateChanged(state);
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




    }

}
