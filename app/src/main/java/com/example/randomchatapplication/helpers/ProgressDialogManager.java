package com.example.randomchatapplication.helpers;

import android.app.AlertDialog;
import android.content.Context;

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
        dialog = new AlertDialog.Builder(context, R.style.ProgressDialogTheme)
                .setView(R.layout.progress_dialog)
                .setCancelable(false)
                .show();
    }

    public void dismiss(){
        if(dialog != null && dialog.isShowing())
            dialog.dismiss();
        dialog = null;
    }

}
