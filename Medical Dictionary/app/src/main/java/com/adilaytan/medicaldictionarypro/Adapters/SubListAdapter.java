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

public class SubListAdapter extends ArrayAdapter {

    ArrayList original_list;
    ArrayList filtered_list;
    
    Context context;
    Activity activity;
    
    private RelativeLayout sub_404_layout,sub_main_layout;
    
    public SubListAdapter(Activity context, ArrayList parent_list) {
        super(context, R.layout.fav_list_item, parent_list);
        this.original_list = new ArrayList();
        this.original_list = parent_list;
        this.filtered_list = new ArrayList();
        this.filtered_list.addAll(parent_list);
        this.context = context;
        this.activity = (Activity) context;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRow = layoutInflater.inflate(R.layout.sub_term_parent, null, true);
        final TextView sub_parent = (TextView) viewRow.findViewById(R.id.sub_term_name);
        sub_parent.setText(filtered_list.get(i).toString());

        return viewRow;
    }

    public int getCount() {
        return filtered_list.size();
    }

    private void UpdateView(boolean state)
    {
        sub_main_layout = (RelativeLayout) activity.findViewById(R.id.sub_term_main);
        sub_404_layout = (RelativeLayout) activity.findViewById(R.id.subterm_404_layout);
        if (sub_main_layout != null && sub_404_layout != null)
        {
            if (state == true)
            {
                sub_main_layout.setVisibility(View.VISIBLE);
                sub_404_layout.setVisibility(View.INVISIBLE);
            }
            else if(state == false)
            {
                sub_404_layout.setVisibility(View.VISIBLE);
                sub_main_layout.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void filterData(String query)
    {
        filtered_list.clear();
        if (query.length() == 0)
        {
            UpdateView(true);
            filtered_list.addAll(original_list);
        }
        else
        {
            for (Object str : original_list)
            {
                if (str.toString().startsWith(query))
                {
                    filtered_list.add(str);
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
    
}