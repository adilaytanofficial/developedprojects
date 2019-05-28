package adilaytan.healthcare.followup.Task;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;

import adilaytan.healthcare.followup.R;
import adilaytan.healthcare.followup.SOAPER;

public class LoginProcess extends AsyncTask<String, String, String> {

    private Context ctx;
    ProgressDialog proq;
    SOAPER lg;
    String data,donen,userType;
    Intent loader;
    boolean network_state;

    public LoginProcess(Context context,String json)
    {
        this.ctx = context;
        this.data = json;
        proq = new ProgressDialog(ctx, R.style.MyAlertDialogStyle);
        network_state = isConnected();
    }

    protected void onPreExecute() {
        lg = new SOAPER(ctx);
        proq.setMessage("Please wait, logging in...");
        proq.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        proq.setCancelable(false);
        proq.show();
        donen = "";
        super.onPreExecute();
    }

    protected String doInBackground(String[] strings) {
        if (network_state == true){
            donen = lg.loginVoid(data);
        }
        else
        {
            proq.dismiss();
            donen = "notconnected";
        }

        return "";
    }

    protected void onPostExecute(String e) {
        proq.dismiss();
        if(donen.equals("false")){
            Toast.makeText(ctx,"Please, check your login informations! ",Toast.LENGTH_SHORT).show();
        }
        else if (donen == "notconnected")
        {
            Toast.makeText(ctx,"Please, open your internet connection! ",Toast.LENGTH_SHORT).show();
        }
        else if(!donen.equals("false"))
        {
                loader = new Intent("adilaytan.healthcare.followup.PREPARE");
                loader.putExtra("id",donen);
                ctx.startActivity(loader);
        }
        super.onPostExecute(e);
    }

    private boolean isConnected() {
        boolean con = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            con = true;
        } else {
            con = false;
        }
        return con;
    }


}
