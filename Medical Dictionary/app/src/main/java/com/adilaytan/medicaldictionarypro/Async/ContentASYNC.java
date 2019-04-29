package com.adilaytan.medicaldictionarypro.Async;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.adilaytan.medicaldictionarypro.DBHelpers.DatabaseHelper;
import com.adilaytan.medicaldictionarypro.R;

import java.util.ArrayList;


public class ContentASYNC extends AsyncTask<String,String,String> {

    Context context;
    ArrayAdapter adapter;

    DatabaseHelper mydbhelper;
    ArrayList liste;

    ListView listView;

    Activity activity;

    private Cursor cursor;

    private RelativeLayout main_content,content_404,not_search,loading;


    public ContentASYNC(Context ctx)
    {
        this.context = ctx;
        this.activity = (Activity) ctx;
    }

    protected void onPreExecute()
    {
        mydbhelper = new DatabaseHelper(context);
        liste = new ArrayList();
        ContentUpdate("loading");
    }

    protected String doInBackground(String... params)
    {
        String durum = "";
        String method = params[0];

        if (method.equals("search"))
        {
            String query = params[1].toString();

            if (query.length() > 0) {
                liste.clear();
                liste = mydbhelper.ContentSearch(query);
                if (liste.size() > 0) {
                    durum = "found";
                } else if (liste.size() <= 0) {
                    durum = "not_found";
                }
            }
            else
            {
                durum = "not_yet";
            }
        }
        else if(method.equals("not_yet"))
        {
            durum = "not_yet";
        }

        return durum;
    }

    protected void onPostExecute(String out)
    {
        ContentUpdate(out);
    }

    public void ContentUpdate(String state)
    {
        main_content = (RelativeLayout) activity.findViewById(R.id.main_content);
        content_404 = (RelativeLayout) activity.findViewById(R.id.content_404_found);
        not_search = (RelativeLayout) activity.findViewById(R.id.content_nott_search);
        loading = (RelativeLayout) activity.findViewById(R.id.content_load);
        ListView listView = (ListView) activity.findViewById(R.id.content_list);

        if (main_content != null && content_404 != null && not_search != null && loading != null)
        {
            if (state.equals("found")) {
                loading.setVisibility(View.INVISIBLE);
                main_content.setVisibility(View.VISIBLE);
                content_404.setVisibility(View.INVISIBLE);
                not_search.setVisibility(View.INVISIBLE);
                adapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,android.R.id.text1,liste);
                listView.setAdapter(adapter);
            }
            else if(state.equals("not_found"))
            {
                loading.setVisibility(View.INVISIBLE);
                content_404.setVisibility(View.VISIBLE);
                main_content.setVisibility(View.INVISIBLE);
                not_search.setVisibility(View.INVISIBLE);
            }
            else if (state.equals("not_yet"))
            {
                loading.setVisibility(View.INVISIBLE);
                not_search.setVisibility(View.VISIBLE);
                content_404.setVisibility(View.INVISIBLE);
                main_content.setVisibility(View.INVISIBLE);
            }
            else if (state.equals("loading"))
            {
                loading.setVisibility(View.VISIBLE);
                not_search.setVisibility(View.INVISIBLE);
                content_404.setVisibility(View.INVISIBLE);
                main_content.setVisibility(View.INVISIBLE);
            }
        }

    }


}
