package com.adilaytan.medicaldictionarypro.Intro;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.adilaytan.medicaldictionarypro.Classes.Class;
import com.adilaytan.medicaldictionarypro.R;

public class IntroScreen extends AppCompatActivity {

    private ViewPager viewPager;
    private IntroAdapter introadapter;
    private IntroManager intromanager;
    private LinearLayout dotslayout,line_layout;
    Button next,skip;
    private int[] layouts;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Class Sınıf = new Class();
        Sınıf.makeFullScreen(IntroScreen.this);
        Sınıf.SetLanguage(this);

        setContentView(R.layout.intro_manager);

        if (savedInstanceState != null)
        {
            Sınıf.SetLanguage(this);
        }

        layouts = new int[]{R.layout.intro_1,R.layout.intro_2,R.layout.intro_3};
        dotslayout = (LinearLayout) findViewById(R.id.layout_dots);
        line_layout = (LinearLayout) findViewById(R.id.layout_line);

        viewPager = (ViewPager) findViewById(R.id.intro_viewpager);

        skip = (Button) findViewById(R.id.btn_skip);
        next = (Button) findViewById(R.id.btn_next);


        introadapter  = new IntroAdapter(this,layouts);
        intromanager = new IntroManager(this);

        viewPager.setAdapter(introadapter);
        viewPager.addOnPageChangeListener(viewpagelisten);

        // İzin isteme Request
        ActivityCompat.requestPermissions(IntroScreen.this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        ///

        skip.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                intromanager.setIntroShow(false);
                startActivity(new Intent("com.adilaytan.medicaldictionarypro.MAINACTIVITY"));
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                    int current = getItem(+1);
                    if (current <  layouts.length)
                    {
                        ActiveElements();
                        viewPager.setCurrentItem(current);
                    }
                    else if(current == layouts.length)
                    {
                        viewPager.setCurrentItem(current);
                        intromanager.setIntroShow(false);
                        startActivity(new Intent("com.adilaytan.medicaldictionarypro.MAINACTIVITY"));
                    }
                //atla yı pasif yap
                // ileri git pasif
                // dots pasif
            }
        });



        setDots(getItem(0));

    }

    private int getItem(int i)
    {
        return viewPager.getCurrentItem() + i;
    }



    ViewPager.OnPageChangeListener viewpagelisten = new ViewPager.OnPageChangeListener() {
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }



        public void onPageSelected(int position) {
            setDots(getItem(0));
            if (position == (layouts.length - 1))
            {
                PassiveElements();
            }
            else
            {
                ActiveElements();
            }
        }
        public void onPageScrollStateChanged(int state) {

        }
    };
    int[] dots_id = {R.id.dots_1,R.id.dots_2,R.id.dots_3};

     private void setDots(int dot)
    {
        for(int i = 0; i< dots_id.length ; i++)
        {
            ImageView img = (ImageView) findViewById(dots_id[i]);
            if (i != dot)
            {
                img.setBackground(getResources().getDrawable(R.drawable.shape_passive));
            }
            else
            {
                img.setBackground(getResources().getDrawable(R.drawable.shape_active));
            }
        }
    }

    private void dotsState(boolean state)
    {
        for(int i = 0; i< dots_id.length ; i++)
        {
            ImageView img = (ImageView) findViewById(dots_id[i]);
            if (state == true)
            {
                img.setVisibility(View.VISIBLE);
            }
            else if (state == false)
            {
                img.setVisibility(View.INVISIBLE);
            }
        }

    }


    private void ActiveElements()
    {
        next.setText(getResources().getString(R.string.intro_next));
    }

    private void PassiveElements()
    {
        next.setText(getResources().getString(R.string.intro_proceed));
    }

}
