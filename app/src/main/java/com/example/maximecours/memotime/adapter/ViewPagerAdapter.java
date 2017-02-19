package com.example.maximecours.memotime.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.ListFragment;

import com.example.maximecours.memotime.fragments.AnimateFragment;
import com.example.maximecours.memotime.fragments.ListeFragment;

/**
 * Created by MaximeCours on 13/02/2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private final static int NOMBRE_PAGES = 2;

    public ViewPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);

    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new ListeFragment();
        }else{
            return new AnimateFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "MES MEMOS";
            default:
                return "ANIMATION";
        }
    }

    @Override
    public int getCount() {
        return NOMBRE_PAGES;
    }
}
