package com.example.randomchatapplication.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.databinding.CpCheckedBinding;
import com.example.randomchatapplication.databinding.CpDatePickerBinding;
import com.example.randomchatapplication.databinding.CpEditTextBinding;
import com.example.randomchatapplication.databinding.CpRangeSeekbarBinding;
import com.example.randomchatapplication.databinding.CpSeekbarBinding;
import com.example.randomchatapplication.databinding.CpSelectBinding;
import com.example.randomchatapplication.models.Field;
import com.example.randomchatapplication.models.Step;
import com.example.randomchatapplication.models.ViewInfo;
import com.example.randomchatapplication.ui.create_profile.fields.CheckBoxViewModel;
import com.example.randomchatapplication.ui.create_profile.fields.DatePickerViewModel;
import com.example.randomchatapplication.ui.create_profile.fields.EditTextViewModel;
import com.example.randomchatapplication.ui.create_profile.fields.RangeSeekbarViewModel;
import com.example.randomchatapplication.ui.create_profile.fields.SeekbarViewModel;
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
        FieldViewModel viewModel;
        ViewDataBinding binding;
        View view;
        ViewInfo viewInfo;
        for (Field field : fieldsForStep) {

            switch (field.getType()) {
                case "edit text":
                    view = LayoutInflater.from(context).inflate(R.layout.cp_edit_text, rootView, false);
                    binding = CpEditTextBinding.bind(view);
                    viewModel = new EditTextViewModel();
                    ((CpEditTextBinding) binding).setViewModel((EditTextViewModel) viewModel);
                    ((EditTextViewModel) viewModel).init(field);
                    viewInfo = new ViewInfo(binding, viewModel, view);
                    viewInfos.add(viewInfo);
                    break;
                case "year picker":
                    view = LayoutInflater.from(context).inflate(R.layout.cp_date_picker, rootView, false);
                    binding = CpDatePickerBinding.bind(view);
                    viewModel = new DatePickerViewModel();
                    ((CpDatePickerBinding) binding).setViewModel((DatePickerViewModel) viewModel);
                    ((DatePickerViewModel) viewModel).init(field);
                    viewInfo = new ViewInfo(binding, viewModel, view);
                    viewInfos.add(viewInfo);
                    break;
                case "slider":
                    view = LayoutInflater.from(context).inflate(R.layout.cp_seekbar, rootView, false);
                    binding = CpSeekbarBinding.bind(view);
                    viewModel = new SeekbarViewModel();
                    ((CpSeekbarBinding) binding).setViewModel((SeekbarViewModel) viewModel);
                    ((SeekbarViewModel) viewModel).init(field);
                    viewInfo = new ViewInfo(binding, viewModel, view);
                    viewInfos.add(viewInfo);
                    break;
                case "range slider":
                    view = LayoutInflater.from(context).inflate(R.layout.cp_range_seekbar, rootView, false);
                    binding = CpRangeSeekbarBinding.bind(view);
                    viewModel = new RangeSeekbarViewModel();
                    ((CpRangeSeekbarBinding) binding).setViewModel((RangeSeekbarViewModel) viewModel);
                    ((RangeSeekbarViewModel) viewModel).init(field);
                    viewInfo = new ViewInfo(binding, viewModel, view);
                    viewInfos.add(viewInfo);
                    break;
                case "select":
                    view = LayoutInflater.from(context).inflate(R.layout.cp_select, rootView, false);
                    binding = CpSelectBinding.bind(view);
                    viewModel = new SelectViewModel();
                    ((CpSelectBinding) binding).setViewModel((SelectViewModel) viewModel);
                    ((SelectViewModel) viewModel).init(field);
                    viewInfo = new ViewInfo(binding, viewModel, view);
                    viewInfos.add(viewInfo);
                    break;
                case "check":
                    view = LayoutInflater.from(context).inflate(R.layout.cp_checked, rootView, false);
                    binding = CpCheckedBinding.bind(view);
                    viewModel = new CheckBoxViewModel();
                    ((CpCheckedBinding)binding).setViewModel((CheckBoxViewModel)viewModel);
                    ((CheckBoxViewModel)viewModel).init(field);
                    viewInfo = new ViewInfo(binding, viewModel, view);
                    viewInfos.add(viewInfo);
            }

        }
        return viewInfos;
    }

}
