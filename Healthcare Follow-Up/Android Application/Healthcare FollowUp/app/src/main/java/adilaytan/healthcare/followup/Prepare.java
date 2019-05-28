package adilaytan.healthcare.followup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import adilaytan.healthcare.followup.Task.PrepareProcess;


public class Prepare extends Activity {

    String id,userType;
    TextView pat;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prepare_screen);

        Intent data = getIntent();
        id = data.getStringExtra("id");
        userType = data.getStringExtra("user");

        pat = (TextView) findViewById(R.id.pat);

        //Toast.makeText(this,userType,Toast.LENGTH_LONG).show();
        PrepareProcess px = new PrepareProcess(this,id,userType);
        px.execute();
        pat.setText("Patient ID : " + id);


    }
}
