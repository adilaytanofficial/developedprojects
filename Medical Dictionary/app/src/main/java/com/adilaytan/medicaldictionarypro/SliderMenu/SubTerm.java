package com.adilaytan.medicaldictionarypro.SliderMenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.adilaytan.medicaldictionarypro.Adapters.SubListAdapter;
import com.adilaytan.medicaldictionarypro.MainActivity;
import com.adilaytan.medicaldictionarypro.R;


public class SubTerm extends Fragment {

    SubListAdapter subAdapter;

    public SubTerm()
    {

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sub_term,container,false);

        final ListView parent_listView = (ListView) view.findViewById(R.id.sub_parent_listview);
        parent_listView.setFastScrollEnabled(true);

        String query = ((MainActivity) getActivity()).araniyor.toString();

        if (parent_listView.getAdapter() == null)
        {
            subAdapter = ((MainActivity) getActivity()).subAdapter;
            parent_listView.setAdapter(subAdapter);
        }

        if (query.equals("") || query.isEmpty() || query.length() == 0)
        {
            subAdapter.filterData("");
        }
        else if (query.length() > 0)
        {
            subAdapter.filterData(query);
        }

        parent_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView text = (TextView) view.findViewById(R.id.sub_term_name);
                String main_Term = text.getText().toString();
                Intent yolla = new Intent("com.adilaytan.medicaldictionarypro.subrecycler.SUBACTIVITY");
                yolla.putExtra("main_Term",main_Term);
                startActivity(yolla);
            }
        });

        return view;
    }


}