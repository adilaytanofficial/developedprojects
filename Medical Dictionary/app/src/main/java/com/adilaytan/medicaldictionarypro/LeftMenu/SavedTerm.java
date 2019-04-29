package com.adilaytan.medicaldictionarypro.LeftMenu;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.adilaytan.medicaldictionarypro.Classes.Class;
import com.adilaytan.medicaldictionarypro.DBHelpers.DatabaseHelper;
import com.adilaytan.medicaldictionarypro.LeftAdapters.SavedListAdapter;
import com.adilaytan.medicaldictionarypro.R;

import java.util.ArrayList;


public class SavedTerm extends AppCompatActivity implements ListView.OnItemClickListener {

    public static ArrayList list;
    public static boolean durum;
    DatabaseHelper db;
    Class Sınıf;
    public static SavedListAdapter adapter;
    public  static ListView listView1;
    public RelativeLayout saved_404,saved_list;
    private RelativeLayout ana_layout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Sınıf = new Class();
        Sınıf.setProgramTheme(this);
        Sınıf.SetLanguage(this);

        getSupportActionBar().setTitle(getResources().getString(R.string.saved_terms));
        setContentView(R.layout.saved_terms);


        saved_404 = (RelativeLayout) findViewById(R.id.saved_404);
        saved_list = (RelativeLayout) findViewById(R.id.saved_list_layout);
        ana_layout = (RelativeLayout) findViewById(R.id.saved_ana_layout);
        db = new DatabaseHelper(this);

        list = new ArrayList();
        db.Listeleme("kaydedilenler",list,0);



        if (savedInstanceState != null)
        {
            Sınıf.SetLanguage(this);
            getSupportActionBar().setTitle(getResources().getString(R.string.saved_terms));
        }

        if (list.size() > 0)
        {
            LayoutShow(true);
            durum = true;
            listView1 = (ListView) findViewById(R.id.saved_list);
            adapter = new SavedListAdapter(this,list);
            listView1.setAdapter(adapter);
            listView1.setOnItemClickListener(this);
        }
        else
        {
            LayoutShow(false);
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        DatabaseHelper mydbhelper = new DatabaseHelper(SavedTerm.this);
        String metin = list.get(i).toString();
        String aciklama = mydbhelper.AciklamaGetir(metin.replaceAll("'","''")).toString();
        Intent cagir = new Intent("com.adilaytan.medicaldictionarypro.termsprocess.TERMDESCRIPTION");
        cagir.putExtra("terimadi",metin.toString());
        cagir.putExtra("terimaciklama",aciklama.toString());
        startActivityForResult(cagir,1);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.delete_all)
        {
            ArrayList silincek = new ArrayList();
            silincek = db.ListeDondur("kaydedilenler",silincek,0);
            db.CokluSilme("terim","Terim_ad",silincek);
            db.TabloSifirla("kaydedilenler");
            Sınıf.SnackMessage(getResources().getString(R.string.deleted_all),ana_layout);
            LayoutShow(false);
            item.setVisible(false);
        }

        return super.onOptionsItemSelected(item);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        if (durum  == true)
        {
            getMenuInflater().inflate(R.menu.delete_all, menu);

            return true;
        }

        return false;
    }

    public void LayoutShow(boolean state)
    {
        if (state == true)
        {
            saved_list.setVisibility(View.VISIBLE);
            saved_404.setVisibility(View.INVISIBLE);
        }
        else
        {
            saved_list.setVisibility(View.INVISIBLE);
            saved_404.setVisibility(View.VISIBLE);
            durum = false;
        }
    }

}
