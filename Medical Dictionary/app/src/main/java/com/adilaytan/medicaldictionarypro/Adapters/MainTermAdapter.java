package com.adilaytan.medicaldictionarypro.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adilaytan.medicaldictionarypro.R;

import java.util.ArrayList;

public class MainTermAdapter extends ArrayAdapter {

    ArrayList originalList;
    ArrayList filtered_list;
    Context context;
    Activity activity;
    private RelativeLayout main_words_layout,main_404;

    public MainTermAdapter(Context context, ArrayList lists) {
        super(context, android.R.layout.simple_list_item_1,lists);
        this.filtered_list = new ArrayList();
        this.filtered_list.addAll(lists);
        this.originalList = new ArrayList();
        this.originalList.addAll(lists);
        this.context = context;
        this.activity = (Activity) context;
    }

    public int getCount() {
        return filtered_list.size();
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRow = layoutInflater.inflate(android.R.layout.simple_list_item_1, null, true);

        TextView text = (TextView) viewRow.findViewById(android.R.id.text1);
        text.setText(filtered_list.get(i).toString());

        return viewRow;
    }

    public Object getItem(int position) {
        return filtered_list.get(position);
    }

    public void filterData(String query)
    {
        filtered_list.clear();
        if (query.length() == 0)
        {
            UpdateView(true);
            filtered_list.addAll(originalList);
        }
        else
        {
            for (Object string : originalList)
            {
                if (string.toString().startsWith(query))
                {
                    filtered_list.add(string);
                }
            }
            if (filtered_list.size() > 0)
            {
                UpdateView(true);
            }
            else if (filtered_list.size() <= 0)
            {
                UpdateView(false);
            }
        }
        notifyDataSetChanged();
    }

    private void UpdateView(boolean state)
    {
        main_words_layout = (RelativeLayout) activity.findViewById(R.id.main_words_full);
        main_404 = (RelativeLayout) activity.findViewById(R.id.main_404_layout);
        if (main_404 != null && main_words_layout != null)
        {
            if (state == true)
            {
                main_words_layout.setVisibility(View.VISIBLE);
                main_404.setVisibility(View.INVISIBLE);
            }
            else if(state == false)
            {
                main_404.setVisibility(View.VISIBLE);
                main_words_layout.setVisibility(View.INVISIBLE);
            }
        }

    }

}