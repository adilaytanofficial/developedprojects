package com.adilaytan.medicaldictionarypro.TermsProcess;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.adilaytan.medicaldictionarypro.Classes.Class;
import com.adilaytan.medicaldictionarypro.DBHelpers.DatabaseHelper;
import com.adilaytan.medicaldictionarypro.R;


public class SaveTerm extends AppCompatActivity {

    private Class class_s;
    private EditText terimadi,terimaciklama;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        class_s = new Class();
        class_s.setProgramTheme(this);
        class_s.SetLanguage(this);

        setContentView(R.layout.save_term);
        getSupportActionBar().setTitle(getResources().getString(R.string.add_term_layout));

        if (savedInstanceState != null)
        {
            class_s.SetLanguage(this);
            getSupportActionBar().setTitle(getResources().getString(R.string.add_term_layout));
        }


        final String zatenvar = getResources().getString(R.string.term_exist) + " ";
        final String kaydet  = getResources().getString(R.string.added_term_success) + " ";
        terimadi = (EditText) findViewById(R.id.kelime);
        terimaciklama = (EditText) findViewById(R.id.aciklama);
        Button kaydetbuton = (Button) findViewById(R.id.butonkaydet);


        KeyboardShow();

        kaydetbuton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (terimadi.getText().length() <= 0 || terimaciklama.getText().length() <= 0) {
                    KeyboardHide();
                    class_s.SnackMessage(getResources().getString(R.string.fill_need),view);
                }
                else if (terimadi.getText().length() > 0 && terimaciklama.getText().length() > 0)
                {
                    KeyboardHide();
                    String terim = terimadi.getText().toString();
                    String aciklamasi = terimaciklama.getText().toString();
                    String query_term = terim.replaceAll("'","''");
                    DatabaseHelper db = new DatabaseHelper(SaveTerm.this);
                    boolean devam = db.KayitVarmi(query_term, "terim", "Terim_ad");
                    if (devam == false) {
                        db.KayitEkleme(terim, aciklamasi);
                        db.DigerKayitEkleme(terim, "kaydetad", "kaydedilenler");
                        terimadi.getText().clear();
                        terimaciklama.getText().clear();
                        class_s.SnackMessage(kaydet + terim.toString(),view);
                    }
                    else if (devam == true) {
                        class_s.SnackMessage(zatenvar + terim.toString(), view);
                    }
                }
            }

        });
    }


    private void KeyboardHide()
    {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(terimadi.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(terimaciklama.getWindowToken(), 0);
    }

    private void KeyboardShow()
    {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInputFromInputMethod(terimadi.getWindowToken(),1);
    }


}
