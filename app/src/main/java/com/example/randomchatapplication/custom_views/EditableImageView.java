package com.example.randomchatapplication.custom_views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.example.randomchatapplication.R;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("AppCompatCustomView")
public class EditableImageView extends ImageView {
    public static final String TAG = "EditableImageView";

    //Przybliżanie
    Matrix matrix;
    float minScale = 1f;
    float maxScale = 4f;
    float[] m;
    int viewWidth, viewHeight;
    float saveScale = 1f;
    protected float origWidth, origHeight;
    ScaleGestureDetector mScaleDetector;

    //Rysowanie
    private boolean isDrawingEnabled = false;
    Canvas canvas;
    Paint paint;
    private ArrayList<Path> paths = new ArrayList<>();
    private Path drawPath;
    private Bitmap mBitmap;
    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;
    private int selectedColor = R.color.colorPrimary;
    private float strokeWidth = 10f;

    Context context;


    public EditableImageView(Context context) {
        super(context);
        initView(context);
    }

    public EditableImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public EditableImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void setDrawingEnabled(boolean isDrawingEnabled) {
        this.isDrawingEnabled = isDrawingEnabled;
    }

    public void setSelectedColor(int selectedColor) {
//        invalidate();
        this.selectedColor = selectedColor;
        paint.setColor(selectedColor);
    }

    public void setStrokeWidth(float strokeWidth) {
//        invalidate();
        this.strokeWidth = strokeWidth;
        paint.setStrokeWidth(strokeWidth);
    }

    public void clearCanvas() {
        if(paths.size()>0){
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            drawPath.reset();
            paths.clear();
            invalidate();
        }
    }

    public void clearLastPainting() {
        if (paths.size() > 0) {
            Log.d(TAG, "paths size" + paths.size());
            Path tmpPath = paths.get(paths.size() - 1);
            paths.remove(tmpPath);
            drawPath.reset();
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            for(Path path : paths)
            {
                canvas.drawPath(path, paint);
            }
            invalidate();
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    private void initView(Context context) {
        this.context = context;
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        matrix = new Matrix();
        m = new float[9];
        setImageMatrix(matrix);
        setScaleType(ScaleType.MATRIX);
        setOnTouchListener((v, event) -> {
            if (event.getPointerCount() == 2) {
                mScaleDetector.onTouchEvent(event);
                setImageMatrix(matrix);
            } else {
                if (isDrawingEnabled) {
                    Log.d(TAG, "rysowanko");
                    float touchX = event.getX();
                    float touchY = event.getY();
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            touch_start(touchX, touchY);
                            break;
                        case MotionEvent.ACTION_MOVE:
                            touchMove(touchX, touchY);
                            break;
                        case MotionEvent.ACTION_UP:
                            touch_up();
                            break;
                    }
                    invalidate();
                }
            }
            return true;
        });
        canvas = new Canvas();
        paint = new Paint();
        paint.setColor(getResources().getColor(selectedColor));
        paint.setStrokeWidth(strokeWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    private void touch_start(float x, float y) {
        drawPath = new Path();
        drawPath.moveTo(x, y);
        mX = x;
        mY = y;
    }


    private void touchMove(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            drawPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    private void touch_up() {
        drawPath.lineTo(mX, mY);
        canvas.drawPath(drawPath, paint);
        paths.add(drawPath);
        // mPath= new Path();
    }

    @Override
    public void draw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.draw(canvas);
        canvas.drawBitmap(mBitmap, 0, 0, paint);
        if (drawPath != null)
            canvas.drawPath(drawPath, paint);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(mBitmap);
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
        viewWidth = MeasureSpec.getSize(widthMeasureSpec);
        viewHeight = MeasureSpec.getSize(heightMeasureSpec);


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
