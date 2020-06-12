//package com.example.randomchatapplication.adapters.hobbies.hobbiesAdapter;
//
//import androidx.databinding.ObservableField;
//
//import com.example.randomchatapplication.base.BaseAdapterViewModel;
//import com.example.randomchatapplication.models.Hobby;
//
//public class HobbiesAdapterViewModel extends BaseAdapterViewModel {
//
//    public ObservableField<String> value = new ObservableField<>();
//
//    private Hobby hobby;
//    private HobbiesAdapter adapter;
//    @Override
//    public void init(Object[] values) {
//        hobby = (Hobby) values[0];
//        adapter = (HobbiesAdapter) values[1];
//        value.set(hobby.getValue());
//    }
//
//    public void onClick(){
//        adapter.onRemoveItem(hobby);
//    }
//}
