package com.example.randomchatapplication.helpers;

import android.app.Activity;

import com.example.randomchatapplication.activites.main.MainActivity;
import com.example.randomchatapplication.navigation.Navigator;
import com.example.randomchatapplication.ui.profiles.ProfilesFragment;

public class MenuHelper {

    public static void clickedMenu(MainActivity activity, int menuActionId) {
        switch (menuActionId) {
            case 1:
                showProfiles(activity);
                break;
                case 2:
                    break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }
    }

    private static void showProfiles(MainActivity activity){
        if(activity.getCurrentFragment() instanceof ProfilesFragment) {
            activity.closeDrawer();
        }else {
            activity.getNavigator().attach(ProfilesFragment.newInstance(), ProfilesFragment.TAG);
        }
    }
}
