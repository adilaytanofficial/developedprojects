package adilaytan.healthcare.followup;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import adilaytan.healthcare.followup.Task.LoginProcess;

public class LoginScreen extends Activity implements View.OnClickListener {

    Button login,register;
    EditText un,pw,age,name,surname,gender;
    RadioButton user;
    SOAPER soaper;
    JSONEncoder js;
    RadioGroup group;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.login_screen_layout);
        js = new JSONEncoder();
        soaper  = new SOAPER(this);

        user = (RadioButton) findViewById(R.id.user_type);
        login = (Button) findViewById(R.id.loginbtn);
        register = (Button) findViewById(R.id.registerbtn);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        group = (RadioGroup) findViewById(R.id.radio_group);
        un = (EditText) findViewById(R.id.username);
        pw = (EditText) findViewById(R.id.password);
    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.loginbtn){
            String json = js.loginJSON(un.getText().toString(),pw.getText().toString());
            LoginProcess lg = new LoginProcess(this,json);
            lg.execute();
        }
        else if (id == R.id.registerbtn){
            Intent ac = new Intent("adilaytan.healthcare.followup.REGISTERSCREEN");
            startActivity(ac);
        }
    }


}
