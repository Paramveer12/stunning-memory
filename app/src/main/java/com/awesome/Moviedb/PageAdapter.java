package com.awesome.Moviedb;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {

    private int numofTabs;
    public PageAdapter(FragmentManager fm,int numofTabs) {

        super(fm);
        this.numofTabs = numofTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                return new Frag1();
            default:
                return new Frag2();
        }
    }

    @Override
    public int getCount() {
        return numofTabs;
    }
}
