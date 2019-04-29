package com.adilaytan.medicaldictionarypro.Settings;


import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.adilaytan.medicaldictionarypro.Classes.Class;
import com.adilaytan.medicaldictionarypro.R;

public abstract class AppCompatPreferenceActivity extends PreferenceActivity {

    private AppCompatDelegate mDelegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getDelegate().installViewFactory();
        getDelegate().onCreate(savedInstanceState);
        Class yeni = new Class();
        String themen = yeni.getThemeName(this);
        changeToolbarColor(themen);
        getSupportActionBar().setTitle(getResources().getString(R.string.settings).toString());
        yeni.SetLanguage(this);
        super.onCreate(savedInstanceState);
    }

    public void statusNavbarColor(int color){
        try{
            if(Build.VERSION.SDK_INT>=21){
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                getWindow().setStatusBarColor(getResources().getColor(color));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    private void changeToolbarColor(String themename)
    {
        switch (themename)
        {
            case "2131361933":
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.def_color)));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    statusNavbarColor(R.color.def_color);
                }
                break;
            case "2131362063":
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.yesil_color)));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    statusNavbarColor(R.color.yesil_color);
                }
                break;
            case "2131361981":
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.siyah_color)));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    statusNavbarColor(R.color.siyah_color);
                }
                break;
            case "2131362065":
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.kirmizi_color)));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    statusNavbarColor(R.color.kirmizi_color);
                }
                break;
            case "2131361983":
              getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.pembe_color)));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    statusNavbarColor(R.color.pembe_color);
                }
                break;
            default:
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.def_color)));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    statusNavbarColor(R.color.def_color);
                }
                break;
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getDelegate().onPostCreate(savedInstanceState);
    }

    public ActionBar getSupportActionBar() {
        return getDelegate().getSupportActionBar();
    }

    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        getDelegate().setSupportActionBar(toolbar);
    }

    @Override
    public MenuInflater getMenuInflater() {
        return getDelegate().getMenuInflater();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        getDelegate().setContentView(layoutResID);
    }

    @Override
    public void setContentView(View view) {
        getDelegate().setContentView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        getDelegate().setContentView(view, params);
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        getDelegate().addContentView(view, params);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        getDelegate().onPostResume();
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        getDelegate().setTitle(title);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getDelegate().onConfigurationChanged(newConfig);
    }

    @Override
    protected void onStop() {
        super.onStop();
        getDelegate().onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getDelegate().onDestroy();
    }

    public void invalidateOptionsMenu() {
        getDelegate().invalidateOptionsMenu();
    }

    private AppCompatDelegate getDelegate() {
        if (mDelegate == null) {
            mDelegate = AppCompatDelegate.create(this, null);
        }
        return mDelegate;
    }
}
