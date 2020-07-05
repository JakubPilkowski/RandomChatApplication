package com.example.randomchatapplication.custom_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.VectorDrawable;
import android.media.Image;
import android.util.AttributeSet;

import androidx.core.content.ContextCompat;

import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.example.randomchatapplication.R;
import com.example.randomchatapplication.helpers.ImageHelper;

public class CustomRangeSeekbar extends CrystalRangeSeekbar {
    public CustomRangeSeekbar(Context context) {
        super(context);
    }

    public CustomRangeSeekbar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRangeSeekbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected Drawable getRightDrawable(TypedArray typedArray) {
        return ContextCompat.getDrawable(getContext(), R.drawable.thumb_non_active);
    }

    @Override
    protected Drawable getLeftDrawable(TypedArray typedArray) {
        return ContextCompat.getDrawable(getContext(), R.drawable.thumb_non_active);
    }

    @Override
    protected Drawable getLeftDrawablePressed(TypedArray typedArray) {
        return ContextCompat.getDrawable(getContext(), R.drawable.thumb_active);
    }

    @Override
    protected Drawable getRightDrawablePressed(TypedArray typedArray) {
        return ContextCompat.getDrawable(getContext(), R.drawable.thumb_active);
    }

    @Override
    protected Bitmap getBitmap(Drawable drawable) {
        if (drawable instanceof VectorDrawable || drawable instanceof LayerDrawable){
            return ImageHelper.drawableToBitmap(drawable);
        }
        else{
            return super.getBitmap(drawable);
        }
    }

}
