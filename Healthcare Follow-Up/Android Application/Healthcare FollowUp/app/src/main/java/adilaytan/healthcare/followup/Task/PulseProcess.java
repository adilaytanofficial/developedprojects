package adilaytan.healthcare.followup.Task;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import adilaytan.healthcare.followup.FraqAdap.PulseAdapter;
import adilaytan.healthcare.followup.JSONEncoder;
import adilaytan.healthcare.followup.SOAPER;
import adilaytan.healthcare.followup.Structure.Parameter;

public class PulseProcess extends AsyncTask<String,String,String> {

    private Context ctx;
    private String patid;
    private String gelen;
    private SOAPER soaper;
    private SwipeRefreshLayout ref;
    private ListView listView;
    private ArrayList<Parameter> al;
    private JSONEncoder js;

    public PulseProcess(Context context, String data, ListView lw,SwipeRefreshLayout swp){
        this.ctx = context;
        this.patid = data;
        this.soaper = new SOAPER(ctx);
        this.ref = swp;
        this.listView = lw;
        this.js = new JSONEncoder();
        this.ref = swp;
    }

    protected void onPreExecute() {
        gelen = "";
        al = new ArrayList<>();
        super.onPreExecute();
    }

    protected String doInBackground(String[] strings) {
        gelen = soaper.allPULSE(patid);
        return null;
    }

    protected void onPostExecute(String s) {
        if (ref.isRefreshing()){
            ref.setRefreshing(false);
        }
        al = js.GetPulse(gelen);
        PulseAdapter pr = new PulseAdapter((Activity) ctx,al);
        listView.setAdapter(pr);
        Toast.makeText(ctx,"Informations are updated!" ,Toast.LENGTH_SHORT).show();
        super.onPostExecute(s);
    }
}
