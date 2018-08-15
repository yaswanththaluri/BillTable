package com.example.yaswanththaluri.billtable;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yaswanththaluri.billtable.data.CustomerContract;
import com.example.yaswanththaluri.billtable.data.CustomerDbHelper;

public class PinChange extends AppCompatActivity {

    private EditText newPin;
    private EditText ConformPin;

    private String pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_change);

        newPin = (EditText)findViewById(R.id.new_password);
        ConformPin = (EditText)findViewById(R.id.conf_new_password);

        Button submit = (Button)findViewById(R.id.change);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click();
            }
        });

        Button reset = (Button)findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetField();
            }
        });
    }

    public void resetField()
    {
        newPin.setText("");
        ConformPin.setText("");
    }

    public void click()
    {
        if (newPin.getText().toString().equals(ConformPin.getText().toString()))
        {
            pin = newPin.getText().toString();
            updatePin();
        }
        else
            Toast.makeText(this, "New pin and Confirm Pin should be same", Toast.LENGTH_SHORT).show();
    }

    public void updatePin()
    {
        CustomerDbHelper mDbHelper = new CustomerDbHelper(PinChange.this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CustomerContract.CustomerEntry.PIN, pin);

        long newRowId = db.update(CustomerContract.CustomerEntry.OWNER_TABLE_NAME, values, CustomerContract.CustomerEntry.OWNER_ID + "= ?", new String[] {"1"});

        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving Owner", Toast.LENGTH_SHORT).show();
        }
        if(newRowId == 0)
        {
            Toast.makeText(this, "You Must save the details of trade first", Toast.LENGTH_SHORT).show();
            Intent i =new Intent(PinChange.this, Settings.class);
            startActivity(i);
        }
        else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Details saved successfully" + newRowId, Toast.LENGTH_LONG).show();
            Intent i =new Intent(PinChange.this, MainActivity.class);
            startActivity(i);
        }
    }
}
