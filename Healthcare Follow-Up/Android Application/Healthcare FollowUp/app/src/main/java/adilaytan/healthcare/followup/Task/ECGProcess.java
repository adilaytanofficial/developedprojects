package adilaytan.healthcare.followup.Task;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import adilaytan.healthcare.followup.FraqAdap.EcgAdapter;
import adilaytan.healthcare.followup.JSONEncoder;
import adilaytan.healthcare.followup.SOAPER;
import adilaytan.healthcare.followup.Structure.Parameter;

public class ECGProcess extends AsyncTask<String,String,String> {

    private Context ctx;
    private String patid;
    private String gelen;
    private SOAPER soaper;
    private JSONEncoder js;
    private ArrayList<Parameter> al;
    private SwipeRefreshLayout ref;
    private ListView listView;

    public ECGProcess(Context context, String data, ListView lw, SwipeRefreshLayout swp){
        this.ctx = context;
        this.patid = data;
        this.soaper = new SOAPER(ctx);
        this.listView = lw;
        this.ref = swp;
    }

    protected void onPreExecute() {
        gelen = "";
        al = new ArrayList<>();
        js = new JSONEncoder();
        super.onPreExecute();
    }

    protected String doInBackground(String[] strings) {
        gelen = soaper.allECG(patid);
        return null;
    }

    protected void onPostExecute(String s) {
        if (ref.isRefreshing()){
            ref.setRefreshing(false);
        }
        al = js.GetECG(gelen);
        EcgAdapter ea = new EcgAdapter((Activity)ctx,al);
        listView.setAdapter(ea);
        Toast.makeText(ctx,"Informations are updated!" ,Toast.LENGTH_SHORT).show();
        super.onPostExecute(s);
    }
}
