package com.example.mmart.banpatito.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MMART on 9/22/2017.
 */
public class DBUtils extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="BanPatito";
    public static final int DATABASE_VERSION = 2;

    public static final String CUSTOMER_TABLE="Customers";
    public static final String CUSTOMER_ID="id";
    public static final String CUSTOMER_NAME="name";
    public static final String CUSTOMER_OPERATIONS="operations";
    public static final String CUSTOMER_POSITION="position";

    public static final String CUSTOMERVISITS_TABLE="CustomerVisits";
    public static final String CV_ID= "id";
    public static final String CVCUSTOMER_ID="idCustomer";
    public static final String CVVISIT_ID="idVisit";

    public static final String VISIT_TABLE="Visits";
    public static final String VISIT_ID="id";
    public static final String VISIT_DATE="date";

    public static final String CREATE_CUSTOMER =
            "CREATE TABLE "+CUSTOMER_TABLE +
                    "("+CUSTOMER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CUSTOMER_NAME + " TEXT NOT NULL, " +
                    CUSTOMER_OPERATIONS + " INTEGER NOT NULL," +
                    CUSTOMER_POSITION + " INTEGER NOT NULL)";

    public static final String CREATE_CUSTOMERVISITS =
            "CREATE TABLE "+CUSTOMERVISITS_TABLE +
                    "("+CV_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CVCUSTOMER_ID + " INTEGER NOT NULL, " +
                    CVVISIT_ID + " INTEGER NOT NULL, " +
                    "FOREIGN KEY(" + CVCUSTOMER_ID + ") REFERENCES Customers(id), " +
                    "FOREIGN KEY(" + CVVISIT_ID + ") REFERENCES Visits(id) )";

    public static final String CREATE_VISIT =
            "CREATE TABLE "+VISIT_TABLE +
                    "("+VISIT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    VISIT_DATE + " TEXT NOT NULL)";

    public DBUtils(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CUSTOMER);
        db.execSQL(CREATE_CUSTOMERVISITS);
        db.execSQL(CREATE_VISIT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CUSTOMER_TABLE + "");
        db.execSQL("DROP TABLE IF EXISTS " + CUSTOMERVISITS_TABLE + "");
        db.execSQL("DROP TABLE IF EXISTS " + VISIT_TABLE + "");
        onCreate(db);
    }
}
