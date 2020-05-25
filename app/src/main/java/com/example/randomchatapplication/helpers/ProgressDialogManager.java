package com.example.randomchatapplication.helpers;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.example.randomchatapplication.R;

public class ProgressDialogManager {

    private static ProgressDialogManager INSTANCE;
    private Context context;
    private AlertDialog dialog;
    public ProgressDialogManager(Context context) {
        this.context = context;
    }

    public static ProgressDialogManager init(Context context){
        return INSTANCE = new ProgressDialogManager(context);
    }

    public static ProgressDialogManager get(){
        return INSTANCE;
    }

    public void show(){
        dismiss();
        RelativeLayout layout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.progress_dialog, null, false);
        View imageView = layout.getChildAt(1);
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.scale_2);

        imageView.startAnimation(animation);
        dialog = new AlertDialog.Builder(context, R.style.ProgressDialogTheme)
                .setView(layout)
                .setCancelable(false)
                .show();
    }

    public void dismiss(){
        if(dialog != null && dialog.isShowing())
            dialog.dismiss();
        dialog = null;
    }

}
