package com.example.memeit;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    public FragmentAdapter(FragmentManager fgM){
        super(fgM);
    }
    // position of fragments
    @Override
    public Fragment getItem(int position){
        if(position == 0){
            return new CodingMemesFragment();
        }
        if(position == 1){
            return new WholeSomeMemesFragment();
        }
        else{
            return new JokesFragment();
        }
    }
    // Viewpager asking for how many pages
    @Override
    public int getCount() {
        return 3;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "Coding";
        }
        else if (position == 1)
        {
            title = "Memes";
        }
        else if (position == 2)
        {
            title = "Jokes";
        }
        return title;
    }
}