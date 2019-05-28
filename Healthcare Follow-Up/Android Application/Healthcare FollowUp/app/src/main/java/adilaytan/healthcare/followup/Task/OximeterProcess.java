package adilaytan.healthcare.followup.Task;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import adilaytan.healthcare.followup.FraqAdap.OxygenAdapter;
import adilaytan.healthcare.followup.JSONEncoder;
import adilaytan.healthcare.followup.SOAPER;
import adilaytan.healthcare.followup.Structure.Parameter;

public class OximeterProcess extends AsyncTask<String,String,String> {

    private Context ctx;
    private String patid;
    private String gelen;
    private SOAPER soaper;
    private SwipeRefreshLayout ref;
    private ListView listView;
    private ArrayList<Parameter> al;
    private JSONEncoder js;

    public OximeterProcess(Context context, String data, ListView lw, SwipeRefreshLayout swp){
        this.ctx = context;
        this.patid = data;
        this.soaper = new SOAPER(ctx);
        this.ref = swp;
        this.listView = lw;
        this.js = new JSONEncoder();
    }

    protected void onPreExecute() {
        gelen = "";
        al = new ArrayList<>();
        super.onPreExecute();
    }

    protected String doInBackground(String[] strings) {
        gelen = soaper.allSPO2(patid);
        return null;
    }

    protected void onPostExecute(String s) {
        if (ref.isRefreshing()){
            ref.setRefreshing(false);
        }
        al = js.GetOxy(gelen);
        OxygenAdapter oxygenAdapter = new OxygenAdapter((Activity) ctx,al);
        listView.setAdapter(oxygenAdapter);
        Toast.makeText(ctx,"Informations are updated!" ,Toast.LENGTH_SHORT).show();
        super.onPostExecute(s);
    }
}
