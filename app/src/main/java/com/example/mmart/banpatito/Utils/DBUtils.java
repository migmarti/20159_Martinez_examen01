package com.example.mmart.banpatito.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MMART on 9/22/2017.
 */
public class DBUtils extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="BanPatito";
    public static final int DATABASE_VERSION = 1;

    public static final String CUSTOMER_TABLE="Customers";
    public static final String CUSTOMER_ID="id";
    public static final String CUSTOMER_NAME="name";
    public static final String CUSTOMER_OPERATIONS="operations";
    public static final String CUSTOMER_POSITION="position";

    public static final String DATABASE_CREATE =
            "CREATE TABLE "+CUSTOMER_TABLE +
                    "("+CUSTOMER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CUSTOMER_NAME + " TEXT NOT NULL, " +
                    CUSTOMER_OPERATIONS + " INTEGER NOT NULL," +
                    CUSTOMER_POSITION + " INTEGER NOT NULL)";

    public DBUtils(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CUSTOMER_TABLE + "");
        onCreate(db);
    }
}
