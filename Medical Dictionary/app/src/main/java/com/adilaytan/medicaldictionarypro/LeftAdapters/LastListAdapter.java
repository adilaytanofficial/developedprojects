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
import com.adilaytan.medicaldictionarypro.LeftMenu.LastSearches;
import com.adilaytan.medicaldictionarypro.R;

import java.util.ArrayList;

public class LastListAdapter extends ArrayAdapter {

    ArrayList androidListViewStrings;
    Context context;

    private Class Sınıf;

    public LastListAdapter(Activity context, ArrayList list) {
        super(context, R.layout.last_list_item, list);
        this.androidListViewStrings = list;
        this.context = context;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRow = layoutInflater.inflate(R.layout.last_list_item, null, true);


        final String delete_last = getContext().getResources().getString(R.string.delete_last);
        final String deleted_last = getContext().getResources().getString(R.string.deleted_last_search) + " ";

        final TextView title_last = (TextView) viewRow.findViewById(R.id.last_terim);

        ImageView delete_last_btn = (ImageView) viewRow.findViewById(R.id.last_delete_btn);

        Sınıf = new Class();

        delete_last_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {
                AlertDialog.Builder cevap = new AlertDialog.Builder(getContext());
                cevap.setMessage(delete_last).setPositiveButton(R.string.yes ,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int is) {

                        Animation anim = AnimationUtils.loadAnimation(getContext(),android.R.anim.slide_out_right);
                        anim.setDuration(400);
                        LastSearches.listView1.getChildAt(i).startAnimation(anim);
                        anim.setAnimationListener(new Animation.AnimationListener() {
                            public void onAnimationStart(Animation animation) {

                            }

                            public void onAnimationEnd(Animation animation) {
                                String silinen = title_last.getText().toString();
                                final DatabaseHelper db = new DatabaseHelper(getContext());
                                String query = silinen.replaceAll("'","''");
                                db.Silme("sonaranan","sonaranan",query);
                                LastSearches.list.remove(i);
                                LastSearches.adapter.notifyDataSetChanged();
                                if (LastSearches.durum == true && LastSearches.list.size() == 0)
                                {
                                    ((LastSearches) getContext()).LayoutShow(false);
                                    Sınıf.SnackMessage(deleted_last + silinen,view);
                                }
                                else
                                {
                                    Sınıf.SnackMessage(deleted_last + silinen,view);
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
        title_last.setText(androidListViewStrings.get(i).toString());
        
        return viewRow;
    }
}