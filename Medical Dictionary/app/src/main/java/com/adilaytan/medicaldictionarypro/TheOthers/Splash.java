package com.adilaytan.medicaldictionarypro.TheOthers;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Window;
import android.view.WindowManager;

import com.adilaytan.medicaldictionarypro.Classes.Class;
import com.adilaytan.medicaldictionarypro.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Splash extends Activity {

    SharedPreferences.Editor editor;
    Intent cagir;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fullscreen yapma methodları

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final Class Sınıf = new Class();

        Sınıf.setProgramThemeNoAction(this);
        Sınıf.SetLanguage(this);

        setContentView(R.layout.splash_screen);

        veritabanıolustur();

        if (savedInstanceState != null)
        {
            Sınıf.SetLanguage(this);
        }

        final boolean durum = getIntro();



        Thread zaman = new Thread() {
            public void run() {
                try {
                    sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (durum == false) {
                            cagir = new Intent("com.adilaytan.medicaldictionarypro.MAINACTIVITY");
                            startActivity(cagir);
                    }
                    else if (durum == true)
                    {
                        Def_set();
                        cagir = new Intent("com.adilaytan.medicaldictionarypro.intro.INTROSKIP");
                        startActivity(cagir);
                    }
                }
            }
        };

        zaman.start();

    }

    private boolean getIntro()
    {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean intro = settings.getBoolean("intro_show",true);
        return intro;
    }

    private void Def_set()
    {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        editor = settings.edit();
        editor.putString("theme","2131361933");
        editor.putString("dil","tr");
        editor.commit();
    }


    // dosya yükleme
    private void veritabanıolustur()
    {
        try {
            String dbPath = "/data/data/" + getPackageName() + "/databases/veritabani";
            String dbPath2 = "/data/data/" + getPackageName()  + "/databases/subterms";

            File folder = new File("/data/data/" + getPackageName() + "/databases/");
            File f = new File(dbPath);

            File sub_term = new File(dbPath2);

            if (!f.exists())
            {
                folder.mkdir();
                InputStream in = getAssets().open("veritabani");
                f.createNewFile();
                OutputStream out = new FileOutputStream(dbPath);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
                out.flush();
                out.close();
                in.close();
            }

            if (!sub_term.exists()) {
                folder.mkdir();
                InputStream in = getAssets().open("subterms");
                sub_term.createNewFile();
                OutputStream out = new FileOutputStream(dbPath2);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
                out.flush();
                out.close();
                in.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

