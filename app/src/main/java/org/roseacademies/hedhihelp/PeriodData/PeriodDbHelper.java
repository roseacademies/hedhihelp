package org.roseacademies.hedhihelp.PeriodData;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static org.roseacademies.hedhihelp.PeriodData.PeriodTable.SQL_CREATE_ENTRIES;

public class PeriodDbHelper extends SQLiteOpenHelper {


    // If there is ever a change to the database schema, change the version!
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "period.db";

    public PeriodDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is to save period info, so hold up with this stuff
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Figure this out later if this happens
    }
}
