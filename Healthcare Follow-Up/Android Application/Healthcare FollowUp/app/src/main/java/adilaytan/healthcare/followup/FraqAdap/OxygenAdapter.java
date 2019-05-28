package adilaytan.healthcare.followup.FraqAdap;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import adilaytan.healthcare.followup.R;
import adilaytan.healthcare.followup.Structure.Parameter;

public class OxygenAdapter extends ArrayAdapter {
    ArrayList<Parameter> list;
    Context context;

    public OxygenAdapter(Activity context, ArrayList<Parameter> listValue) {
        super(context, R.layout.po_item, listValue);
        this.list = listValue;
        this.context = context;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRow = layoutInflater.inflate(R.layout.po_item, null, true);
        TextView po_value = (TextView) viewRow.findViewById(R.id.po_value);
        TextView po_date = (TextView) viewRow.findViewById(R.id.po_date);
        po_date.setText(list.get(i).getDate());
        po_value.setText(list.get(i).getValue());
        return viewRow;
    }
}
