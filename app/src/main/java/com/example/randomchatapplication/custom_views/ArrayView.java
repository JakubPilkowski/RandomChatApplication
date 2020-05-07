package com.example.randomchatapplication.custom_views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import com.example.randomchatapplication.navigation.Navigator;

import java.util.ArrayList;
import java.util.List;

public abstract class ArrayView<I> extends LinearLayout {
    public Context context;
    public List<I> items = new ArrayList<>();
    private Navigator navigator;

    public ArrayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initControl(context);
        this.context = context;
    }

    public Navigator getNavigator() {
        return navigator;
    }

    public void setItems(List<I> items){
        this.items.clear();
        this.items.addAll(items);
        setUpAdapter();
    }
    public void setNavigator(Navigator navigator){
        this.navigator = navigator;
    }
    @LayoutRes
    public abstract int getLayoutRes();

    public abstract void assignUiElements();

    public abstract void setUpAdapter();


    private void initControl(Context context)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(getLayoutRes(), this);
        assignUiElements();
    }
}

