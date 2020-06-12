package com.example.randomchatapplication.ui.create_profile.fields;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.widget.LinearLayout;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.example.randomchatapplication.activites.profile_creation.CreateProfileActivity;
import com.example.randomchatapplication.activites.search_view.SearchViewActivity;
import com.example.randomchatapplication.models.Field;
import com.example.randomchatapplication.models.Hobby;
import com.example.randomchatapplication.viewmodels.FieldViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchViewModel extends FieldViewModel {

    public ObservableField<String> note = new ObservableField<>();

    public ObservableField<ArrayList<Hobby>> hobbies = new ObservableField<>();
    public ObservableField<SearchViewModel> viewModel = new ObservableField<>(this);
    private Activity activity;
    public void init(Field field, Activity activity){
        note.set(field.getNote());
        this.activity = activity;
        this.hobbies.set(new ArrayList<Hobby>());
    }


    public void onClick(){
        Intent intent = new Intent(activity.getApplicationContext(), SearchViewActivity.class);
        intent.putParcelableArrayListExtra("hobbies", hobbies.get());
        activity.startActivityForResult(intent, CreateProfileActivity.hobbyRequest);
    }


    public void updateItems(ArrayList<Hobby> hobbies){
        this.hobbies.set(hobbies);
    }
}
