package com.example.randomchatapplication.helpers;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import androidx.databinding.ViewDataBinding;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.databinding.SpinnerFragmentBinding;
import com.example.randomchatapplication.interfaces.SpinnerViewListener;
import com.example.randomchatapplication.models.SpinnerItem;
import com.example.randomchatapplication.ui.spinner.SpinnerFragment;
import com.example.randomchatapplication.ui.spinner.SpinnerViewModel;

import java.util.List;

public class SelectViewDialogManager {

    private static SelectViewDialogManager INSTANCE;
    private Context context;
    private AlertDialog dialog;

    public static SelectViewDialogManager init(Context context){
        return INSTANCE = new SelectViewDialogManager(context);
    }

    public SelectViewDialogManager(Context context){
        this.context = context;
    }

    public static SelectViewDialogManager get(){
        return INSTANCE;
    }

    public void show(List<SpinnerItem> itemList, SpinnerViewListener listener){

        LinearLayout spinnerLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.spinner_fragment, null, false);
        ViewDataBinding binding = SpinnerFragmentBinding.bind(spinnerLayout);
        SpinnerViewModel viewModel = new SpinnerViewModel();
        viewModel.setSpinnerViewListener(listener);
        viewModel.init(itemList);
        ((SpinnerFragmentBinding) binding).setViewModel(viewModel);
        dialog = new AlertDialog.Builder(context, R.style.SelectViewTheme)
                .setView(spinnerLayout)
                .show();
        View decor = dialog.getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        else{
            decor.setSystemUiVisibility(0);
        }

    }

    public AlertDialog getDialog() {
        return dialog;
    }

    public void dismiss(){
        if(dialog != null && dialog.isShowing())
            dialog.dismiss();
        dialog = null;
    }
}

