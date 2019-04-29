package com.adilaytan.medicaldictionarypro.Adapters;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.adilaytan.medicaldictionarypro.SliderMenu.MainWords;
import com.adilaytan.medicaldictionarypro.SliderMenu.SearchWRTContent;
import com.adilaytan.medicaldictionarypro.SliderMenu.SubTerm;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;
    TabLayout tabLayout;

    public ViewPagerAdapter(FragmentManager fm, int NumOfTabs, TabLayout tabLayout) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.tabLayout = tabLayout;
    }
    public SubTerm tab2;
    public MainWords tab1;
    public SearchWRTContent tab3;


    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                tab1 = new MainWords();
                return tab1;
            case 1:
                tab2 = new SubTerm();
                return tab2;
            case 2:
                tab3 = new SearchWRTContent();
                return tab3;
            default:
                return null;
        }
    }
    public int getCount() {
        return mNumOfTabs;
    }
}