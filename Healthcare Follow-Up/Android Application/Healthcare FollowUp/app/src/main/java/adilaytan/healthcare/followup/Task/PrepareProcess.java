package adilaytan.healthcare.followup.Task;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import adilaytan.healthcare.followup.JSONEncoder;
import adilaytan.healthcare.followup.R;
import adilaytan.healthcare.followup.SOAPER;

public class PrepareProcess extends AsyncTask<String,String,String> {

    private Context ctx;
    private String id;
    private SOAPER sp;
    private String personjson,lastjson,tempjson,pulsejson,ejson,pojson,patientsjson,userType;
    private JSONEncoder js;


    public PrepareProcess(Context context, String patientid, String userType){
        this.ctx = context;
        this.id = patientid;
        this.sp = new SOAPER(ctx);
        this.js = new JSONEncoder();
        this.userType = userType;
    }

    protected void onPreExecute() {
        personjson = "";
        lastjson = "";
        super.onPreExecute();
    }

    protected String doInBackground(String... strings) {
            personjson = sp.getPersonalInfo(id);
            lastjson = sp.getLastInfo(id);
            tempjson = sp.allTEMP(id);
            pulsejson = sp.allPULSE(id);
            ejson = sp.allECG(id);
            pojson = sp.allSPO2(id);
            return null;
    }


    protected void onPostExecute(String s) {
            Intent x = new Intent("adilaytan.healthcare.followup.MAINSCREEN");
            x.putExtra("psjson",personjson);
            x.putExtra("tempjson",tempjson);
            x.putExtra("pulsejson",pulsejson);
            x.putExtra("ecgjson",ejson);
            x.putExtra("lastjson",lastjson);
            x.putExtra("pojson",pojson);
            ctx.startActivity(x);
            super.onPostExecute(s);
    }
}
