package com.adilaytan.medicaldictionarypro.Settings;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.adilaytan.medicaldictionarypro.Classes.Class;
import com.adilaytan.medicaldictionarypro.DBHelpers.DatabaseHelper;
import com.adilaytan.medicaldictionarypro.MainActivity;
import com.adilaytan.medicaldictionarypro.R;

import java.util.ArrayList;

public class SettingsScreen extends AppCompatPreferenceActivity {


    private Class yeni;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle(getResources().getString(R.string.settings).toString());

        addPreferencesFromResource(R.xml.settings);

        yeni = new Class();

        Preference dil = findPreference("dil");
        dil.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object o) {
                String lang  = o.toString();
                yeni.RefreshLanguage(SettingsScreen.this,o.toString());
                finish();
                startActivity(getIntent());
                if (lang.equals("en"))
                {
                    Toast.makeText(getApplicationContext(),"Default language is set to English",Toast.LENGTH_SHORT).show();
                }
                else if (lang.equals("tr"))
                {
                    Toast.makeText(getApplicationContext(),"Varsayılan dil Türkçe olarak ayarlandı",Toast.LENGTH_SHORT).show();
                }
               return false;
            }
        });


        Preference tema = findPreference("theme");

        tema.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object o) {
                yeni.RefreshTheme(SettingsScreen.this,o.toString());
                finish();
                startActivity(getIntent());
                Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.changing_theme).toString(),Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        Preference sifirla = findPreference("sifirla");
        sifirla.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsScreen.this);

                final String fav = getResources().getString(R.string.favourites_term);
                final String last_search = getResources().getString(R.string.last_searchs);
                final String saved = getResources().getString(R.string.saved_terms);


                final String[] deleted_var = new String[]{
                        fav,
                        last_search,
                        saved
                };

                final boolean[] checkedvar = new boolean[]{
                        false,
                        false,
                        false
                };


                builder.setMultiChoiceItems(deleted_var, checkedvar, new DialogInterface.OnMultiChoiceClickListener() {
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                    }
                });


                builder.setCancelable(false);
                builder.setTitle(getResources().getString(R.string.reset_database));

                final ArrayList liste = new ArrayList(3);

                final DatabaseHelper db = new DatabaseHelper(SettingsScreen.this);
                builder.setPositiveButton(getResources().getString(R.string.reset), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        if (checkedvar.length > 0)
                        {
                            int sayac = 0;

                            for (int i = 0; i<checkedvar.length; i++){
                                boolean checked = checkedvar[i];

                                if (i == 0 && checked) {
                                    db.TabloSifirla("favoriler");
                                    sayac++;
                                }
                                else if (i == 1 && checked) {
                                    db.TabloSifirla("sonaranan");
                                    sayac++;
                                }
                                else if (i == 2 && checked) {
                                    ArrayList silincek = new ArrayList();
                                    silincek = db.ListeDondur("kaydedilenler",silincek,0);
                                    db.CokluSilme("terim","Terim_ad",silincek);
                                    db.TabloSifirla("kaydedilenler");
                                    sayac++;
                                }

                            }
                            if (sayac != 0)
                            {
                                Toast.makeText(getApplicationContext(),getApplicationContext().getResources().getString(R.string.reseted_database),Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),getApplicationContext().getResources().getString(R.string.non_reseted_database),Toast.LENGTH_SHORT).show();
                            }
                        }


                    }
                });

                // Set the neutral/cancel button click listener
                builder.setNeutralButton(getApplicationContext().getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
                return false;
            }
        });

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}