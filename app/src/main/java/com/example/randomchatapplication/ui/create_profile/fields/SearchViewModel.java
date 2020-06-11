package com.example.randomchatapplication.ui.create_profile.fields;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.activites.profile_creation.CreateProfileActivity;
import com.example.randomchatapplication.activites.search_view.SearchViewActivity;
import com.example.randomchatapplication.adapters.hobbies.hobbiesAdapter.HobbiesAdapter;
import com.example.randomchatapplication.interfaces.HobbyInterface;
import com.example.randomchatapplication.models.Field;
import com.example.randomchatapplication.models.Hobby;
import com.example.randomchatapplication.viewmodels.FieldViewModel;

import java.util.List;

public class SearchViewModel extends FieldViewModel {

    public ObservableField<String> note = new ObservableField<>();
    public ObservableBoolean scrollingEnabled = new ObservableBoolean();
    public ObservableField<HobbiesAdapter> adapter = new ObservableField<>();
    public ObservableField<List<Hobby>> hobbies = new ObservableField<>();
    private HobbiesAdapter hobbiesAdapter;
    private Activity activity;
    private LinearLayout hobbiesContainer;
    public void init(Field field, Activity activity){
        note.set(field.getNote());
        scrollingEnabled.set(false);
        this.activity = activity;
//        hobbiesAdapter = new HobbiesAdapter();
//        adapter.set(hobbiesAdapter);

    }


    public void onClick(){
        Intent intent = new Intent(activity.getApplicationContext(), SearchViewActivity.class);
        activity.startActivityForResult(intent, CreateProfileActivity.hobbyRequest);
    }

    public void updateItems(List<Hobby> hobbies){
        this.hobbies.set(hobbies);
//        hobbiesAdapter.setItems(hobbies);
    }
}
