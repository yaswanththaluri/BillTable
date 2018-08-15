package com.example.yaswanththaluri.billtable;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yaswanththaluri.billtable.data.CustomerDbHelper;
import com.example.yaswanththaluri.billtable.data.CustomerContract.CustomerEntry;

public class MainActivity extends AppCompatActivity {


//    public static final String EXTRA_OWNER = " ";
    private CustomerDbHelper mDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






//        try{
//            str= (String) getIntent().getExtras().get(EXTRA_OWNER);
//        }
//        catch (NullPointerException e)
//        {
//
//        }

        ImageView image = (ImageView)findViewById(R.id.icon_setttings);
        image.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(MainActivity.this,settings.class);
                startActivity(i);
            }
        });

//        TextView t = (TextView)findViewById(R.id.stock);
//        t.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

//        TextView t = (TextView)findViewById(R.id.set_owner_name);
//        t.setText("Hello"+str);

        TextView ac = (TextView)findViewById(R.id.accounts);
        ac.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(MainActivity.this,accounts.class);
                startActivity(i);
            }
        });

        TextView t2 = (TextView)findViewById(R.id.stock);
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Stock.class);
                startActivity(i);
            }
        });

        mDbHelper = new CustomerDbHelper(this);
    }

    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }



    private void displayDatabaseInfo() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                CustomerEntry.OWNER_NAME,
        };

        Cursor cursor = db.query(
                CustomerEntry.OWNER_TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);

        TextView t = (TextView)findViewById(R.id.set_owner_name);
//        t.append("");

        try {
                int nameColumnIndex = cursor.getColumnIndex(CustomerEntry.OWNER_NAME);


            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                String currentName = cursor.getString(nameColumnIndex);
                t.setText("Hello "+currentName);
            }
        }
        finally {
            cursor.close();
        }
    }
}
