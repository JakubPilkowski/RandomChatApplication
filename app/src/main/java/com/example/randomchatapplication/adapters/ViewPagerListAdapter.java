package com.example.randomchatapplication.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.randomchatapplication.base.BaseFragment;

import java.util.ArrayList;

public class ViewPagerListAdapter extends FragmentPagerAdapter {
    private ArrayList<BaseFragment> fragments = new ArrayList<>();

    public ViewPagerListAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    public void addFragment(BaseFragment fragment) {
        fragments.add(fragment);
    }

    public ArrayList<BaseFragment> getFragments() {
        return fragments;
    }
}
