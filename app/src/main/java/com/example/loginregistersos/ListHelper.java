package com.example.loginregistersos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ListHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="receiver.db";
    public static final String TABLE_NAME ="receivers";
    public static final String TABLE_COL1 ="ID";
    public static final String TABLE_COL2 ="name";


    public ListHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create db tables
        db.execSQL("CREATE TABLE " +TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //tto update or modify db table
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String item1){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_COL2,item1);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result ==-1)
            return false;
        else
            return true;
    }


    public Cursor viewData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor myData = db.rawQuery("SELECT * FROM " +TABLE_NAME, null);
        return myData;
    }

    public boolean deleteData(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME,TABLE_COL2+" LIKE '" + item + "'",null);
        if(result ==0)
            return false;
        else
            return true;
    }


    public  boolean editData(String item1, int item2){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_COL2, item1);

        long result = db.update(TABLE_NAME, contentValues, TABLE_COL1 +" = '" + item2 + "'",null);

        if(result ==-1)
            return false;
        else
            return true;

    }

    public Cursor getid(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT "+TABLE_COL1+" FROM " + TABLE_NAME +" WHERE "+TABLE_COL2+" = '"+item+"'", null);
        return data;
    }




}
