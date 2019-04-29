package com.adilaytan.medicaldictionarypro.Intro;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class IntroAdapter extends PagerAdapter {

    private LayoutInflater layoutInflater;
    private int[] layouts;
    Context context;

    public IntroAdapter(Context context,int[] layouts)
    {
        this.context = context;
        this.layouts = layouts;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v  = layoutInflater.inflate(layouts[position],container,false);
        container.addView(v);
        return v;
    }

    public int getCount() {
        return layouts.length;
    }

    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        View v = (View)object;
        container.removeView(v);
    }
}
