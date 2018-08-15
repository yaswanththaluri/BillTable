package com.example.yaswanththaluri.billtable;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yaswanththaluri.billtable.data.CustomerContract;
import com.example.yaswanththaluri.billtable.data.CustomerDbHelper;

import java.net.IDN;


public class settings extends AppCompatActivity {


    private EditText shopname;
    private EditText ownername;
    private  EditText type;

    public String owner_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);



        Button button = (Button) findViewById(R.id.save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                shopname = (EditText) findViewById(R.id.shop_name);
                ownername = (EditText) findViewById(R.id.owner_name);
                type = (EditText) findViewById(R.id.business_type);
                Intent i = new Intent(settings.this,MainActivity.class);
                insertOwner();
//                i.putExtra(MainActivity.EXTRA_OWNER,  owner_name);
                startActivity(i);
            }
        });

        Button button2 = (Button)findViewById(R.id.change_pin);
        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(settings.this,PinChange.class);
                startActivity(i);
            }
        });
    }



    public void insertOwner()
    {
        String shop = shopname.getText().toString().trim();
        String owner = ownername.getText().toString().trim();
        String nature = type.getText().toString().trim();
        int id = 1;

        CustomerDbHelper mDbHelper = new CustomerDbHelper(settings.this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CustomerContract.CustomerEntry.OWNER_ID, id);
        values.put(CustomerContract.CustomerEntry.SHOP_NAME, shop);
        values.put(CustomerContract.CustomerEntry.OWNER_NAME, owner);
        values.put(CustomerContract.CustomerEntry.TYPE, nature);


        long newRowId = db.update(CustomerContract.CustomerEntry.OWNER_TABLE_NAME, values, CustomerContract.CustomerEntry.OWNER_ID + "= ?", new String[] {"1"});
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving Owner", Toast.LENGTH_SHORT).show();
        }
        if(newRowId == 0)
        {
            values.put(CustomerContract.CustomerEntry.PIN,"");
            db.insert(CustomerContract.CustomerEntry.OWNER_TABLE_NAME, null, values);
        }
        else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Details saved successfully" + newRowId, Toast.LENGTH_LONG).show();
        }


    }
}
