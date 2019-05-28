package adilaytan.healthcare.followup.Screens;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import adilaytan.healthcare.followup.FraqAdap.OxygenAdapter;
import adilaytan.healthcare.followup.JSONEncoder;
import adilaytan.healthcare.followup.R;
import adilaytan.healthcare.followup.Structure.Parameter;
import adilaytan.healthcare.followup.Task.OximeterProcess;

public class PulseOximeterScreen extends Fragment implements View.OnClickListener,SwipeRefreshLayout.OnRefreshListener {


    ListView lw;
    OxygenAdapter oa;
    ArrayList<Parameter> ap;
    JSONEncoder js;
    SwipeRefreshLayout refresher;

    public PulseOximeterScreen(){
        js =new JSONEncoder();
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String get = getArguments().getString("data");
        View v = (View) inflater.inflate(R.layout.pulse_oximeter_layout, container, false);
        lw = (ListView) v.findViewById(R.id.spo2_exp);
        refresher = (SwipeRefreshLayout) v.findViewById(R.id.sp_layout);
        refresher.setOnRefreshListener(this);
        ap = new ArrayList<>();
        ap = js.GetOxy(get);
        oa = new OxygenAdapter(getActivity(),ap);
        lw.setAdapter(oa);
        return v;


        /*String get = getArguments().getString("data");
        View v = (View) inflater.inflate(R.layout.pulse_oximeter_layout, container, false);
        lw = (ListView) v.findViewById(R.id.spo2_exp);
        ap = new ArrayList<>();
        ap = js.GetPulse(get);
        oa = new OxygenAdapter(getActivity(),ap);
        lw.setAdapter(oa);
        return v;*/

        /* View view = inflater.inflate(R.layout.pulse_oximeter_layout, container, false);
        aq = new ArrayList<>();
        Parameter param = new Parameter();
        param.setValue("70");
        param.setDate("7.12.2018");
        aq.add(param);
        param = new Parameter();
        param.setValue("80");
        param.setDate("8.12.2018");
        aq.add(param);
        oa = new OxygenAdapter(getActivity(),aq);
        exp = (ListView) view.findViewById(R.id.spo2_exp);
        exp.setAdapter(oa);*/
    }

    public void onClick(View v) {
        int id = v.getId();

    }

    public void onRefresh() {
        new OximeterProcess(getActivity(),"47",lw,refresher).execute();
    }
}
