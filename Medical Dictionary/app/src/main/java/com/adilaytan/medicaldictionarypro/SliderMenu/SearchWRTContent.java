package com.adilaytan.medicaldictionarypro.SliderMenu;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.adilaytan.medicaldictionarypro.Async.ContentASYNC;
import com.adilaytan.medicaldictionarypro.DBHelpers.DatabaseHelper;
import com.adilaytan.medicaldictionarypro.MainActivity;
import com.adilaytan.medicaldictionarypro.R;

public class SearchWRTContent extends Fragment {


    public String aciklama = "";
    private View view;

    DatabaseHelper db;
    ContentASYNC contentASYNC;

    public SearchWRTContent() {

    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.content_main, container, false);

        final ListView content_list = (ListView) view.findViewById(R.id.content_list);

        db = new DatabaseHelper(getContext());


        String query = ((MainActivity) getActivity()).araniyor.toString();


        if (query.equals("") || query.isEmpty() || query.length() == 0)
        {
            contentASYNC = new ContentASYNC(getContext());
            contentASYNC.execute("search",query);
        }
        else if (query.length() > 0)
        {
            contentASYNC = new ContentASYNC(getContext());
            contentASYNC.execute("search",query);
        }

        content_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String metin = content_list.getItemAtPosition(i).toString();
                aciklama = db.AciklamaGetir(metin.toString().replaceAll("'", "''"));
                Intent cagir = new Intent("com.adilaytan.medicaldictionarypro.termsprocess.TERMDESCRIPTION");
                cagir.putExtra("terimadi", metin.toString());
                cagir.putExtra("terimaciklama", aciklama.toString());
                startActivity(cagir);
            }
        });

        return view;
    }

}
