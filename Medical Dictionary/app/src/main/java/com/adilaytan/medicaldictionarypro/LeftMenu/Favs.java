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
import com.adilaytan.medicaldictionarypro.LeftAdapters.FavListAdapter;
import com.adilaytan.medicaldictionarypro.R;
import java.util.ArrayList;


public class Favs extends AppCompatActivity implements ListView.OnItemClickListener {

    public static ArrayList list;
    public static boolean durum;

    Class Sınıf;
    DatabaseHelper db;
    public static FavListAdapter favListAdapter;
    public static ListView listView1;
    public RelativeLayout fav_404,fav_list;
    private RelativeLayout ana_layout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Sınıf = new Class();
        Sınıf.setProgramTheme(Favs.this);

        getSupportActionBar().setTitle(getResources().getString(R.string.favourites_term));
        setContentView(R.layout.favourites);

        fav_404 = (RelativeLayout) findViewById(R.id.fav_404_layout);
        fav_list = (RelativeLayout) findViewById(R.id.fav_list_layout);
        ana_layout = (RelativeLayout) findViewById(R.id.fav_layout);

        db = new DatabaseHelper(this);
        list = new ArrayList();
        db.Listeleme("favoriler", list, 0);

        if (savedInstanceState != null)
        {
            Sınıf.SetLanguage(this);
            getSupportActionBar().setTitle(getResources().getString(R.string.favourites_term));
        }

        if (list.size() > 0)
        {
            LayoutShow(true);
            durum  = true;
            listView1 = (ListView) findViewById(R.id.fav_listview);
            favListAdapter = new FavListAdapter(this,list);
            listView1.setAdapter(favListAdapter);
            listView1.setOnItemClickListener(this);
        }
        else
        {
            LayoutShow(false);
        }

    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        DatabaseHelper mydbhelper = new DatabaseHelper(Favs.this);
        String metin = list.get(i).toString();
        String query = list.get(i).toString().replace("'","''");
        String aciklama = mydbhelper.AciklamaGetir(query).toString();
        Intent cagir = new Intent("com.adilaytan.medicaldictionarypro.termsprocess.TERMDESCRIPTION");
        cagir.putExtra("terimadi", metin.toString());
        cagir.putExtra("terimaciklama", aciklama.toString());
        startActivityForResult(cagir, 1);
    }

    public void LayoutShow(boolean state)
    {
        if (state == true)
        {
            fav_list.setVisibility(View.VISIBLE);
            fav_404.setVisibility(View.INVISIBLE);
        }
        else
        {
            fav_list.setVisibility(View.INVISIBLE);
            fav_404.setVisibility(View.VISIBLE);
            durum = false;
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.delete_all)
        {
            db.TabloSifirla("favoriler");
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
        else
        {

        }

        return false;
    }
}
