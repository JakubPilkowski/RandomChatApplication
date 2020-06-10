package com.example.randomchatapplication.activites.search_view;

import android.util.Log;
import android.widget.SearchView;

import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.RecyclerView;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.adapters.searchview_hobby.HobbySearchViewAdapter;
import com.example.randomchatapplication.api.BaseCallback;
import com.example.randomchatapplication.api.MockyConnection;
import com.example.randomchatapplication.api.responses.HobbiesResponse;
import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.databinding.ActivitySearchViewBinding;
import com.example.randomchatapplication.helpers.ProgressDialogManager;
import com.example.randomchatapplication.interfaces.HobbyInterface;

public class SearchViewActivityViewModel extends BaseViewModel {

    public ObservableField<SearchView.OnQueryTextListener> listener = new ObservableField<>();
    public ObservableField<RecyclerView.Adapter> adapter = new ObservableField<>();
    private HobbySearchViewAdapter hobbySearchViewAdapter = new HobbySearchViewAdapter();
    private HobbyInterface hobbyListener;
    public void init(HobbyInterface hobbyListener){
        this.hobbyListener = hobbyListener;
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
            hobbySearchViewAdapter.setListener(hobbyListener);
            hobbySearchViewAdapter.setItems(response.getZainteresowania());
            adapter.set(hobbySearchViewAdapter);
            ProgressDialogManager.get().dismiss();
        }

        @Override
        public void onError(String message) {
            ProgressDialogManager.get().dismiss();
        }
    };
    public void onClick(){
        ((ActivitySearchViewBinding)getActivityOrFragmentBinding()).search.setIconified(false);
    }
}
