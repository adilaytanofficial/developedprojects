package adilaytan.healthcare.followup.Screens;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import adilaytan.healthcare.followup.FraqAdap.PulseAdapter;
import adilaytan.healthcare.followup.JSONEncoder;
import adilaytan.healthcare.followup.R;
import adilaytan.healthcare.followup.SOAPER;
import adilaytan.healthcare.followup.Structure.Parameter;
import adilaytan.healthcare.followup.Task.PulseProcess;

public class PulseScreen extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    PulseAdapter pa;
    ArrayList<Parameter> ap;
    ListView lw;
    JSONEncoder js;
    SwipeRefreshLayout refresher;
    SOAPER sp;

    public PulseScreen(){
        js = new JSONEncoder();
        sp = new SOAPER(getActivity());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String get = getArguments().getString("data");
        View v = (View) inflater.inflate(R.layout.pulse_oximeter_layout, container, false);
        lw = (ListView) v.findViewById(R.id.spo2_exp);
        refresher = (SwipeRefreshLayout) v.findViewById(R.id.sp_layout);
        refresher.setOnRefreshListener(this);
        ap = new ArrayList<>();
        ap = js.GetPulse(get);
        pa = new PulseAdapter(getActivity(),ap);
        lw.setAdapter(pa);
        return v;

        /*View v = (View) inflater.inflate(R.layout.pulse_oximeter_layout, container, false);
        lw = (ListView) v.findViewById(R.id.spo2_exp);
        ap = new ArrayList<>();
        Parameter param;
        for(int i=0;i<30;i++)
        {
            param = new Parameter();
            param.setValue("70");
            param.setDate("7.12.2018");
            ap.add(param);
        }
        pa = new PulseAdapter(getActivity(),ap);
        lw.setAdapter(pa);*/
    }


    public void onRefresh() {

        new PulseProcess(getActivity(),"47",lw,refresher).execute();
       /* new PulseProcess().execute();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                String qu = sp.allPULSE("47");
                ap = new ArrayList<>();
                ap = js.GetPulse(qu);
                pa = new PulseAdapter(getActivity(),ap);
                lw.setAdapter(pa);
                Toast.makeText(getActivity(), "Anana", Toast.LENGTH_SHORT).show();
            }
        }, 2500);*/



    }
}
