package com.example.yaswanththaluri.billtable;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yaswanththaluri.billtable.data.CustomerContract;
import com.example.yaswanththaluri.billtable.data.CustomerDbHelper;

public class Authentication extends AppCompatActivity {

    int pinCount = 0;
    private CustomerDbHelper mDbHelper;
    private String pin = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        mDbHelper = new CustomerDbHelper(this);
    }

    protected void onStart() {
        super.onStart();
        try {
            pinInfo();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Error in Authentication..!", Toast.LENGTH_SHORT).show();
        }
    }

    public void pinInfo()
    {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String count = " select count(*) from owner";
        Cursor mCursor = db.rawQuery(count,null);
        mCursor.moveToFirst();
        int  icount = mCursor.getInt(0);
        if(icount>0)
        {
            String[] projection = {
                    CustomerContract.CustomerEntry.PIN,
            };

            Cursor cursor = db.query(
                    CustomerContract.CustomerEntry.OWNER_TABLE_NAME,   // The table to query
                    projection,            // The columns to return
                    null,                  // The columns for the WHERE clause
                    null,                  // The values for the WHERE clause
                    null,                  // Don't group the rows
                    null,                  // Don't filter by row groups
                    null);



            try {
                int nameColumnIndex = cursor.getColumnIndex(CustomerContract.CustomerEntry.PIN);


                while (cursor.moveToNext()) {
                    // Use that index to extract the String or Int value of the word
                    // at the current row the cursor is on.
                    String currentPin = cursor.getString(nameColumnIndex);
                    pin = currentPin;
                }
            }
            finally {
                cursor.close();
            }
            authenticate();
        }
        else
        {
            Intent i = new Intent(Authentication.this, MainActivity.class);
            startActivity(i);
        }
        mCursor.close();
    }

    public void authenticate() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(Authentication.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_password, null);
        final EditText password = (EditText) mView.findViewById(R.id.digit_one);
        Button submit = (Button) mView.findViewById(R.id.submit_pin);
        final String oPin = pin;

        builder.setView(mView);
        final AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pin = password.getText().toString().trim();
                if (!pin.isEmpty()) {
                    if (oPin.equals(pin)) {
                        Toast.makeText(Authentication.this, "Login Successful", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                        Intent i = new Intent(Authentication.this, MainActivity.class);
                        startActivity(i);

                    } else {
                        if (pinCount != 3) {
                            pinCount += 1;
                            Toast.makeText(Authentication.this, "Login Failed...Try Again, Attempts left:"+(3-pinCount), Toast.LENGTH_SHORT).show();
                            password.setText("");
                        } else {
                            Toast.makeText(Authentication.this, "Login Attempts Exceeded", Toast.LENGTH_LONG).show();
                            finish();
                            System.exit(0);
                        }
                    }
                } else {
                    Toast.makeText(Authentication.this, "Pin Field Should not be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
