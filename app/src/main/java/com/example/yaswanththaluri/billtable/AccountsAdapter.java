package com.example.yaswanththaluri.billtable;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AccountsAdapter extends ArrayAdapter<ListAccounts>
{


    public AccountsAdapter(Activity context, ArrayList<ListAccounts> list)
    {
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_accounts, parent, false);
        }

        ListAccounts currentList = getItem(position);

        TextView view1 = (TextView) listItemView.findViewById(R.id.customer);
        view1.setText(currentList.getCustomerName());

        TextView view2 = (TextView) listItemView.findViewById(R.id.customer_id);
        view2.setText(currentList.getCustomerId());

        return listItemView;

    }

}


//import android.content.Context;
//import android.database.Cursor;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.CursorAdapter;
//import android.widget.TextView;
//
//public class AccountsAdapter extends CursorAdapter {
//
//    public AccountsAdapter(Context context, Cursor c) {
//        super(context, c);
//    }
//
//    @Override
//    public View newView(Context context, Cursor cursor, ViewGroup parent) {
//        // when the view will be created for first time,
//        // we need to tell the adapters, how each item will look
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View retView = inflater.inflate(R.layout.list_accounts, parent, false);
//
//        return retView;
//    }
//
//    @Override
//    public void bindView(View view, Context context, Cursor cursor) {
//        // here we are setting our data
//        // that means, take the data from the cursor and put it in views
//
//        TextView customerName = (TextView) view.findViewById(R.id.customer_name);
//        customerName.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));
//
//        TextView customerId = (TextView) view.findViewById(R.id.customer_id);
//        customerId.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));
//    }
//}