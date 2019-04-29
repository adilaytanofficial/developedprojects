package com.adilaytan.medicaldictionarypro.SliderMenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.adilaytan.medicaldictionarypro.Adapters.MainTermAdapter;
import com.adilaytan.medicaldictionarypro.DBHelpers.DatabaseHelper;
import com.adilaytan.medicaldictionarypro.MainActivity;
import com.adilaytan.medicaldictionarypro.R;

public class MainWords extends Fragment {

    public String aciklama = "";
    private View view;

    public MainWords()
    {

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    MainTermAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_words,container,false);

        final ListView listView1 = (ListView) view.findViewById(R.id.listView);

        if (listView1.getAdapter() == null)
        {
            adapter = ((MainActivity) getActivity()).mainTermAdapter;
            listView1.setAdapter(adapter);
        }

        String query = ((MainActivity) getActivity()).araniyor.toString();

        if (query.equals("") || query.isEmpty() || query.length() == 0)
        {
           adapter.filterData("");
        }
        else if (query.length() > 0)
        {
            adapter.filterData(query);
        }

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String metin = listView1.getItemAtPosition(i).toString();
                String cagir_Metin = metin.replaceAll("'","''");
                DatabaseHelper mydbhelper = new DatabaseHelper(getContext());
                boolean durum = mydbhelper.KayitVarmi(cagir_Metin,"sonaranan","sonaranan");
                if (durum == false)
                {
                    mydbhelper.DigerKayitEkleme(metin,"sonaranan","sonaranan");
                }
                aciklama = mydbhelper.AciklamaGetir(cagir_Metin).toString();
                Intent cagir = new Intent("com.adilaytan.medicaldictionarypro.termsprocess.TERMDESCRIPTION");
                cagir.putExtra("terimadi",metin.toString());
                cagir.putExtra("terimaciklama",aciklama.toString());
                startActivityForResult(cagir,1);
            }
        });
        return view;
    }
}
