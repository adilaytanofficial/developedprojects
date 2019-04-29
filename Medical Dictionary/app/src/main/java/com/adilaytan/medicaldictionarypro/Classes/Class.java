package com.adilaytan.medicaldictionarypro.Classes;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.adilaytan.medicaldictionarypro.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

public class Class extends Activity {

    SharedPreferences.Editor editor;

    public void SnackMessage(String mesaj,View v)
    {
        int color = getColorPrimary(v.getContext());
        Snackbar bar =  Snackbar.make(v,mesaj,Snackbar.LENGTH_SHORT);
        bar.getView().setBackgroundColor(color);
        TextView metin = (TextView) bar.getView().findViewById(android.support.design.R.id.snackbar_text);
        metin.setTextColor(Color.WHITE);
        bar.show();
    }

    public String getThemeName(Context ctx)
    {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(ctx);
        String ThemeId = settings.getString("theme", ""); //Varsayılan Theme
        return ThemeId;
    }

    public String getLanguage(Context ctx)
    {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(ctx);
        String ThemeId = settings.getString("dil", ""); //Varsayılan Theme
        return ThemeId;
    }
    public void setProgramThemeNoAction(Context ctx)
    {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(ctx);
        String ThemeId = settings.getString("theme", "2131361933"); //Varsayılan Theme

        if (ThemeId.equals("2131361933"))
        {
            ctx.setTheme(R.style.MaviTema_NoActionBar);
        }
        else if (ThemeId.equals("2131362063"))
        {
            ctx.setTheme(R.style.YesilTema_NoActionBar);
        }
        else if (ThemeId.equals("2131361981"))
        {
            ctx.setTheme(R.style.SiyahTema_NoActionBar);
        }
        else if (ThemeId.equals("2131362065"))
        {
            ctx.setTheme(R.style.KirmiziTema_NoActionBar);
        }
        else if (ThemeId.equals("2131361983"))
        {
            ctx.setTheme(R.style.PembeTema_NoActionBar);
        }
    }
    public void setProgramTheme(Context ctx) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(ctx);
        String ThemeId = settings.getString("theme", "2131361933"); //Varsayılan Theme

        if (ThemeId.equals("2131361933"))
        {
            ctx.setTheme(R.style.MaviTema);
        }
        else if (ThemeId.equals("2131362063"))
        {
            ctx.setTheme(R.style.YesilTema);
        }
        else if (ThemeId.equals("2131361981"))
        {
            ctx.setTheme(R.style.SiyahTema);
        }
        else if (ThemeId.equals("2131362065"))
        {
            ctx.setTheme(R.style.KirmiziTema);
        }
        else if (ThemeId.equals("2131361983"))
        {
            ctx.setTheme(R.style.PembeTema);
        }
    }

    public void setLangRecreate(String langval) {
        Configuration config = getBaseContext().getResources().getConfiguration();
        Locale locale = new Locale(langval);
        Locale.setDefault(locale);
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        recreate();
    }


    private void SetLanTurkish()
    {
        Locale locale = new Locale("");  //locale en yaptık. Artık değişkenler values-en paketinden alınacak
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        finish();//mevcut acivity i bitir.
        startActivity(getIntent());//activity i baştan yükle
        //Toast.makeText(getApplicationContext(), R.string.lan_set_default, Toast.LENGTH_LONG).show();
    }

    public int getColorPrimary(Context ctx){
        TypedValue typedValue = new TypedValue();
        TypedArray typedArray = ctx.obtainStyledAttributes(typedValue.data,new int[] {R.attr.colorPrimary});
        int color = typedArray.getColor(0,0);
        typedArray.recycle();
        return color;
    }


    public void RefreshLanguage(Context ctx,String value)
    {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(ctx);
        editor = settings.edit();

        Configuration config = ctx.getResources().getConfiguration();

        editor.putString("dil",value);
        editor.commit();
        ctx.getResources().updateConfiguration(config,ctx.getResources().getDisplayMetrics());
        }

    public void RefreshTheme(Context ctx,String value)
    {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(ctx);
        editor = settings.edit();
        editor.putString("theme",value);
        editor.commit();
    }

    public void makeFullScreen(Activity activity)
    {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void SetLanguage(Context ctx)
    {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(ctx);
        editor = settings.edit();

        Configuration config = ctx.getResources().getConfiguration();
        String lang = settings.getString("dil", "");

        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        config.locale = locale;

        ctx.getResources().updateConfiguration(config,ctx.getResources().getDisplayMetrics());
    }

    public void ResetPREF(Context context)
    {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        editor = settings.edit();
        editor.putString("theme","131361933");
        editor.putString("dil","tr");
        editor.commit();
    }

    public boolean PrefControl(Context context)
    {
        boolean durum =  false;
        boolean theme_state= false;
        boolean lan_state= false;

        String theme = getThemeName(context);

        String lang = getLanguage(context);

        String[] lanq = {"en","tr"};
        String[] themes = {"2131361933","2131362063","2131361981","2131362065","2131361983"};

        for (String str : lanq)
        {
            if (!str.equals(lang)){
                lan_state = false;
            }
            else
            {
                lan_state = true;
            }

        }

        for (String str : themes)
        {
            if (!str.equals(theme))
            {
                theme_state = false;
            }
            else
            {
                theme_state = true;
            }
        }
        if (theme_state == true  && lan_state == true)
        {
            durum = false;
        }
        else
        {
            durum = false;
        }

        return durum;
    }
    public boolean FileControl(Context context)
    {
        boolean durum = false;

        String name_package = "com.med.medicaldictionary";

        String database_file = "/data/data/" + name_package + "/databases/veritabani";
        String sub_path = "/data/data/" + name_package + "/databases/subterms";
        File folder = new File("/data/data/" + name_package + "/databases/");
        File database = new File(database_file);
        File sub_term = new File(sub_path);

        if (database.exists() && sub_term.exists())
        {
            durum = true;
            setFileControl(true,context);
        }
        else if (!database.exists() || !sub_term.exists())
        {
            durum = false;
           setFileControl(false,context);
            DbINSTALL("both",context);
        }

        return durum;
    }

    public void setFileControl(boolean state,Context context)
    {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        editor = settings.edit();
        editor.putBoolean("db_installed",state);
        editor.commit();
    }

    public void DbINSTALL(String state, Context context)
    {
        try {
            if (state.equals("both"))
            {
                FileWRITE("subterms",context);
                FileWRITE("veritabani",context);
                setFileControl(true,context);
            }
            else if (state.equals("veritabani"))
            {
                FileWRITE("veritabani",context);
                setFileControl(true,context);
            }
            else if (state.equals("subterms"))
            {
                FileWRITE("subterms",context);
                setFileControl(true,context);
            }

        }
        catch (Exception e)
        {
            e.toString();
        }
    }

    public void FileWRITE(String dbname,Context context)
    {
        String name_package = "com.med.medicaldictionary";
        File dbfolder = new File("/data/data/" + context.getPackageName() + "/databases/");
        String dbfile_folder = "/data/data/" + context.getPackageName() + "/databases/" + dbname;
        File dbfile = new File(dbfile_folder);
        try
        {
            dbfolder.mkdir();
            InputStream in = context.getAssets().open(dbname);
            dbfile.createNewFile();
            OutputStream out = new FileOutputStream(dbfile);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            out.flush();
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
