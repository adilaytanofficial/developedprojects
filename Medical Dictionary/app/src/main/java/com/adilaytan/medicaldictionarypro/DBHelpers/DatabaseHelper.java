package com.adilaytan.medicaldictionarypro.DBHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class DatabaseHelper extends SQLiteOpenHelper {
    String DB_PATH = null;
    private static String DB_NAME = "veritabani";
    private SQLiteDatabase myDatabase;
    private final Context myContext;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 10);
        this.myContext = context;
        this.DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
    }


    public void openDatabase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        myDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
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

    public String AciklamaGetir(String terimad) {
        String aciklama = "";
        String selectQuery = "SELECT Terim_aciklama FROM terim WHERE Terim_ad='" + terimad + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            aciklama = cursor.getString(0);
        }
        db.close();
        return aciklama;
    }


    public void Listeleme(String tablo_adi, ArrayList liste, Integer sutunno)
    {
        String selectQuery = "SELECT * FROM " + tablo_adi;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                liste.add(cursor.getString(sutunno));
            } while (cursor.moveToNext());
            db.close();
            Collections.sort(liste);
        }
    }


    public ArrayList MainList()
    {
        ArrayList list = new ArrayList();
        String query = "SELECT DISTINCT Terim_ad FROM terim ORDER BY Terim_ad";
        SQLiteDatabase db = this.getReadableDatabase();
        //db.beginTransaction();
        Cursor cursor = db.rawQuery(query, null);

        list.clear();
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0));
            } while (cursor.moveToNext());
            //Collections.sort(list);
        }
        /*db.setTransactionSuccessful();
        db.endTransaction();*/
        db.close();
        return list;
    }

    public ArrayList ListeDondur(String tablo_adi, ArrayList liste, Integer sutunno)
    {
        String selectQuery = "SELECT * FROM " + tablo_adi;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                liste.add(cursor.getString(sutunno));
            } while (cursor.moveToNext());
        }
        db.close();
        return liste;
    }

    public Cursor AciklamaCek(String searched)
    {
        String query =  "SELECT DISTINCT Terim_ad FROM terim WHERE Terim_aciklama LIKE '%" + searched + "%' ORDER BY Terim_ad";
        return getReadableDatabase().rawQuery(query,null);
    }

    public ArrayList ContentSearch(String query)
    {
        ArrayList list = new ArrayList();

       // String selectQuery = "SELECT DISTINCT Terim_ad FROM terim WHERE INSTR(Terim_aciklama,'" + query.toString() + "') > 0 ORDER BY Terim_ad";
        String selectQuery =  "SELECT DISTINCT Terim_ad FROM terim WHERE Terim_aciklama LIKE '%" + query + "%' ORDER BY Terim_ad";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0));
            } while (cursor.moveToNext());
            db.close();
        }
        return list;
    }


    public void Silme(String tabloadi,String sutun,String silinecek_deger)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(tabloadi, sutun + "='" + silinecek_deger + "'", null);
        db.close();
    }
    public void CokluSilme(String tabloadi,String sutun,ArrayList silinecek_degerler)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        for (int i = 0;i<silinecek_degerler.size();i++)
        {
            db.delete(tabloadi, sutun + "='" + silinecek_degerler.get(i).toString() + "'", null);
        }
        db.close();
    }


    public boolean KayitVarmi(String aranan,String tabloadi,String sutunadi) {
        boolean durum = false;

        String selectQuery = "SELECT * FROM " + tabloadi + " WHERE " + sutunadi + "='" + aranan + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.getCount() == 0) {
          durum = false;
        }
        else if (cursor.getCount() > 0)
        {
            durum = true;
        }

        db.close();

        return durum;
    }

    public void TabloSifirla(String tabloadi)
    {
        String selectQuery = "DELETE FROM " + tabloadi ;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(selectQuery);
        db.close();
    }


    public ArrayList Aramayap(String aranan) {
        ArrayList list = new ArrayList();
        String selectQuery = "SELECT DISTINCT Terim_ad FROM terim WHERE Terim_aciklama LIKE '%" + aranan + "%' ORDER BY Terim_ad LIMIT 5000";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        list.clear();
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0));
            } while (cursor.moveToNext());
            db.close();
            //Collections.sort(list);
        }
        return list;
    }

    public void DigerKayitEkleme(String veri,String sutun_adi,String tablo_adi)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(sutun_adi, veri);
        long rowID = db.insert(tablo_adi, null, cv);
        db.close();
    }
    public void KayitEkleme(String terim,String aciklama)
    {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("Terim_ad", terim);
            cv.put("Terim_aciklama", aciklama);
            long rowID = db.insert("terim", null, cv);
            db.close();
    }
    public Cursor query(String table,String[] columns,String selection,String[] selectionArgs,String groupBy,String having,String orderBy){
            return myDatabase.query("terim",null,null,null,null,null,null);
        }
}
