package com.example.yaswanththaluri.billtable.data;

import android.provider.BaseColumns;

public final class CustomerContract
{
    private CustomerContract()
    {

    }

    public static final class CustomerEntry implements BaseColumns
    {
        public final static String TABLE_NAME = "customers";

        public static final String _ID = BaseColumns._ID;

        public static final String NAME = "name";

        public static final String ADDRESS = "addresss";

        public static final String AGE = "age";

        public static final String NUMBER = "number";

        public static final String OWNER_TABLE_NAME = "owner";

        public static final String OWNER_ID = "id";

        public static final String SHOP_NAME = "shopname";

        public static final String OWNER_NAME  = "ownername";

        public static final String TYPE = "type";

        public static final String PIN = "pin";

    }
}
