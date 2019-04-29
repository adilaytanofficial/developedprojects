package com.adilaytan.medicaldictionarypro.DBHelpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.adilaytan.medicaldictionarypro.SubRecycler.SubtermItem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubDatabaseHelper extends SQLiteOpenHelper {
    String DB_PATH = null;
    private static String DB_NAME = "subterms";
    private SQLiteDatabase myDatabase;
    private final Context myContext;

    public SubDatabaseHelper(Context context) {
        super(context, DB_NAME, null, 10);
        this.myContext = context;
        this.DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
    }


    public void openDatabase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        myDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    public synchronized void close() {
        if (myDatabase != null)
            myDatabase.close();
        super.close();
    }


    public void onCreate(SQLiteDatabase db) {

    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public ArrayList parentHeader()
    {
        ArrayList list = new ArrayList();

        String selectQuery = "SELECT DISTINCT anakelime FROM altkelimeler ORDER BY anakelime";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()) {
            do {
                    /*if (!list.contains(cursor.getString(3)))
                    {*/
                        list.add(cursor.getString(0));
                    //}
            } while (cursor.moveToNext());
            //Collections.sort(list);
        }
        db.close();
        return list;
    }

    public ArrayList parentHeaderArray()
    {
        ArrayList list = new ArrayList();

        String selectQuery = "SELECT * FROM altkelimeler";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                if (!list.contains(cursor.getString(3)))
                {
                    list.add(cursor.getString(3));
                }
            } while (cursor.moveToNext());
            db.close();
            Collections.sort(list);
        }
        return list;
    }


    public List<SubtermItem> getSubItems(String query)
    {
        List<SubtermItem> childs = new ArrayList<>();

        String cek = query.replace("'","''");

        String selectQuery = "SELECT * FROM altkelimeler WHERE anakelime = '" +  cek + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        childs.clear();
        if (cursor.moveToFirst()) {
            do {
                if (!childs.contains(cursor.getString(1)))
                {
                    SubtermItem subtermItem = new SubtermItem();
                    subtermItem.setName(cursor.getString(1));
                    subtermItem.setDescription(cursor.getString(2));
                    childs.add(subtermItem);
                }
            } while (cursor.moveToNext());
            if (childs.size() > 1)
            {
                Sort(childs);
            }
            db.close();
        }
        return childs;
    }


    public void Sort(List<SubtermItem> list)
    {
        List<SubtermItem> updated_list = new ArrayList<>();
        List liste = new ArrayList();
        for (SubtermItem subtermItem : list)
        {
            liste.add(subtermItem.getName());
        }
        Collections.sort(liste);
        for (SubtermItem subtermItem: list)
        {
            for (Object str : liste)
            {
                if (str.toString() == subtermItem.getName())
                {
                    SubtermItem sub_updated = new SubtermItem();
                    sub_updated.setName(str.toString());
                    sub_updated.setDescription(subtermItem.getDescription());
                    updated_list.add(sub_updated);
                }
            }
        }
        list = updated_list;
    }

    public Cursor query(String table,String[] columns,String selection,String[] selectionArgs,String groupBy,String having,String orderBy){
        return myDatabase.query("altkelimeler",null,null,null,null,null,null);
    }
}

