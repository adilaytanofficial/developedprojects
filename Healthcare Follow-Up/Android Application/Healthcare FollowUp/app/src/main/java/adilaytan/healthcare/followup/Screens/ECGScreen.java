package adilaytan.healthcare.followup.Screens;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import adilaytan.healthcare.followup.FraqAdap.EcgAdapter;
import adilaytan.healthcare.followup.JSONEncoder;
import adilaytan.healthcare.followup.R;
import adilaytan.healthcare.followup.Structure.Parameter;
import adilaytan.healthcare.followup.Task.ECGProcess;


public class ECGScreen extends Fragment implements ListView.OnItemClickListener,SwipeRefreshLayout.OnRefreshListener {


    ListView lw;
    EcgAdapter ea;
    ArrayList<Parameter> aq;
    JSONEncoder js;
    SwipeRefreshLayout refresher;

    public ECGScreen(){  js =new JSONEncoder(); }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String get = getArguments().getString("data");
        View v = (View) inflater.inflate(R.layout.pulse_oximeter_layout, container, false);
        lw = (ListView) v.findViewById(R.id.spo2_exp);
        refresher = (SwipeRefreshLayout) v.findViewById(R.id.sp_layout);
        refresher.setOnRefreshListener(this);
        aq = new ArrayList<>();
        //Toast.makeText(getActivity(),get,Toast.LENGTH_SHORT).show();
        aq = js.GetECG(get);
        ea = new EcgAdapter(getActivity(),aq);
        lw.setAdapter(ea);
        lw.setOnItemClickListener(this);
        return v;
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Parameter param = (Parameter) aq.get(position);
        Intent i = new Intent("adilaytan.healthcare.followup.GRAPHDATA");
        i.putExtra("ecgdata",param.getValue());
        i.putExtra("date",param.getDate());
        startActivity(i);
    }

    public void onRefresh() {
        new ECGProcess(getActivity(),"47",lw,refresher).execute();
    }
}
