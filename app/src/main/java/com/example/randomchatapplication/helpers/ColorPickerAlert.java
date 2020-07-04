package com.example.randomchatapplication.helpers;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.adapters.colorPicker.ColorPickerAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class ColorPickerAlert {
    private static ColorPickerAlert INSTANCE;
    private Context context;
    private Dialog dialog;

    public ColorPickerAlert(Context context) {
        this.context = context;
    }

    public static ColorPickerAlert init(Context context) {
        return INSTANCE = new ColorPickerAlert(context);
    }

    public static ColorPickerAlert get() {
        return INSTANCE;
    }

    public void show() {
        dismiss();
        RelativeLayout layout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.custom_color_picker, null, false);
        RecyclerView view = layout.findViewById(R.id.color_picker_recycler_view);
        ColorPickerAdapter colorPickerAdapter = new ColorPickerAdapter();
        ArrayList<Integer> colors = new ArrayList<>(Arrays.asList(R.color.colorAccent, R.color.colorBlack, R.color.iconGrayLight
                , R.color.gold, R.color.red, R.color.orange, R.color.yellow, R.color.lime, R.color.green, R.color.cyan, R.color.blue, R.color.purple,
                R.color.magenta, R.color.pink, R.color.apricot, R.color.beige, R.color.mint, R.color.lavender, R.color.maroon,
                R.color.brown, R.color.olive, R.color.teal, R.color.navy, R.color.diamond));
        colorPickerAdapter.setItems(colors);
        view.setLayoutManager(new GridLayoutManager(context, 6));
        view.setAdapter(colorPickerAdapter);

        layout.setOnClickListener(v -> dismiss());

        dialog = new Dialog(context, R.style.ColorPickerTheme);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.setContentView(layout);
        dialog.show();
    }

    public void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            Log.d("dismiss: ", "jest dialog");
            dialog.dismiss();
        }
        dialog = null;
    }
}
