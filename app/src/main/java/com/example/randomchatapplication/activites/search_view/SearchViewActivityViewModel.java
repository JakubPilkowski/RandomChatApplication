package com.example.randomchatapplication.activites.search_view;

import android.widget.SearchView;

import androidx.databinding.ObservableField;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.databinding.ActivityCreateProfileBinding;
import com.example.randomchatapplication.databinding.ActivitySearchViewBinding;

public class SearchViewActivityViewModel extends BaseViewModel {

    public ObservableField<SearchView.OnQueryTextListener> listener = new ObservableField<>();

    public void init(){
        SearchViewActivity activity = (SearchViewActivity) getActivity();
        activity.setSupportActionBar(((ActivitySearchViewBinding) getActivityOrFragmentBinding()).zainteresowaniaToolbar);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setHomeAsUpIndicator(getActivity().getResources().getDrawable(R.drawable.ic_arrow_back));
        listener.set(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
    }

    public void onClick(){
        ((ActivitySearchViewBinding)getActivityOrFragmentBinding()).search.setIconified(false);
    }
}
