package com.example.randomchatapplication.helpers;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;

public class DimensionsHelper {
    public static float convertPixelsToDp(float px, Context context){
        return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }


}
