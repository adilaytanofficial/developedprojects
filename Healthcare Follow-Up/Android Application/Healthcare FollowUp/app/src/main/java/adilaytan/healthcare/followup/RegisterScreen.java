package adilaytan.healthcare.followup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import adilaytan.healthcare.followup.Task.CreateUserProcess;


public class RegisterScreen extends AppCompatActivity implements View.OnClickListener {

    EditText un,pw,age,name,surname,gender;
    Button regbtn;

    SOAPER sp;
    JSONEncoder js;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.register_screen_layout);

        sp = new SOAPER(RegisterScreen.this);
        js = new JSONEncoder();

        un = (EditText) findViewById(R.id.nickname);
        name = (EditText) findViewById(R.id.name);
        surname = (EditText) findViewById(R.id.surname);
        age = (EditText) findViewById(R.id.age);
        gender = (EditText) findViewById(R.id.gender);
        pw = (EditText) findViewById(R.id.password);

        regbtn = (Button) findViewById(R.id.registerbtn);
        regbtn.setOnClickListener(this);
    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.registerbtn){
            String json = js.registerJSON(un.getText().toString(),name.getText().toString(),surname.getText().toString(),age.getText().toString(),gender.getText().toString(),pw.getText().toString());
            CreateUserProcess cu = new CreateUserProcess(this,json);
            cu.execute();
        }
    }
}
