package com.example.yaswanththaluri.billtable;

public class ListAccounts
{
    private String customer_name;
    private String customer_id;

    ListAccounts(String customer,String id)
    {
        customer_name = customer;
        customer_id = id;
    }

    public String getCustomerName()
    {
        return customer_name;
    }

    public String getCustomerId()
    {
        return customer_id;
    }
}
