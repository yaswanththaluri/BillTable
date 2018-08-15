package com.example.yaswanththaluri.billtable;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import com.example.yaswanththaluri.billtable.data.CustomerDbHelper;
import com.example.yaswanththaluri.billtable.data.CustomerContract.CustomerEntry;

public class accounts extends AppCompatActivity {

        private CustomerDbHelper mDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);


//        ArrayList<ListAccounts> lists = new ArrayList<ListAccounts>();
//
//        lists.add(new ListAccounts("Yaswanth","11602775"));
//        lists.add(new ListAccounts("Madhulika","11602776"));
//        lists.add(new ListAccounts("Haneesh","11602777"));
//        lists.add(new ListAccounts("Naveen","11602778"));
//
//        AccountsAdapter accounts = new AccountsAdapter(this,lists);
//        ListView view = findViewById(R.id.list_customers);
//        view.setAdapter(accounts);

        mDbHelper = new CustomerDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                CustomerEntry._ID,
                CustomerEntry.NAME,
        };

        Cursor cursor = db.query(
                CustomerEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);

        TextView displayName = (TextView) findViewById(R.id.cus_add_name);
        TextView displayId = (TextView) findViewById(R.id.cus_add_id);


        try {
            int idColumnIndex = cursor.getColumnIndex(CustomerEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(CustomerEntry.NAME);

            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);

                displayName.append(currentName+"\n\n");
                displayId.append("" + currentID+"\n\n");
            }
        }
        finally {
            cursor.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.icon_customer:
            {
                Intent i = new Intent(accounts.this,AddCustomer.class);
                startActivity(i);
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
