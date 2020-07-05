package com.example.randomchatapplication.helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import androidx.camera.core.ImageProxy;

public class ImageHelper {

    public static Bitmap rotateImageFromImageProxy(Bitmap img, ImageProxy imageProxy) {
        int rotation = imageProxy.getImageInfo().getRotationDegrees();
        if (rotation != 0) {
            Matrix matrix = new Matrix();
            matrix.postRotate(rotation);
            return Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
        } else {
            return img;
        }
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
