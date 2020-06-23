package com.example.randomchatapplication.ui.create_profile.fields;

import android.app.Activity;
import android.widget.SearchView;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.RecyclerView;

import com.example.randomchatapplication.adapters.hobbies.hobbiesSearchViewAdapter.HobbySearchViewAdapter;
import com.example.randomchatapplication.interfaces.SearchViewListener;
import com.example.randomchatapplication.models.Field;
import com.example.randomchatapplication.models.Hobby;
import com.example.randomchatapplication.viewmodels.FieldViewModel;

import java.util.ArrayList;

public class SearchViewModel extends FieldViewModel implements SearchViewListener {

    public ObservableField<String> note = new ObservableField<>();

    public ObservableField<ArrayList<Hobby>> hobbies = new ObservableField<>();
    public ObservableField<SearchViewModel> viewModel = new ObservableField<>(this);
    public ObservableBoolean iconified = new ObservableBoolean(true);
    public ObservableField<RecyclerView.Adapter> adapter = new ObservableField<>();
    private HobbySearchViewAdapter hobbySearchViewAdapter = new HobbySearchViewAdapter();
    public ObservableField<String> query = new ObservableField<>();
    private Activity activity;
    public ObservableField<SearchView.OnQueryTextListener> queryListener = new ObservableField<>();
    public ObservableField<SearchView.OnCloseListener> cancelListener = new ObservableField<>();

    public void init(Field field, Activity activity){
        note.set(field.getNote());
        this.activity = activity;
        this.hobbies.set(new ArrayList<Hobby>());
        hobbySearchViewAdapter.setSearchViewListener(this);
        queryListener.set(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                hobbySearchViewAdapter.getFilter().filter(newText);
                return false;
            }
        });
        cancelListener.set(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                iconified.set(true);
                return false;
            }
        });
        adapter.set(hobbySearchViewAdapter);

    }



    public void onClick() {
        iconified.set(false);
    }

    @Override
    public void onChecked() {
        query.set("");
        iconified.set(true);
    }
}
