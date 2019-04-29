package com.adilaytan.medicaldictionarypro.TermsProcess;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adilaytan.medicaldictionarypro.Classes.Class;
import com.adilaytan.medicaldictionarypro.DBHelpers.DatabaseHelper;
import com.adilaytan.medicaldictionarypro.R;


public class TermDescription extends AppCompatActivity {

    private Intent myintent;
    private String activity;
    private Class Sınıf;
    private String terimismi,paylass;
    private TextView terimad;
    private RelativeLayout layout;
    private static String ViaURL = "https://play.google.com/store/apps/details?id=";
    private boolean isFavourite = false;
    private DatabaseHelper mydb;
    private ImageView fav_view;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Sınıf = new Class();
        Sınıf.setProgramTheme(this);

        setContentView(R.layout.term_description);

        if (savedInstanceState != null)
        {
            Sınıf.SetLanguage(this);
            getSupportActionBar().setTitle(getResources().getString(R.string.description_layout));
        }

        layout = (RelativeLayout) findViewById(R.id.coordinatorLayout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.description_layout));

        myintent = getIntent();

        terimismi =  myintent.getExtras().getString("terimadi");
        String aciklamasi =  myintent.getExtras().getString("terimaciklama");

        fav_view = (ImageView) findViewById(R.id.fav_icon);
        mydb = new DatabaseHelper(TermDescription.this);
        isFavourite = mydb.KayitVarmi(terimismi.toString().replaceAll("'","''"),"favoriler","favadi");

        final TextView terimacik = (TextView) findViewById(R.id.textView2);
        terimad = (TextView) findViewById(R.id.textViewterim);
        TextView terimaciklama = (TextView) findViewById(R.id.textView3);
        String oku = getResources().getString(R.string.term_name) + " ";
        terimad.setText(oku + terimismi.toString());
        terimaciklama.setText(aciklamasi.toString());

        paylass = terimad.getText().toString() + "\n\n" + terimacik.getText().toString() + "\n\n" + aciklamasi
    + "\n\n" + getResources().getString(R.string.app_name) + " " + getResources().getString(R.string.version) + "\n\n" + ViaURL.toString() + getPackageName().toString();


        //materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        //favfabb = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
        //paylassfab = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);
        //copyfab = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item3);



    }

    public void onBackPressed() {
            super.onBackPressed();
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.description, menu);

        final MenuItem fav = menu.findItem(R.id.action_favourite);

        if (isFavourite == true)
        {
            fav.setVisible(false);
            fav_view.setVisibility(View.VISIBLE);
            //fav.setIcon(R.drawable.fav_selected);
        }

        fav.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem menuItem) {
                String oku = getResources().getString(R.string.addedfav) + " ";
                String var = getResources().getString(R.string.favterm_exists);

                if (isFavourite == true)
                {
                    //Sınıf.SnackMessage(var,layout);
                }
                else if (isFavourite == false)
                {
                    fav.setIcon(R.drawable.fav_selected);
                    mydb.DigerKayitEkleme(terimismi,"favadi","favoriler");
                    fav.setVisible(false);
                    fav_view.setVisibility(View.VISIBLE);
                    Sınıf.SnackMessage(oku + terimismi,layout);
                }
                return true;
            }
        });

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == android.R.id.home) {
            super.onBackPressed();
        }
        else if (id == R.id.action_share)
        {
            String baslik = getResources().getString(R.string.share_term);
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(android.content.Intent.EXTRA_TEXT, paylass);
            startActivity(Intent.createChooser(intent,baslik));
        }
        else if (id == R.id.action_copy)
        {
            ((ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE)).setText(paylass);
            String oku = getResources().getString(R.string.copied_clipboard);
            Sınıf.SnackMessage(oku,layout);
        }

        return super.onOptionsItemSelected(menuItem);
    }


}