package com.example.randomchatapplication.adapters.profile_hobbies;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.base.BaseAdapterViewModel;
import com.example.randomchatapplication.models.Hobby;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ProfileHobbiesAdapterViewModel  {


    public ObservableField<String> value = new ObservableField<>();
    public ObservableInt backgroundColor = new ObservableInt();

    private Hobby hobby;

    public void init(Hobby hobby) {
        this.hobby = hobby;
        value.set(hobby.getValue());
        ArrayList<Integer> colors = new ArrayList<>(Arrays.asList( R.color.colorBlack, R.color.iconGrayLight
                , R.color.gold, R.color.red, R.color.orange, R.color.yellow, R.color.lime, R.color.green, R.color.cyan, R.color.blue, R.color.purple,
                R.color.magenta, R.color.pink, R.color.apricot, R.color.mint, R.color.lavender, R.color.maroon,
                R.color.brown, R.color.olive, R.color.teal, R.color.navy, R.color.diamond));
        Random random = new Random();
        int color = random.nextInt(21);
        backgroundColor.set(colors.get(color));
    }
}
