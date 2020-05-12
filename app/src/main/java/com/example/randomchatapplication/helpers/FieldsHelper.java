package com.example.randomchatapplication.helpers;

import android.content.Context;
import android.view.View;

import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.models.Field;
import com.example.randomchatapplication.models.Step;

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


    public static void init(List<Field>fields, List<Step> steps){
        INSTANCE = new FieldsHelper(fields, steps);
    }

    public static FieldsHelper get(){
        return INSTANCE;
    }

    public List<Field>getFieldsForStep(int step){
        List<Field>result = new ArrayList<>();
        for(Field field : fields){
            if(field.getStep()==step){
                result.add(field);
            }
        }
        return result;
    }

    public List<View> createViewsForStep(List<Field>fieldsForStep, Context context){
        return new ArrayList<>();
    }

}
