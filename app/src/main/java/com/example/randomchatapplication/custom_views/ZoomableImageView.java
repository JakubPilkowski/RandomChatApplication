package com.example.randomchatapplication.custom_views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
public class ZoomableImageView extends ImageView {

    Matrix matrix = new Matrix();
    public static final String TAG = "ZoomableImageView";
    static final int NONE = 0;
    static final int DRAG = 1;
    static final int ZOOM = 2;
    static final int CLICK = 3;
    int mode = NONE;

    PointF last = new PointF();
    PointF start = new PointF();
    float minScale = 1f;
    float maxScale = 4f;
    float[] m;

    float redundantXSpace, redundantYSpace;
    float width, height;
    float saveScale = 1f;
    float right, bottom, origWidth, origHeight, bmWidth, bmHeight, lastRight, lastBottom;

    int zoomInAnimationDuration = 400;
    GestureDetector gestureDetector;
    ScaleGestureDetector mScaleDetector;
    Context context;
    private float scaleX;
    private float scaleY;

    private GestureListener gestureListener;

    public void setGestureListener(GestureListener gestureListener) {
        this.gestureListener = gestureListener;
    }

    public interface GestureListener {
        void onMove(MotionEvent event, float deltaX, float deltaY);
        void onDoubleTap(MotionEvent event);
        void onScaleStart(ScaleGestureDetector detector);
        void onScaleEnd(ScaleGestureDetector detector, float saveScale);
        void onClick(MotionEvent event);
    }


    public ZoomableImageView(Context context, AttributeSet attr) {
        super(context, attr);
        super.setClickable(true);
        this.context = context;
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        gestureDetector = new GestureDetector(context, new CustomGestureListener());
        matrix.setTranslate(1f, 1f);
        m = new float[9];
        setImageMatrix(matrix);
        setScaleType(ScaleType.MATRIX);
        setOnTouchListener((v, event) -> {
            mScaleDetector.onTouchEvent(event);
            gestureDetector.onTouchEvent(event);
            matrix.getValues(m);
            float x = m[Matrix.MTRANS_X];
            float y = m[Matrix.MTRANS_Y];
            PointF curr = new PointF(event.getX(), event.getY());
            switch (event.getAction()) {
                //when one finger is touching
                //set the mode to DRAG
                case MotionEvent.ACTION_DOWN:
                    last.set(event.getX(), event.getY());
                    start.set(last);
                    mode = DRAG;
                    break;
                //when two fingers are touching
                //set the mode to ZOOM
                case MotionEvent.ACTION_POINTER_DOWN:
                    last.set(event.getX(), event.getY());
                    start.set(last);
                    mode = ZOOM;
                    break;
                //when a finger moves
                //If mode is applicable move image
                case MotionEvent.ACTION_MOVE:
                    //if the mode is ZOOM or
                    //if the mode is DRAG and already zoomed
                    if (mode == DRAG && saveScale > minScale) {

                        float deltaX = curr.x - last.x;// x difference
                        float deltaY = curr.y - last.y;// y difference
                        float scaleWidth = Math.round(origWidth * saveScale);// width after applying current scale
                        float scaleHeight = Math.round(origHeight * saveScale);// height after applying current scale
                        //if scaleWidth is smaller than the views width
                        //in other words if the image width fits in the view
                        //limit left and right movement

                        if (scaleWidth < width) {
                            deltaX = 0;
                            if (y + deltaY > 0)
                                deltaY = -y;
                            else if (y + deltaY < -bottom)
                                deltaY = -(y + bottom);
                        }
                        //if scaleHeight is smaller than the views height
                        //in other words if the image height fits in the view
                        //limit up and down movement
                        else if (scaleHeight < height) {
                            deltaY = 0;
                            if (x + deltaX > 0)
                                deltaX = -x;
                            else if (x + deltaX < -right)
                                deltaX = -(x + right);
                        }
                        //if the image doesnt fit in the width or height
                        //limit both up and down and left and right
                        else {
                            if (x + deltaX > 0)
                                deltaX = -x;
                            else if (x + deltaX < -right)
                                deltaX = -(x + right);

                            if (y + deltaY > 0)
                                deltaY = -y;
                            else if (y + deltaY < -bottom)
                                deltaY = -(y + bottom);
                        }
                        //move the image with the matrix
                        matrix.postTranslate(deltaX, deltaY);
                        //set the last touch location to the current
                        last.set(curr.x, curr.y);
                        if(gestureListener != null){
                            gestureListener.onMove(event, deltaX, deltaY);
                        }
                    }
                    break;
                //first finger is lifted
                case MotionEvent.ACTION_UP:
                    mode = NONE;
                    int xDiff = (int) Math.abs(curr.x - start.x);
                    int yDiff = (int) Math.abs(curr.y - start.y);
                    if (xDiff < CLICK && yDiff < CLICK)
                        performClick();
                    break;
                // second finger is lifted
                case MotionEvent.ACTION_POINTER_UP:
                    mode = NONE;
                    break;
            }
            setImageMatrix(matrix);
            invalidate();
            return true;
        });
    }

    public void setZoomInAnimationDuration(int zoomInAnimationDuration) {
        this.zoomInAnimationDuration = zoomInAnimationDuration;
    }

    public void setMaxZoom(float x) {
        maxScale = x;
    }

    private class CustomGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            if (mode == ZOOM || saveScale > minScale) {
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(saveScale, minScale);
                valueAnimator.setDuration(zoomInAnimationDuration);
                valueAnimator.addUpdateListener(animation -> {
                    matrix.getValues(m);
                    float value = (float) animation.getAnimatedValue();
                    float x = m[Matrix.MTRANS_X];
                    float y = m[Matrix.MTRANS_Y];
                    lastRight = right;
                    lastBottom = bottom;
                    right = width * value - width - (2 * redundantXSpace * value);
                    bottom = height * value - height - (2 * redundantYSpace * value);
                    float xDiff = x - (x * (right / lastRight));
                    float yDiff = y - (y * (bottom / lastBottom));
                    matrix.setScale(value * scaleX, value * scaleY);
                    matrix.postTranslate(x - xDiff, y - yDiff);
                    setImageMatrix(matrix);
                    invalidate();
                });
                valueAnimator.start();
                valueAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        saveScale = 1f;
                        right = width * saveScale - width - (2 * redundantXSpace * saveScale);
                        bottom = height * saveScale - height - (2 * redundantYSpace * saveScale);
                        mode = NONE;
                        if(gestureListener !=null){
                            gestureListener.onDoubleTap(e);
                        }
                    }
                });
            }
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            if(gestureListener != null)
                gestureListener.onClick(e);
            return super.onSingleTapConfirmed(e);
        }
    }


    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            mode = ZOOM;
            if(gestureListener !=null){
                gestureListener.onScaleStart(detector);
            }
            return true;
        }


        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            if(gestureListener!=null){
                gestureListener.onScaleEnd(detector, saveScale);
            }
            super.onScaleEnd(detector);
        }

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float mScaleFactor = detector.getScaleFactor();
            float origScale = saveScale;

            saveScale *= mScaleFactor;
            if (saveScale > maxScale) {
                saveScale = maxScale;
                mScaleFactor = maxScale / origScale;
            } else if (saveScale < minScale) {
                saveScale = minScale;
                mScaleFactor = minScale / origScale;
            }

            right = width * saveScale - width - (2 * redundantXSpace * saveScale);
            bottom = height * saveScale - height - (2 * redundantYSpace * saveScale);

            if (origWidth * saveScale <= width || origHeight * saveScale <= height){
                matrix.postScale(mScaleFactor, mScaleFactor, width / 2, height / 2);
            }
            else
                matrix.postScale(mScaleFactor, mScaleFactor, detector.getFocusX(), detector.getFocusY());
            fixTrans();
            return true;
            }
        }
    void fixTrans() {

        matrix.getValues(m);

        float transX = m[Matrix.MTRANS_X];

        float transY = m[Matrix.MTRANS_Y];

        float fixTransX = getFixTrans(transX, width, origWidth * saveScale);

        float fixTransY = getFixTrans(transY, height, origHeight * saveScale);

        if (fixTransX != 0 || fixTransY != 0) {
            matrix.postTranslate(fixTransX, fixTransY);
        }

    }

    float getFixTrans(float trans, float viewSize, float contentSize) {

        float minTrans, maxTrans;

        if (contentSize <= viewSize) {

            minTrans = 0;

            maxTrans = viewSize - contentSize;

        } else {

            minTrans = viewSize - contentSize;

            maxTrans = 0;

        }

        if (trans < minTrans)

            return -trans + minTrans;

        if (trans > maxTrans)

            return -trans + maxTrans;

        return 0;

    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);

        Drawable drawable = getDrawable();
        if (drawable == null || drawable.getIntrinsicWidth() == 0 || drawable.getIntrinsicHeight() == 0)
            return;

        bmWidth = drawable.getIntrinsicWidth();

        bmHeight = drawable.getIntrinsicHeight();


        //Fit to screen.
        scaleX = width / bmWidth;
        scaleY = height / bmHeight;
        matrix.setScale(scaleX, scaleY);
        setImageMatrix(matrix);
        saveScale = 1f;

        // Center the image
        redundantYSpace = height - (scaleY * bmHeight);
        redundantXSpace = width - (scaleX * bmWidth);

        redundantYSpace /= 2;
        redundantXSpace /= 2;


        matrix.postTranslate(redundantXSpace, redundantYSpace);

        origWidth = width - 2 * redundantXSpace;
        origHeight = height - 2 * redundantYSpace;
        right = width * saveScale - width - (2 * redundantXSpace * saveScale);
        bottom = height * saveScale - height - (2 * redundantYSpace * saveScale);

        setImageMatrix(matrix);
    }
}
