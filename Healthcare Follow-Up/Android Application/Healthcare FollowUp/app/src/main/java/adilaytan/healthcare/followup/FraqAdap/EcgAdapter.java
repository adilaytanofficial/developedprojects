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

public class EcgAdapter extends ArrayAdapter {


    ArrayList<Parameter> list;
    Context context;

    public EcgAdapter(Activity context, ArrayList<Parameter> listValue) {
        super(context, R.layout.ecg_item, listValue);
        this.list = listValue;
        this.context = context;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRow = layoutInflater.inflate(R.layout.ecg_item, null, true);
        TextView ecg_date = (TextView) viewRow.findViewById(R.id.ecg_date);
        // value
        ecg_date.setText(list.get(i).getDate());
        //pu_value.setText(list.get(i).getValue());
        return viewRow;
    }
}
