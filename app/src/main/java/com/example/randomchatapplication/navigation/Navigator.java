package com.example.randomchatapplication.navigation;

import android.util.Log;
import android.view.View;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.adapters.ViewPagerListAdapter;
import com.example.randomchatapplication.api.responses.FieldsResponse;
import com.example.randomchatapplication.base.BaseActivity;
import com.example.randomchatapplication.base.BaseFragment;
import com.example.randomchatapplication.helpers.FieldsHelper;
import com.example.randomchatapplication.ui.create_profile.profile.CreateProfileFragment;

public class Navigator {
    private BaseActivity activity;
    private int fragmentContainer;

    public static final int BACKSTACK_ADD = 2;
    public static final int BACKSTACK_REPLACE = 1;


    public void setActivity(BaseActivity activity) {
        this.activity = activity;
    }

    public void clearBackStack() {
        for (int i = 1; i < activity.getSupportFragmentManager().getBackStackEntryCount(); i++) {
            FragmentManager.BackStackEntry entry = activity.getSupportFragmentManager().getBackStackEntryAt(i);
            activity.getSupportFragmentManager().popBackStack(entry.getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

    }


    private void deleteUselessFragments(String tag) {
        for (int i = 0; i < activity.getSupportFragmentManager().getBackStackEntryCount(); i++) {
            FragmentManager.BackStackEntry backStackEntry = activity.getSupportFragmentManager().getBackStackEntryAt(i);
            String tmpTag = backStackEntry.getName();
            Log.d("fragmenty", tmpTag);
//            if (!tmpTag.contains(tag) && !tmpTag.equals(HomeFragment.TAG)) {
//                activity.getSupportFragmentManager().popBackStack(tmpTag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//            }
        }
    }

    private boolean isAvailable(String tag) {
        for (int i = 0; i < activity.getSupportFragmentManager().getBackStackEntryCount(); i++) {
            FragmentManager.BackStackEntry backStackEntry = activity.getSupportFragmentManager().getBackStackEntryAt(i);
            String tmpTag = backStackEntry.getName();
            if (tmpTag.equals(tag)) {
                return true;
            }
        }
        return false;
    }





    public void attach(BaseFragment fragment, String tag) {
        deleteUselessFragments(tag);
        if (!isAvailable(tag)) {
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(tag)
                    .replace(fragmentContainer, fragment, tag)
                    .setTransition(FragmentTransaction.TRANSIT_NONE)
                    .commit();
        } else {
            activity.getSupportFragmentManager().popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            activity.getSupportFragmentManager().beginTransaction().addToBackStack(tag).replace(fragmentContainer, fragment, tag)
                    .setTransition(FragmentTransaction.TRANSIT_NONE)
                    .commit();
        }
    }

    public void attachAdd(BaseFragment fragment, String tag){
        deleteUselessFragments(tag);
        if (!isAvailable(tag)) {
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(tag)
                    .add(fragmentContainer, fragment, tag)
                    .setTransition(FragmentTransaction.TRANSIT_NONE)
                    .commit();
        } else {
            activity.getSupportFragmentManager().popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            activity.getSupportFragmentManager().beginTransaction().addToBackStack(tag).replace(fragmentContainer, fragment, tag)
                    .setTransition(FragmentTransaction.TRANSIT_NONE)
                    .commit();
        }
    }


    public void profileSharedTransition(BaseFragment fragment, String tag, View sharedView, String sharedViewName){
        activity.getSupportFragmentManager()
                .beginTransaction()
                .addSharedElement(sharedView, sharedViewName)
                .replace(fragmentContainer, fragment, tag)
                .setReorderingAllowed(true)
                .addToBackStack(tag)
                .commit();
    }


    public ViewPagerListAdapter showCreateProfileFragments(FieldsResponse response, int statusBarHeight){
        ViewPagerListAdapter viewPagerListAdapter = new ViewPagerListAdapter(activity.getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        FieldsHelper.init(response.getPola(), response.getKroki());
        int size = response.getKroki().size();
        for (int i = 0; i < size; i++) {
            viewPagerListAdapter.addFragment(CreateProfileFragment.newInstance(i+1, statusBarHeight));
        }
        return viewPagerListAdapter;
    }

    public void showLoginScreen() {
//        deleteUselessFragments(LoginFragment.TAG);
    }

    public void setFragmentContainer(int fragmentContainer) {
        this.fragmentContainer = fragmentContainer;
    }
}
