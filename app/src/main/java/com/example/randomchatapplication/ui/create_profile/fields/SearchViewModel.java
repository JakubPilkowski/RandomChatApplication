package com.example.randomchatapplication.ui.create_profile.fields;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.RecyclerView;

import com.example.randomchatapplication.adapters.hobbies.hobbiesSearchViewAdapter.HobbySearchViewAdapter;
import com.example.randomchatapplication.base.BaseActivity;
import com.example.randomchatapplication.helpers.HobbiesHelper;
import com.example.randomchatapplication.interfaces.SearchViewListener;
import com.example.randomchatapplication.models.Field;
import com.example.randomchatapplication.models.Hobby;
import com.example.randomchatapplication.viewmodels.FieldViewModel;

import java.util.ArrayList;

public class SearchViewModel extends FieldViewModel implements SearchViewListener {

    public ObservableField<String> note = new ObservableField<>();
    public ObservableField<SearchViewModel> viewModel = new ObservableField<>(this);
    public ObservableBoolean iconified = new ObservableBoolean(true);
    public ObservableField<RecyclerView.Adapter> adapter = new ObservableField<>();
    private HobbySearchViewAdapter hobbySearchViewAdapter = new HobbySearchViewAdapter();
    public ObservableField<String> query = new ObservableField<>();
    public ObservableField<SearchView.OnQueryTextListener> queryListener = new ObservableField<>();
    public ObservableField<SearchView.OnCloseListener> cancelListener = new ObservableField<>();
    private Activity activity;
    public void init(Field field, Activity activity){
        note.set(field.getNote());
        this.activity = activity;
        hobbySearchViewAdapter.setItems(HobbiesHelper.get().getHobbies());
        hobbySearchViewAdapter.setSearchViewListener(this);
        queryListener.set(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                query.set(newText);
                hobbySearchViewAdapter.getFilter().filter(newText);
                return false;
            }
        });

        cancelListener.set(() -> {
            query.set("");
            iconified.set(true);
            return false;
        });
        adapter.set(hobbySearchViewAdapter);

    }



    public void onClick() {
        iconified.set(false);
    }

    @Override
    public void onChecked() {
        query.set("");
        ((BaseActivity) activity).hideKeyboard();
    }



}
