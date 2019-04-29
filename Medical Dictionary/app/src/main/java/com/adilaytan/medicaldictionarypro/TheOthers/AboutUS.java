package com.adilaytan.medicaldictionarypro.TheOthers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.adilaytan.medicaldictionarypro.R;
import com.adilaytan.medicaldictionarypro.Classes.Class;


public class AboutUS extends AppCompatActivity {

    private Class Sınıf;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Sınıf =  new Class();
        Sınıf.SetLanguage(this);
        Sınıf.setProgramTheme(this);
        setContentView(R.layout.about);
        getSupportActionBar().setTitle(getResources().getString(R.string.about_layout));

        if (savedInstanceState != null)
        {
            Sınıf.SetLanguage(this);
            getSupportActionBar().setTitle(getResources().getString(R.string.about_layout));
        }
    }

}
