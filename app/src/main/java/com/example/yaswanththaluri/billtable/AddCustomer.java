package com.example.yaswanththaluri.billtable;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yaswanththaluri.billtable.data.CustomerContract;
import com.example.yaswanththaluri.billtable.data.CustomerDbHelper;

import java.util.ArrayList;

public class AddCustomer extends AppCompatActivity {

    private EditText nameEditText;
    private EditText addressEditText;
    private EditText ageEditText;
    private EditText numberEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        Button button = (Button) findViewById(R.id.add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                nameEditText = (EditText)findViewById(R.id.customer_name);
                addressEditText = (EditText) findViewById(R.id.customer_address);
                ageEditText = (EditText) findViewById(R.id.age);
                numberEditText = (EditText) findViewById(R.id.customer_mobile);
                insertCustomer();
                Intent i =new Intent(AddCustomer.this,accounts.class);
                startActivity(i);
            }
        });
    }
    private void insertCustomer() {
        String name = nameEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();
        String number = numberEditText.getText().toString().trim();
        int age = Integer.parseInt(ageEditText.getText().toString());

        CustomerDbHelper mDbHelper = new CustomerDbHelper(AddCustomer.this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CustomerContract.CustomerEntry.NAME, name);
        values.put(CustomerContract.CustomerEntry.ADDRESS, address);
        values.put(CustomerContract.CustomerEntry.AGE, age);
        values.put(CustomerContract.CustomerEntry.NUMBER, number);

        long newRowId = db.insert(CustomerContract.CustomerEntry.TABLE_NAME, null, values);

        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving Customer", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Details saved successfully" + newRowId, Toast.LENGTH_LONG).show();
        }
    }
}
