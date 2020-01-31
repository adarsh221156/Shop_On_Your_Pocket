package com.example.shoponyourpocket;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DatabaseHelper extends SQLiteOpenHelper {

    FirebaseUser user;
    String uid;

    public static final String DATABASE_NAME = "user.db";
    public static final String TABLE_NAME = "user_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "product_name";
    public static final String COL_3 = "sale_price";
    public static final String COL_4 = "profit_per_item";
    public static final String COL_5 = "description";
    public static final String COL_6 = "item_source";
    public static final String COL_7 = "Availability";
    public static final String COL_8 = "number_of_item_sold";

    //when this constructor is called database is created
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        sqLiteDatabase.execSQL("create table "+ TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, product_name TEXT, sale_price DOUBLE, profit_per_item DOUBLE, description TEXT, item_source TEXT, Availability INTEGER, number_of_item_sold INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name, double sale_price, double profit, String descriptions, String source, int availability){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, sale_price);
        contentValues.put(COL_4, profit);
        contentValues.put(COL_5, descriptions);
        contentValues.put(COL_6, source);
        contentValues.put(COL_7, availability);
        contentValues.put(COL_8, 0);
        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public boolean updateData(int id,String name, double sale_price, double profit, String descriptions, String source, int availability, int sold){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, sale_price);
        contentValues.put(COL_4, profit);
        contentValues.put(COL_5, descriptions);
        contentValues.put(COL_6, source);
        contentValues.put(COL_7, availability);
        contentValues.put(COL_8, sold);
        sqLiteDatabase.update(TABLE_NAME, contentValues, "id = ?", new String[] {String.valueOf(id)});
        return true;
    }

    public int deleteData(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, "ID = ?", new String[] {String.valueOf(id)});
    }

}
