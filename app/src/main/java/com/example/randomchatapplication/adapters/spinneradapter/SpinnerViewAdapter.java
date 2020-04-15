package com.example.randomchatapplication.adapters.spinneradapter;

import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.base.BaseRecyclerViewAdapter;
import com.example.randomchatapplication.base.BaseViewHolder;
import com.example.randomchatapplication.databinding.SpinnerItemBinding;
import com.example.randomchatapplication.models.SpinnerItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SpinnerViewAdapter extends BaseRecyclerViewAdapter<SpinnerItem, BaseViewHolder> {
    private List<SpinnerAdapterViewModel> viewModels = new ArrayList<>();
    private int lastPosition = -1;


    @Override
    public int getItemLayoutRes() {
        return R.layout.spinner_item;
    }

    @Override
    public BaseViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType, View itemView) {
        SpinnerItemBinding binding = SpinnerItemBinding.bind(itemView);
        return new BaseViewHolder(itemView,binding);
    }

    @Override
    public void onBindView(BaseViewHolder holder, int position) {
        SpinnerAdapterViewModel viewModel;
        if(viewModels.size()<=position){
            viewModel = new SpinnerAdapterViewModel();
            viewModels.add(viewModel);
            holder.setViewModel(viewModel);
            ((SpinnerItemBinding)holder.getBinding()).setViewModel(viewModel);
            holder.setElement(items.get(position));
        }
        else {
            viewModel = viewModels.get(position);
            ((SpinnerItemBinding)holder.getBinding()).setViewModel(viewModel);
            holder.setViewModel(viewModel);
        }
        if (position+1 == getItemCount()) {
            Log.d("recyclerview", "halo halo");
            setBottomMargin(holder.itemView, (int) (24 * Resources.getSystem().getDisplayMetrics().density));
        }
    }



    private static void setBottomMargin(View view, int bottomMargin) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            params.setMargins(params.leftMargin, params.topMargin, params.rightMargin, bottomMargin);
            view.requestLayout();
        }
    }
    private void setAnimation(View viewToAnimate)
    {
            Animation animation = AnimationUtils.loadAnimation(viewToAnimate.getContext(), android.R.anim.fade_in);
            viewToAnimate.startAnimation(animation);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull BaseViewHolder holder) {
        Log.d("recyclerview", "detached");
        holder.itemView.clearAnimation();
        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.fade_out);
        holder.itemView.startAnimation(animation);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull BaseViewHolder holder) {
        Log.d("recyclerview", "atached");
        holder.itemView.clearAnimation();
        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.fade_in);
        holder.itemView.startAnimation(animation);
    }


}
