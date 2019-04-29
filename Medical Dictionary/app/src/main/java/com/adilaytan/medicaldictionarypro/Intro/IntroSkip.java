package com.adilaytan.medicaldictionarypro.Intro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.adilaytan.medicaldictionarypro.Classes.Class;
import com.adilaytan.medicaldictionarypro.R;

public class IntroSkip extends AppCompatActivity {


    public void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        Class Sınıf = new Class();
        Sınıf.makeFullScreen(IntroSkip.this);
        Sınıf.SetLanguage(this);
        final IntroManager intromanager = new IntroManager(this);

        final boolean durum = intromanager.getIntroShow();


            setContentView(R.layout.intro_home);



        if (savedInstanceState != null)
        {
            Sınıf.SetLanguage(this);
        }



        final Button discover = (Button) findViewById(R.id.discover);
        final TextView skip = (TextView) findViewById(R.id.skip_the_show);

        discover.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent("com.adilaytan.medicaldictionarypro.intro.INTROSCREEN"));
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                intromanager.skip_alert();
            }
        });

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
        }
        return super.onKeyDown(keyCode, event);
    }
}
