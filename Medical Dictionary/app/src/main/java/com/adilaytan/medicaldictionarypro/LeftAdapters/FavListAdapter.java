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
import com.adilaytan.medicaldictionarypro.LeftMenu.Favs;
import com.adilaytan.medicaldictionarypro.R;

import java.util.ArrayList;

public class FavListAdapter extends ArrayAdapter {

    ArrayList androidListViewStrings;
    Context context;


    private Class Sınıf;
    private String fav_deleted,delete_fav;


    public FavListAdapter(Activity context, ArrayList textListView) {
        super(context, R.layout.fav_list_item, textListView);
        this.androidListViewStrings = textListView;
        this.context = context;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View viewRow = layoutInflater.inflate(R.layout.fav_list_item, null, true);

        final TextView title_fav = (TextView) viewRow.findViewById(R.id.fav_terim_2);

        final ImageView delete_fav_btn = (ImageView) viewRow.findViewById(R.id.fav_delete_btn);

        Sınıf = new Class();
        fav_deleted = getContext().getResources().getString(R.string.deletefav);
        delete_fav = getContext().getResources().getString(R.string.deletedfavterm) + " ";
        title_fav.setText(androidListViewStrings.get(i).toString());

        delete_fav_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {
                AlertDialog.Builder cevap = new AlertDialog.Builder(getContext());
                cevap.setMessage(fav_deleted).setPositiveButton(R.string.yes ,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int is) {
                        Animation anim = AnimationUtils.loadAnimation(getContext(),android.R.anim.slide_out_right);
                        anim.setDuration(400);
                        Favs.listView1.getChildAt(i).startAnimation(anim);
                        anim.setAnimationListener(new Animation.AnimationListener() {
                            public void onAnimationStart(Animation animation) {

                            }

                            public void onAnimationEnd(Animation animation) {
                                String silinen = title_fav.getText().toString();
                                final DatabaseHelper db = new DatabaseHelper(getContext());
                                String query = silinen.replaceAll("'","''");
                                db.Silme("favoriler","favadi",query);
                                Favs.list.remove(i);
                                Favs.favListAdapter.notifyDataSetChanged();
                                if (Favs.durum == true && Favs.list.size() == 0)
                                {
                                    ((Favs) getContext()).LayoutShow(false);
                                    Sınıf.SnackMessage(delete_fav + silinen,view);
                                }
                                else
                                {
                                    Sınıf.SnackMessage(delete_fav + silinen,view);
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

        
        return viewRow;
    }
}