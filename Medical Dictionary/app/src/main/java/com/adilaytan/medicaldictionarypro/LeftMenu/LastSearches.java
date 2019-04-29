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
import com.adilaytan.medicaldictionarypro.LeftAdapters.LastListAdapter;
import com.adilaytan.medicaldictionarypro.R;

import java.util.ArrayList;

public class LastSearches extends AppCompatActivity implements ListView.OnItemClickListener {


    public static ArrayList list;
    public static boolean durum;
    Class Sınıf;
    DatabaseHelper db;
    public static LastListAdapter adapter;
    public static ListView listView1;
    public RelativeLayout last_404,last_list;
    private RelativeLayout ana_layout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Sınıf = new Class();
        Sınıf.setProgramTheme(this);

        getSupportActionBar().setTitle(getResources().getString(R.string.last_searchs));
        setContentView(R.layout.last_searches);

        last_404 = (RelativeLayout) findViewById(R.id.last_search_404);
        last_list = (RelativeLayout) findViewById(R.id.last_list_layout);
        ana_layout = (RelativeLayout) findViewById(R.id.last_ana_layout);

        db = new DatabaseHelper(this);
        list = new ArrayList();
        db.Listeleme("sonaranan",list,0);


        if (savedInstanceState != null)
        {
            Sınıf.SetLanguage(this);
            getSupportActionBar().setTitle(getResources().getString(R.string.last_searchs));
        }

        if (list.size() > 0)
        {
            LayoutShow(true);
            durum = true;
            listView1 = (ListView) findViewById(R.id.sonaranan_listview);
            adapter = new LastListAdapter(this,list);
            listView1.setAdapter(adapter);
            listView1.setOnItemClickListener(this);
        }
        else
        {
            LayoutShow(false);
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        DatabaseHelper mydbhelper = new DatabaseHelper(LastSearches.this);
        String metin = list.get(i).toString();
        String not_404 = getResources().getString(R.string.finding_term);
        String query = list.get(i).toString().replace("'","''");
        boolean durum =  mydbhelper.KayitVarmi(query,"terim","Terim_ad");
        if (durum == true)
        {
            String aciklama = mydbhelper.AciklamaGetir(query).toString();
            Intent cagir = new Intent("com.adilaytan.medicaldictionary.termsprocess.TERMDESCRIPTION");
            cagir.putExtra("terimadi", metin.toString());
            cagir.putExtra("terimaciklama", aciklama.toString());
            startActivityForResult(cagir,1);
        }
        else if (durum == false)
        {
            Sınıf.SnackMessage(not_404,view);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.delete_all)
        {
            db.TabloSifirla("sonaranan");
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
            last_list.setVisibility(View.VISIBLE);
            last_404.setVisibility(View.INVISIBLE);
        }
        else
        {
            last_list.setVisibility(View.INVISIBLE);
            last_404.setVisibility(View.VISIBLE);
            durum = false;
        }
    }

}