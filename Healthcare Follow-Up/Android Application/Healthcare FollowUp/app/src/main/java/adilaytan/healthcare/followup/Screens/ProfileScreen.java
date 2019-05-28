package adilaytan.healthcare.followup.Screens;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import adilaytan.healthcare.followup.JSONEncoder;
import adilaytan.healthcare.followup.R;
import adilaytan.healthcare.followup.Structure.LastInfo;


public class ProfileScreen extends Fragment {

    JSONEncoder js;

    public ProfileScreen()
    {
        js = new JSONEncoder();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String get = getArguments().getString("data");
        String get2 = getArguments().getString("data2");
        View v = (View) inflater.inflate(R.layout.profile_fraq, container, false);

        TextView pv = (TextView) v.findViewById(R.id.value_pulse);
        TextView po = (TextView) v.findViewById(R.id.value_spo2);
        TextView date_pu = (TextView) v.findViewById(R.id.pulse_date);
        TextView date_po = (TextView) v.findViewById(R.id.spo2_date);
        TextView date_ecg = (TextView) v.findViewById(R.id.ecg_date);
        LastInfo li = js.getLast(get.toString());
        pv.setText(li.getPulse());
        po.setText(li.getSpo2());
        date_ecg.setText(li.getDate());
        date_po.setText(li.getDate());
        date_pu.setText(li.getDate());
        return v;
    }
}
