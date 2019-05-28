package adilaytan.healthcare.followup;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import adilaytan.healthcare.followup.Screens.ECGScreen;
import adilaytan.healthcare.followup.Screens.ProfileScreen;
import adilaytan.healthcare.followup.Screens.PulseOximeterScreen;
import adilaytan.healthcare.followup.Screens.PulseScreen;


public class MainScreen extends AppCompatActivity implements View.OnClickListener,BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bt;
    FragmentTransaction ft;
    JSONEncoder js;
    String tjson,pjson,ejson,psjson,lastjson,pojson;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_screen_layout_new);

        Intent y = getIntent();
        tjson = y.getStringExtra("tempjson");
        pjson = y.getStringExtra("pulsejson");
        ejson = y.getStringExtra("ecgjson");
        psjson = y.getStringExtra("psjson");
        lastjson = y.getStringExtra("lastjson");
        pojson = y.getStringExtra("pojson");
        bt = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bt.setItemIconTintList(null);
        bt.setOnNavigationItemSelectedListener(this);

        FragmentManager fm = getFragmentManager();
        ft = fm.beginTransaction();
        ProfileScreen pS = new ProfileScreen();
        Bundle bundle = new Bundle();
        bundle.putString("data", lastjson);
        pS.setArguments(bundle);
        ft.replace(R.id.frame1,pS);
        ft.commit();
        setTitle("Profile Screen");

    }

    public void onClick(View v) {
        int id = v.getId();
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_profile){
            FragmentManager fm = getFragmentManager();
            ft = fm.beginTransaction();
            ProfileScreen pS = new ProfileScreen();
            Bundle bundle=new Bundle();
            bundle.putString("data", lastjson);
            bundle.putString("data2",psjson);
            pS.setArguments(bundle);
            ft.replace(R.id.frame1,pS);
            ft.commit();
            setTitle("Profile Screen");
        }
        else if (id == R.id.action_pulse){
            FragmentManager fm = getFragmentManager();
            ft = fm.beginTransaction();
            PulseScreen ps = new PulseScreen();
            Bundle bundle=new Bundle();
            bundle.putString("data", pjson);
            ps.setArguments(bundle);
            ft.replace(R.id.frame1,ps);
            ft.commit();
            setTitle("Pulse");
        }
        else if(id == R.id.action_ecg){
            FragmentManager fm = getFragmentManager();
            ft = fm.beginTransaction();
            ECGScreen ecgScreen = new ECGScreen();
            Bundle bundle=new Bundle();
            bundle.putString("data", ejson);
            ecgScreen.setArguments(bundle);
            ft.replace(R.id.frame1,ecgScreen);
            ft.commit();
            setTitle("ECG");
        }
        else if(id == R.id.action_oxygen){
            FragmentManager fm = getFragmentManager();
            ft = fm.beginTransaction();
            PulseOximeterScreen po = new PulseOximeterScreen();
            Bundle bundle=new Bundle();
            bundle.putString("data", pojson);
            po.setArguments(bundle);
            ft.replace(R.id.frame1,po);
            ft.commit();
            setTitle("SpO2");
        }
        return true;
    }


}
