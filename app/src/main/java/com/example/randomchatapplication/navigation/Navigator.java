package com.example.randomchatapplication.navigation;

import android.util.Log;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.randomchatapplication.base.BaseFragment;

public class Navigator {
    private FragmentActivity activity;
    private int fragmentContainer;
    public void setActivity(FragmentActivity activity) {
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
            Log.d("fragmenty",tmpTag);
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
    public void attach(BaseFragment fragment, String tag){
        deleteUselessFragments(tag);
        if (!isAvailable(tag)) {
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(tag)
                    .replace(fragmentContainer, fragment, tag)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        } else {
            activity.getSupportFragmentManager().popBackStack(tag,FragmentManager.POP_BACK_STACK_INCLUSIVE);
            activity.getSupportFragmentManager().beginTransaction().addToBackStack(tag).replace(fragmentContainer, fragment, tag)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        }
    }

    public void showLoginScreen(){
//        deleteUselessFragments(LoginFragment.TAG);
    }

    public void setFragmentContainer(int fragmentContainer) {
        this.fragmentContainer = fragmentContainer;

    }
//    public void showNoticeBoard() {
//        deleteUselessFragments(NoticeBoardFragment.TAG);
//        if (!isAvailable(NoticeBoardFragment.TAG)) {
//            activity.getSupportFragmentManager()
//                    .beginTransaction()
//                    .addToBackStack(NoticeBoardFragment.TAG)
//                    .replace(R.id.main_container, NoticeBoardFragment.newInstance(), NoticeBoardFragment.TAG)
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                    .commit();
//        } else {
//            activity.getSupportFragmentManager().popBackStack(NoticeBoardFragment.TAG,FragmentManager.POP_BACK_STACK_INCLUSIVE);
//            activity.getSupportFragmentManager().beginTransaction().addToBackStack(NoticeBoardFragment.TAG).replace(R.id.main_container, NoticeBoardFragment.newInstance(), EventsFragment.TAG)
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                    .commit();
//        }
//    }
//
//
//    public void showWeather() {
//        deleteUselessFragments(WeatherFragment.TAG);
//        if (!isAvailable(WeatherFragment.TAG)) {
//            activity.getSupportFragmentManager()
//                    .beginTransaction()
//                    .addToBackStack(WeatherFragment.TAG)
//                    .replace(R.id.main_container, WeatherFragment.newInstance(), WeatherFragment.TAG)
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                    .commit();
//        } else {
//            activity.getSupportFragmentManager().popBackStack(WeatherFragment.TAG,FragmentManager.POP_BACK_STACK_INCLUSIVE);
//            activity.getSupportFragmentManager().beginTransaction().addToBackStack(WeatherFragment.TAG).replace(R.id.main_container, WeatherFragment.newInstance(), EventsFragment.TAG)
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                    .commit();
//        }
//    }
//
//    public void showEvents() {
//        deleteUselessFragments(EventsFragment.TAG);
//        if (!isAvailable(EventsFragment.TAG)){
//            activity.getSupportFragmentManager()
//                    .beginTransaction()
//                    .addToBackStack(EventsFragment.TAG)
//                    .replace(R.id.main_container, EventsFragment.newInstance(), EventsFragment.TAG)
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                    .commit();
//        }
//        else {
//            activity.getSupportFragmentManager().popBackStack(EventsFragment.TAG,FragmentManager.POP_BACK_STACK_INCLUSIVE);
//            activity.getSupportFragmentManager().beginTransaction().addToBackStack(EventsFragment.TAG).replace(R.id.main_container, EventsFragment.newInstance(), EventsFragment.TAG)
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                    .commit();
//        }
//    }
//
//    public void showOffers() {
//        deleteUselessFragments(OffersFragment.TAG);
//        if (!isAvailable(OffersFragment.TAG)) {
//            activity.getSupportFragmentManager()
//                    .beginTransaction()
//                    .addToBackStack(OffersFragment.TAG)
//                    .replace(R.id.main_container, OffersFragment.newInstance(), OffersFragment.TAG)
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                    .commit();
//
//        } else {
//            activity.getSupportFragmentManager().popBackStack(OffersFragment.TAG,FragmentManager.POP_BACK_STACK_INCLUSIVE);
//            activity.getSupportFragmentManager().beginTransaction().addToBackStack(OffersFragment.TAG).replace(R.id.main_container, OffersFragment.newInstance(), EventsFragment.TAG)
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                    .commit();
//        }
//    }
//
//    public void showTimetable() {
//        deleteUselessFragments(TimetableFragment.TAG);
//        if (!isAvailable(TimetableFragment.TAG)) {
//            activity.getSupportFragmentManager()
//                    .beginTransaction()
//                    .addToBackStack(TimetableFragment.TAG)
//                    .replace(R.id.main_container, TimetableFragment.newInstance(), TimetableFragment.TAG)
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                    .commit();
//        } else {
//            activity.getSupportFragmentManager().popBackStack(TimetableFragment.TAG,FragmentManager.POP_BACK_STACK_INCLUSIVE);
//            activity.getSupportFragmentManager().beginTransaction().addToBackStack(TimetableFragment.TAG).replace(R.id.main_container, TimetableFragment.newInstance(), EventsFragment.TAG)
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                    .commit();
//        }
//    }
//
//    public void showOffices() {
//        activity.getSupportFragmentManager()
//                .beginTransaction()
//                .addToBackStack(OfficesFragment.TAG)
//                .replace(R.id.main_container, OfficesFragment.newInstance(), OfficesFragment.TAG)
//                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                .commit();
//    }
//
//    public void showHome() {
//        activity.getSupportFragmentManager()
//                .beginTransaction()
//                .addToBackStack(HomeFragment.TAG)
//                .replace(R.id.main_container, HomeFragment.newInstance(), HomeFragment.TAG)
//                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                .commit();
//    }
//
//    public void showPostOffices() {
//        activity.getSupportFragmentManager()
//                .beginTransaction()
//                .addToBackStack(PostOfficesFragment.TAG)
//                .replace(R.id.main_container, PostOfficesFragment.newInstance(), PostOfficesFragment.TAG)
//                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                .commit();
//    }
//
//    public void addNotice() {
//        deleteUselessFragments(NoticeBoardFragment.TAG);
//        activity.getSupportFragmentManager()
//                .beginTransaction()
//                .addToBackStack(NoticeBoardFragment.TAG + AddNoticeFragment.TAG)
//                .replace(R.id.main_container, AddNoticeFragment.newInstance(), NoticeBoardFragment.TAG + AddNoticeFragment.TAG)
//                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                .commit();
//    }
//
//    public void showNoticeDetails(Notice notice) {
//        deleteUselessFragments(NoticeBoardFragment.TAG);
//        activity.getSupportFragmentManager()
//                .beginTransaction()
//                .addToBackStack(NoticeBoardFragment.TAG + NoticeDetailsFragment.TAG)
//                .replace(R.id.main_container, NoticeDetailsFragment.newInstance(notice), NoticeBoardFragment.TAG + NoticeDetailsFragment.TAG)
//                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                .commit();
//    }
//
//    public void openSite(String uri) {
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse(uri));
//        activity.startActivity(intent);
//    }
//
//    public void showEvent(Event event) {
//        deleteUselessFragments(EventsFragment.TAG);
//        activity.getSupportFragmentManager()
//                .beginTransaction()
//                .addToBackStack(EventsFragment.TAG + EventDetailsFragment.TAG)
//                .replace(R.id.main_container, EventDetailsFragment.newInstance(event), EventsFragment.TAG + EventDetailsFragment.TAG)
//                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                .commit();
//    }
//
//    public void showLineTimetables() {
//        deleteUselessFragments(TimetableFragment.TAG);
//        activity.getSupportFragmentManager()
//                .beginTransaction()
//                .addToBackStack(TimetableFragment.TAG + LineTimetablesFragment.TAG)
//                .replace(R.id.main_container, LineTimetablesFragment.newInstance(), TimetableFragment.TAG + LineTimetablesFragment.TAG)
//                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                .commit();
//    }
//
//    public void showLine(CommunicationLine line) {
//        deleteUselessFragments(TimetableFragment.TAG);
//        activity.getSupportFragmentManager()
//                .beginTransaction()
//                .addToBackStack(TimetableFragment.TAG + SingleLineFragment.TAG)
//                .replace(R.id.main_container, SingleLineFragment.newInstance(line), TimetableFragment.TAG + SingleLineFragment.TAG)
//                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                .commit();
//    }
//
//    public void showMap(CommunicationLine line) {
//        deleteUselessFragments(TimetableFragment.TAG);
//        activity.getSupportFragmentManager()
//                .beginTransaction()
//                .addToBackStack(TimetableFragment.TAG + MapFragment.TAG)
//                .replace(R.id.main_container, MapFragment.newInstance(line), TimetableFragment.TAG + MapFragment.TAG)
//                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                .commit();
//    }
//
//    public void showLineDetails(BusStop stop, CommunicationLine line) {
//        deleteUselessFragments(TimetableFragment.TAG);
//        activity.getSupportFragmentManager()
//                .beginTransaction()
//                .addToBackStack(TimetableFragment.TAG + LineDetailsFragment.TAG)
//                .replace(R.id.main_container, LineDetailsFragment.newInstance(stop, line), TimetableFragment.TAG + LineDetailsFragment.TAG)
//                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                .commit();
//    }
//
//    public void showSetRouteFragment() {
//        deleteUselessFragments(TimetableFragment.TAG);
//        activity.getSupportFragmentManager()
//                .beginTransaction()
//                .addToBackStack(TimetableFragment.TAG + SetRouteFragment.TAG)
//                .replace(R.id.main_container, SetRouteFragment.newInstance(), TimetableFragment.TAG + SetRouteFragment.TAG)
//                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                .commit();
//    }
}
