package com.example.mmart.banpatito.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.mmart.banpatito.Customer;

import java.util.ArrayList;

/**
 * Created by MMART on 9/22/2017.
 */
public class CustomerHelper {
    private DBUtils dbHelper;
    private SQLiteDatabase database;
    private String[] CUSTOMER_TABLE_COLUMNS =
            {
                    DBUtils.CUSTOMER_ID,
                    DBUtils.CUSTOMER_NAME,
                    DBUtils.CUSTOMER_OPERATIONS,
                    DBUtils.CUSTOMER_POSITION
            };

    public CustomerHelper(Context context) {
        dbHelper = new DBUtils(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        database.close();
    }

    public Customer addCustomer(String name, int operations, int position) {
        ContentValues values = new ContentValues();
        values.put(DBUtils.CUSTOMER_NAME, name);
        values.put(DBUtils.CUSTOMER_OPERATIONS, operations);
        values.put(DBUtils.CUSTOMER_POSITION, position);
        long customerId = database.insert(DBUtils.CUSTOMER_TABLE, null, values);
        Cursor cursor = database.query(DBUtils.CUSTOMER_TABLE, CUSTOMER_TABLE_COLUMNS,
                DBUtils.CUSTOMER_ID+" = "+customerId, null, null, null, null);
        cursor.moveToFirst();
        Customer customer = parseCustomer(cursor);
        cursor.close();
        return customer;
    }
    
    public void deleteCustomer(String id) {
        database.delete(DBUtils.CUSTOMER_TABLE, DBUtils.CUSTOMER_ID+" = " + id, null);
    }

    public void deleteAllCustomers() {
        database.delete(DBUtils.CUSTOMER_TABLE, DBUtils.CUSTOMER_OPERATIONS+" > 0", null);
    }

    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        Cursor cursor = database.query(DBUtils.CUSTOMER_TABLE, CUSTOMER_TABLE_COLUMNS, null, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            customers.add(parseCustomer(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return customers;
    }

    private Customer parseCustomer(Cursor cursor) {
        Customer customer = new Customer();
        customer.setId(cursor.getString(cursor.getColumnIndex(DBUtils.CUSTOMER_ID)));
        customer.setName(cursor.getString(cursor.getColumnIndex(DBUtils.CUSTOMER_NAME)));
        customer.setOperations(cursor.getInt(cursor.getColumnIndex(DBUtils.CUSTOMER_OPERATIONS)));
        customer.setPosition(cursor.getInt(cursor.getColumnIndex(DBUtils.CUSTOMER_POSITION)));
        return customer;
    }
}
