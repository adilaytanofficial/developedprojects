package com.adilaytan.medicaldictionarypro.Intro;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.adilaytan.medicaldictionarypro.R;

public class IntroManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    Activity activity;

    public IntroManager(Context context)
    {
        this.context = context;
        this.activity = (Activity) context;
        pref = PreferenceManager.getDefaultSharedPreferences(context);
        editor = pref.edit();
    }

    public void setIntroShow(boolean state)
    {
            editor.putBoolean("intro_show",state);
            editor.commit();
    }

    public boolean getIntroShow()
    {
        boolean intro = pref.getBoolean("intro_show",true);
        return intro;
    }

    public void Finish()
    {
        activity.finish();
    }

    public void skip_alert()
    {
        AlertDialog.Builder cevap = new AlertDialog.Builder(context);
        cevap.setMessage(context.getResources().getString(R.string.skipping)).setPositiveButton(R.string.yes ,new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int is) {
                setIntroShow(false);
                context.startActivity(new Intent("com.adilaytan.medicaldictionarypro.MAINACTIVITY"));
            }
        })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        AlertDialog baslat = cevap.create();
        baslat.show();
    }
}
