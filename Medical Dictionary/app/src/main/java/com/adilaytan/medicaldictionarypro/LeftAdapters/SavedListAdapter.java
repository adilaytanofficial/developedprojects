package com.adilaytan.medicaldictionarypro.LeftAdapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.adilaytan.medicaldictionarypro.Classes.Class;
import com.adilaytan.medicaldictionarypro.DBHelpers.DatabaseHelper;
import com.adilaytan.medicaldictionarypro.LeftMenu.SavedTerm;
import com.adilaytan.medicaldictionarypro.R;

import java.util.ArrayList;

public class SavedListAdapter extends ArrayAdapter {

    ArrayList androidListViewStrings;
    Context context;


    private Class Sınıf;
    private TextView title_saved;

    public SavedListAdapter(Activity context, ArrayList list) {
        super(context, R.layout.fav_list_item, list);
        this.androidListViewStrings = list;
        this.context = context;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRow = layoutInflater.inflate(R.layout.saved_list_item, null, true);

        final String delete_save = getContext().getResources().getString(R.string.deletesaveterm);
        final String deleted_saved = getContext().getResources().getString(R.string.deletedsaveterm) + " ";
        title_saved = (TextView) viewRow.findViewById(R.id.saved_terim);

        ImageView delete_save_btn = (ImageView) viewRow.findViewById(R.id.saved_delete_btn);

        Sınıf = new Class();

        delete_save_btn.setOnClickListener(new View.OnClickListener() {
                 public void onClick(final View view) {
                     AlertDialog.Builder cevap = new AlertDialog.Builder(getContext());
                     cevap.setMessage(delete_save).setPositiveButton(R.string.yes ,new DialogInterface.OnClickListener() {
                         public void onClick(DialogInterface dialogInterface, int is) {
                             Animation anim = AnimationUtils.loadAnimation(getContext(),android.R.anim.slide_out_right);
                             anim.setDuration(400);
                             SavedTerm.listView1.getChildAt(i).startAnimation(anim);
                             anim.setAnimationListener(new Animation.AnimationListener() {

                                 public void onAnimationStart(Animation animation) {

                                 }

                                 public void onAnimationEnd(Animation animation) {
                                     String silinen = title_saved.getText().toString();
                                     final DatabaseHelper db = new DatabaseHelper(getContext());
                                     String query = silinen.replaceAll("'","''");
                                     db.Silme("kaydedilenler","kaydetad",query);
                                     db.Silme("terim","Terim_ad",query);
                                     boolean durm = db.KayitVarmi(query,"sonaranan","sonaranan");
                                     if (durm == true)
                                     {
                                         db.Silme("sonaranan","sonaranan",silinen);
                                     }
                                     SavedTerm.list.remove(i);
                                     SavedTerm.adapter.notifyDataSetChanged();
                                     if (SavedTerm.durum == true && SavedTerm.list.size() == 0)
                                     {
                                         ((SavedTerm) getContext()).LayoutShow(false);
                                         Sınıf.SnackMessage(deleted_saved + silinen,view);
                                     }
                                     else
                                     {
                                         Sınıf.SnackMessage(deleted_saved + silinen,view);
                                     }
                                 }

                                 public void onAnimationRepeat(Animation animation) {

                                 }
                             });

                         }
                     })
                             .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                                 public void onClick(DialogInterface dialogInterface, int i) {
                                     //
                                 }
                             });
                     AlertDialog baslat = cevap.create();
                     baslat.show();
                 }
         });

         title_saved.setText(androidListViewStrings.get(i).toString());
        
        return viewRow;
    }

}