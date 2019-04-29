package com.adilaytan.medicaldictionarypro.SubRecycler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.adilaytan.medicaldictionarypro.DBHelpers.SubDatabaseHelper;
import com.adilaytan.medicaldictionarypro.Classes.Class;
import com.adilaytan.medicaldictionarypro.R;

import java.util.List;


public class SubActivity extends AppCompatActivity implements RecylerAdapter.ItemClickCallBack {

    private RecyclerView recyclerView;
    private RecylerAdapter recylerAdapter;
    private List<SubtermItem> listdata;

    SubDatabaseHelper dbhelper;
    Class Sınıf;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Sınıf = new Class();
        Sınıf.setProgramTheme(this);

        dbhelper = new SubDatabaseHelper(this);

        setContentView(R.layout.sub_activity);

        Intent intent = getIntent();
        String main_Term = intent.getExtras().getString("main_Term");


        if (savedInstanceState != null)
        {
            Sınıf.SetLanguage(this);
            getSupportActionBar().setTitle(main_Term + " " + getResources().getString(R.string.sub_activity_title));
        }

        getSupportActionBar().setTitle(main_Term + " " + getResources().getString(R.string.sub_activity_title));

        listdata = dbhelper.getSubItems(main_Term.toString());


        recyclerView = (RecyclerView) findViewById(R.id.sub_rec_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recylerAdapter = new RecylerAdapter(listdata);

        recyclerView.setAdapter(recylerAdapter);

        recylerAdapter.setItemClickCallback(this);


    }
}
