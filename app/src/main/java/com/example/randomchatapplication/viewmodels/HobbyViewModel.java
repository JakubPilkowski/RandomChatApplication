package com.example.randomchatapplication.viewmodels;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.example.randomchatapplication.models.Hobby;
import com.example.randomchatapplication.ui.create_profile.fields.SearchViewModel;

import java.util.ArrayList;
import java.util.List;

public class HobbyViewModel {
    public ObservableField<String> value = new ObservableField<>();
    private Hobby hobby;
    private ArrayList<Hobby> hobbies = new ArrayList<>();
    private SearchViewModel viewModel;
    public void init(Hobby hobby, List<Hobby>hobbies, SearchViewModel viewModel){
        value.set(hobby.getValue());
        this.hobby = hobby;
        this.hobbies.addAll(hobbies);
        this.viewModel = viewModel;
    }
    public void onClick(){
        hobbies.remove(hobby);
        viewModel.updateItems(hobbies);
    }

}
