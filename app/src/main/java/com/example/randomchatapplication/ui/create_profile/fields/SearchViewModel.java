package com.example.randomchatapplication.ui.create_profile.fields;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import androidx.databinding.ObservableField;

import com.example.randomchatapplication.activites.search_view.SearchViewActivity;
import com.example.randomchatapplication.interfaces.HobbyInterface;
import com.example.randomchatapplication.models.Field;
import com.example.randomchatapplication.models.Hobby;
import com.example.randomchatapplication.models.ListenerContainer;
import com.example.randomchatapplication.viewmodels.FieldViewModel;

public class SearchViewModel extends FieldViewModel implements HobbyInterface {

    public ObservableField<String> note = new ObservableField<>();
    private Activity activity;
    public void init(Field field, Activity activity){
        note.set(field.getNote());
        this.activity = activity;
    }


    public void onClick(){
        Intent intent = new Intent(activity.getApplicationContext(), SearchViewActivity.class);
        ListenerContainer listenerContainer = new ListenerContainer(this);
        intent.putExtra("listener", listenerContainer);
        activity.startActivity(intent);
    }

    @Override
    public void onAdd(Hobby hobby) {
        Log.d("onAdd", "działa listener");
    }
}
