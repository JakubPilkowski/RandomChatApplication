package com.example.randomchatapplication.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.databinding.CpDatePickerBinding;
import com.example.randomchatapplication.databinding.CpEditTextBinding;
import com.example.randomchatapplication.databinding.CpRangeSeekbarBinding;
import com.example.randomchatapplication.databinding.CpSelectBinding;
import com.example.randomchatapplication.models.Field;
import com.example.randomchatapplication.models.Step;
import com.example.randomchatapplication.models.ViewInfo;
import com.example.randomchatapplication.ui.create_profile.fields.DatePickerViewModel;
import com.example.randomchatapplication.ui.create_profile.fields.EditTextViewModel;
import com.example.randomchatapplication.ui.create_profile.fields.RangeSeekbarViewModel;
import com.example.randomchatapplication.ui.create_profile.fields.SelectViewModel;
import com.example.randomchatapplication.viewmodels.FieldViewModel;

import java.util.ArrayList;
import java.util.List;

public class FieldsHelper {

    public static FieldsHelper INSTANCE;

    private List<Field> fields;
    private List<Step> steps;

    public List<Field> getFields() {
        return fields;
    }

    public List<Step> getSteps() {
        return steps;
    }

    private List<BaseViewModel> fieldsViewModels;


    public FieldsHelper(List<Field> fields, List<Step> steps) {
        this.fields = fields;
        this.steps = steps;
    }


    public static void init(List<Field> fields, List<Step> steps) {
        INSTANCE = new FieldsHelper(fields, steps);
    }

    public static FieldsHelper get() {
        return INSTANCE;
    }

    public List<Field> getFieldsForStep(int step) {
        List<Field> result = new ArrayList<>();
        for (Field field : fields) {
            if (field.getStep() == step) {
                result.add(field);
            }
        }
        return result;
    }

    public List<ViewInfo> createViewsForStep(List<Field> fieldsForStep, Context context, LinearLayout rootView) {
        List<ViewInfo> viewInfos = new ArrayList<>();

        for (Field field : fieldsForStep) {

            switch (field.getType()) {
                case "edit text":
                    FieldViewModel viewModel;
                    ViewDataBinding binding;
                    View view;
                    ViewInfo viewInfo;
                    view = LayoutInflater.from(context).inflate(R.layout.cp_edit_text, rootView, false);
                    binding = CpEditTextBinding.bind(view);
                    viewModel = new EditTextViewModel();
                    ((CpEditTextBinding) binding).setViewModel((EditTextViewModel) viewModel);
                    ((EditTextViewModel) viewModel).init(field);
                    viewInfo = new ViewInfo(binding, viewModel, view);
                    viewInfos.add(viewInfo);
                    rootView.addView(view);
                    break;
                case "year picker":
                    FieldViewModel viewModel2;
                    ViewDataBinding binding2;
                    View view2;
                    ViewInfo viewInfo2;
                    view2 = LayoutInflater.from(context).inflate(R.layout.cp_date_picker, rootView, false);
                    binding2 = CpDatePickerBinding.bind(view2);
                    viewModel2 = new DatePickerViewModel();
                    ((CpDatePickerBinding) binding2).setViewModel((DatePickerViewModel) viewModel2);
                    ((DatePickerViewModel) viewModel2).init(field);
                    viewInfo2 = new ViewInfo(binding2, viewModel2, view2);
                    viewInfos.add(viewInfo2);
                    rootView.addView(view2);
                    break;
                case "slider":
                    FieldViewModel viewModel3;
                    ViewDataBinding binding3;
                    View view3;
                    ViewInfo viewInfo3;
                    view3 = LayoutInflater.from(context).inflate(R.layout.cp_range_seekbar, rootView, false);
                    binding3 = CpRangeSeekbarBinding.bind(view3);
                    viewModel3 = new RangeSeekbarViewModel();
                    ((CpRangeSeekbarBinding) binding3).setViewModel((RangeSeekbarViewModel) viewModel3);
                    ((RangeSeekbarViewModel) viewModel3).init(field);
                    viewInfo3 = new ViewInfo(binding3, viewModel3, view3);
                    viewInfos.add(viewInfo3);
                    rootView.addView(view3);
                    break;
                case "select":
                    FieldViewModel viewModel4;
                    ViewDataBinding binding4;
                    View view4;
                    ViewInfo viewInfo4;
                    view4 = LayoutInflater.from(context).inflate(R.layout.cp_select, rootView, false);
                    binding4 = CpSelectBinding.bind(view4);
                    viewModel4 = new SelectViewModel();
                    ((CpSelectBinding) binding4).setViewModel((SelectViewModel) viewModel4);
                    ((SelectViewModel) viewModel4).init(field);
                    viewInfo4 = new ViewInfo(binding4, viewModel4, view4);
                    viewInfos.add(viewInfo4);
                    rootView.addView(view4);
                    break;
            }

        }
        return viewInfos;
    }

}
