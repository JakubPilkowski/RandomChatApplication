package com.example.randomchatapplication.activites.search_view;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.SearchView;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.RecyclerView;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.adapters.hobbies.hobbiesSearchViewAdapter.HobbySearchViewAdapter;
import com.example.randomchatapplication.api.BaseCallback;
import com.example.randomchatapplication.api.MockyConnection;
import com.example.randomchatapplication.api.responses.HobbiesResponse;
import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.databinding.ActivitySearchViewBinding;
import com.example.randomchatapplication.helpers.ProgressDialogManager;
import com.example.randomchatapplication.interfaces.SearchViewListener;
import com.example.randomchatapplication.models.Hobby;

import java.util.ArrayList;
import java.util.List;

public class SearchViewActivityViewModel extends BaseViewModel implements SearchViewListener {

    public ObservableField<SearchView.OnQueryTextListener> listener = new ObservableField<>();
    public ObservableField<RecyclerView.Adapter> adapter = new ObservableField<>();
    private HobbySearchViewAdapter hobbySearchViewAdapter = new HobbySearchViewAdapter();
    private SearchViewActivity activity;
    private ArrayList<Hobby> hobbies = new ArrayList<>();

    public void init(ArrayList<Hobby> hobbies) {
        this.hobbies.addAll(hobbies);
        activity = (SearchViewActivity) getActivity();
        activity.setSupportActionBar(((ActivitySearchViewBinding) getActivityOrFragmentBinding()).zainteresowaniaToolbar);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setHomeAsUpIndicator(getActivity().getResources().getDrawable(R.drawable.ic_arrow_back));
        hobbySearchViewAdapter.setSearchViewListener(this);
        listener.set(new SearchView.OnQueryTextListener() {
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

        MockyConnection.get().getHobbies(callback);
        ProgressDialogManager.get().show();
    }

    private BaseCallback<HobbiesResponse> callback = new BaseCallback<HobbiesResponse>() {
        @Override
        public void onSuccess(HobbiesResponse response) {
            if (hobbies.size() > 0) {
                ArrayList<Hobby> responseHobbies = response.getZainteresowania();
                for (Hobby hobby : responseHobbies) {
                    for (Hobby checkedHobbies : hobbies) {
                        if (hobby.getValue().equals(checkedHobbies.getValue())) {
                            hobby.setChecked(true);
                        }
                    }
                }
                hobbySearchViewAdapter.setItems(responseHobbies);
            } else {
                hobbySearchViewAdapter.setItems(response.getZainteresowania());
            }
            adapter.set(hobbySearchViewAdapter);
            ProgressDialogManager.get().dismiss();
        }

        @Override
        public void onError(String message) {
            ProgressDialogManager.get().dismiss();
        }
    };

    public void onClick() {
        ((ActivitySearchViewBinding) getActivityOrFragmentBinding()).hobbiesSearch.setIconified(false);
    }

    public void onConfirmClick() {
        Intent resultIntent = new Intent();
        ArrayList<Hobby> items = hobbySearchViewAdapter.getItems();
        ArrayList<Hobby> checkedItems = new ArrayList<>();
        for (Hobby hobby : items) {
            if (hobby.isChecked()) {
                Log.d("onConfirmClick", hobby.getValue());
                checkedItems.add(hobby);
            }
        }
        resultIntent.putParcelableArrayListExtra("hobbies", checkedItems);
        activity.setResult(Activity.RESULT_OK, resultIntent);
        activity.finish();
    }

    @Override
    public void onChecked() {
        ((ActivitySearchViewBinding) getActivityOrFragmentBinding()).hobbiesSearch.setQuery("",false);
        ((ActivitySearchViewBinding) getActivityOrFragmentBinding()).hobbiesSearch.setIconified(true);
    }
}
