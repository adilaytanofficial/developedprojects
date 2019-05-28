package adilaytan.healthcare.followup.Task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import adilaytan.healthcare.followup.SOAPER;

public class CreateUserProcess extends AsyncTask<String,String,String> {

    private Context ctx;
    private String json;
    private String gelen;
    private SOAPER soaper;
    private ProgressDialog pq;

    public CreateUserProcess(Context context,String data){
        this.ctx = context;
        this.json = data;
        this.soaper = new SOAPER(ctx);
        this.pq = new ProgressDialog(ctx);
    }

    protected void onPreExecute() {
        gelen = "";
        pq.setMessage("Kullanıcı Oluşturuluyor...");
        pq.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pq.setCancelable(false);
        pq.show();
        super.onPreExecute();
    }

    protected String doInBackground(String[] strings) {
        gelen = soaper.createVoid(json);
        return null;
    }

    protected void onPostExecute(String s) {
        pq.dismiss();
        Toast.makeText(ctx,"" + gelen,Toast.LENGTH_SHORT).show();
        super.onPostExecute(s);
    }
}
