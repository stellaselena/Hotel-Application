package com.example.stellajovanovic.hotelapplication.CheckIn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Stella on 06.05.2017.
 */

public class CustomerSQLiteHelper extends SQLiteOpenHelper {

    private static final int database_VERSION = 1;
    public static final String database_NAME = "customerDB";
    private static final String table_CUSTOMERS = "customers";
    private static final String customer_ID = "id";
    private static final String customer_NAME = "name";
    private static final String customer_SURNAME = "surname";
    public static final String customer_ROOMNUMBER = "roomnumber";
    private static final String[] COLUMNS = {customer_ID, customer_NAME, customer_SURNAME, customer_ROOMNUMBER};

    public CustomerSQLiteHelper(Context context) {
        super(context, database_NAME, null, database_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CUSTOMER_TABLE = "CREATE TABLE customers ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "name TEXT, " + "surname TEXT, " + "roomnumber TEXT)";
        db.execSQL(CREATE_CUSTOMER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS customers");
        this.onCreate(db);
    }

    public void createCustomer(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(customer_NAME, customer.getName());
        values.put(customer_SURNAME, customer.getSurname());
        values.put(customer_ROOMNUMBER, customer.getRoomNumber());


        db.insert(table_CUSTOMERS, null, values);

        db.close();
    }

    public Customer getCustomer(int id) {
        // get reference of the BookDB database
        SQLiteDatabase db = this.getReadableDatabase();

        // get book query
        Cursor cursor = db.query(table_CUSTOMERS, // a. table
                COLUMNS, " id = ?", new String[] { String.valueOf(id) }, null, null, null, null);

        // if results !=null, parse the first one
        if (cursor != null)
            cursor.moveToFirst();

        Customer customer = new Customer();
        customer.setId(Integer.parseInt(cursor.getString(0)));
        customer.setName(cursor.getString(1));
        customer.setSurname(cursor.getString(2));
        customer.setRoomNumber(cursor.getString(3));

        return customer;
    }

    public List getAllCustomers() {
        List customers = new LinkedList();

        String query = "SELECT  * FROM " + table_CUSTOMERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Customer customer = null;
        if (cursor.moveToFirst()) {
            do {
                customer = new Customer();
                customer.setId(Integer.parseInt(cursor.getString(0)));
                customer.setName(cursor.getString(1));
                customer.setSurname(cursor.getString(2));
                customer.setRoomNumber(cursor.getString(3));

                customers.add(customer);
            } while (cursor.moveToNext());
        }
        return customers;
    }

    public int updateCustomer(Customer customer) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", customer.getName());
        values.put("surname", customer.getSurname());
        values.put("roomnumber", customer.getRoomNumber());

        int i = db.update(table_CUSTOMERS, values, customer_ID + " = ?", new String[] { String.valueOf(customer.getId()) });

        db.close();
        return i;
    }

    public void deleteCustomer(Customer customer) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(table_CUSTOMERS, customer_ID + " = ?", new String[] { String.valueOf(customer.getId()) });
        db.close();
    }
}

