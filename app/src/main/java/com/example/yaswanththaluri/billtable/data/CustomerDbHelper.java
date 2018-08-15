package com.example.yaswanththaluri.billtable.data;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.yaswanththaluri.billtable.data.CustomerContract.CustomerEntry;

public class CustomerDbHelper extends SQLiteOpenHelper
{
    public static final String LOG_TAG = CustomerDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "billtable.db";

    private static final int DATABASE_VERSION = 1;

    public CustomerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {

        try {
            String SQL_CREATE_PETS_TABLE = "CREATE TABLE " + CustomerEntry.TABLE_NAME + " ("
                    + CustomerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + CustomerEntry.NAME + " TEXT NOT NULL, "
                    + CustomerEntry.ADDRESS + " TEXT NOT NULL,"
                    + CustomerEntry.AGE + " INTEGER NOT NULL, "
                    + CustomerEntry.NUMBER + " TEXT NOT NULL);";

            // Execute the SQL statement
            db.execSQL(SQL_CREATE_PETS_TABLE);


            String SQL_CREATE_OWNER_TABLE = "CREATE TABLE  " + CustomerEntry.OWNER_TABLE_NAME + "("
                    + CustomerEntry.OWNER_ID + " INTEGER NOT NULL, "
                    + CustomerEntry.SHOP_NAME + " TEXT NOT NULL,"
                    + CustomerEntry.OWNER_NAME + " TEXT NOT NULL, "
                    + CustomerEntry.TYPE + " TEXT NOT NULL,"
                    + CustomerEntry.PIN + " TEXT NOT NULL);";

            db.execSQL(SQL_CREATE_OWNER_TABLE);
        } catch (SQLException e) {
            Log.v("Database Exception",
                    Log.getStackTraceString(e));
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
