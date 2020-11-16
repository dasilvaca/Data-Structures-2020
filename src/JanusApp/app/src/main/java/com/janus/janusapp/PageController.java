package com.janus.janusapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageController extends FragmentPagerAdapter {
    int numOfTabs;

    public PageController(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numOfTabs = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new OwnProjects();
            case 1:
                return new UserFollowedProjects();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 0;
    }
}
