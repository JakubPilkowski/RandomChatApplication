package com.example.randomchatapplication.custom_views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class EditableImageView extends ImageView {
    public static final String TAG = "EditableImageView";

    public EditableImageView(Context context) {
        super(context);
        sharedConstructing(context);
    }

    public EditableImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        sharedConstructing(context);
    }

    public EditableImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        sharedConstructing(context);
    }

    Matrix matrix;

    float minScale = 1f;
    float maxScale = 4f;
    float[] m;
    int viewWidth, viewHeight;

    float saveScale = 1f;

    protected float origWidth, origHeight;

    int oldMeasuredWidth, oldMeasuredHeight;
    ScaleGestureDetector mScaleDetector;
    Context context;

    @SuppressLint("ClickableViewAccessibility")
    private void sharedConstructing(Context context) {
        this.context = context;
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        matrix = new Matrix();
        m = new float[9];
        setImageMatrix(matrix);
        setScaleType(ScaleType.MATRIX);
        setOnTouchListener((v, event) -> {
            if (event.getPointerCount() == 2){
                mScaleDetector.onTouchEvent(event);
                setImageMatrix(matrix);
            }

            return true; // indicate event was handled
        });
    }

    public void setMaxZoom(float x) {
        maxScale = x;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

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

            if (origWidth * saveScale <= viewWidth || origHeight * saveScale <= viewHeight)
                matrix.postScale(mScaleFactor, mScaleFactor, viewWidth / 2, viewHeight / 2);
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

        float fixTransX = getFixTrans(transX, viewWidth, origWidth * saveScale);

        float fixTransY = getFixTrans(transY, viewHeight, origHeight * saveScale);

        if (fixTransX != 0 || fixTransY != 0)
        {
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

        viewWidth = MeasureSpec.getSize(widthMeasureSpec);

        viewHeight = MeasureSpec.getSize(heightMeasureSpec);

        oldMeasuredHeight = viewHeight;

        oldMeasuredWidth = viewWidth;

        if (saveScale == 1) {

            //Fit to screen.

            /*
            ten kod chyba nie jest potrzebny, bo zdjęcie będzie miało zawsze wielkość równą imageview (dzięki glidowi?)
            trzeba sprawdzić na innych urządzeniach
             */

//            float scale;

//            Drawable drawable = getDrawable();

//            if (drawable == null || drawable.getIntrinsicWidth() == 0 || drawable.getIntrinsicHeight() == 0)
//                return;

//            int bmWidth = drawable.getIntrinsicWidth();

//            int bmHeight = drawable.getIntrinsicHeight();

//            Log.d("bmSize", "bmWidth: " + bmWidth + " bmHeight : " + bmHeight);

//            float scaleX = (float) viewWidth / (float) bmWidth;
//
//            float scaleY = (float) viewHeight / (float) bmHeight;
//
//
//            scale = Math.min(scaleX, scaleY);

//            Log.d(TAG, "onMeasureScale " + scale);
            matrix.setScale(1, 1);

            // Center the image

//            float redundantYSpace = (float) viewHeight - (1 * (float) bmHeight);

//            float redundantXSpace = (float) viewWidth - (1 * (float) bmWidth);

//            redundantYSpace /= (float) 2;

//            redundantXSpace /= (float) 2;

//            Log.d(TAG, "redundantXSpace: " + redundantXSpace);
//            Log.d(TAG, "redundantYSpace: "+ redundantYSpace);
            matrix.postTranslate(0f, 0f);

//            origWidth = viewWidth - 2 * redundantXSpace;

//            origHeight = viewHeight - 2 * redundantYSpace;

            origWidth = viewWidth;
            origHeight = viewHeight;
            setImageMatrix(matrix);
        }

        fixTrans();
    }
}
