package com.example.sarahpagnani.sdesignapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sarahpagnani.sdesignapp.PeriodData.PeriodDbHelper;
import com.example.sarahpagnani.sdesignapp.PeriodData.PeriodTable;

import static android.content.ContentValues.TAG;

import java.util.Date;

/**
 * Created by SarekSoteloJimenez on 3/8/18.
 */

public class PeriodActivity extends AppCompatActivity implements View.OnClickListener{
    PeriodDbHelper mDBHelper;
    private Button startTrack;
    private Button endTrack;
    private Button settings;
    boolean isPeriod;

    public int queryDB(SQLiteDatabase db) {
        String[] projection = {
                BaseColumns._ID,
                PeriodTable.PeriodEntry.PERIOD_STATUS
        };

        String selection = PeriodTable.PeriodEntry.PERIOD_STATUS + " = ?";
        String[] selectionArgs = {"1"};

//        String sortOrder = PeriodEntry.
        Cursor cursor = db.query(
                PeriodTable.PeriodEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        cursor.moveToNext();
        if (cursor.isAfterLast()) return 0;
        return 1;
//        return result;
    }

    public void updateDB() {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        Long endDate = (new Date().getTime());
        ContentValues values = new ContentValues();
        values.put(PeriodTable.PeriodEntry.END_DATE, endDate);

        String selection = PeriodTable.PeriodEntry.PERIOD_STATUS + " LIKE ?";
        String[] selectionArgs = {"1"};

        int count = db.update(
                PeriodTable.PeriodEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        int deletedRows = db.delete(PeriodTable.PeriodEntry.TABLE_NAME, selection, selectionArgs);
        Toast.makeText(PeriodActivity.this, "This many rows: " + String.valueOf(deletedRows), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        // This gets a new reference to the database
        mDBHelper = new PeriodDbHelper(this);
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        int results = queryDB(db);

        startTrack = (Button) findViewById(R.id.PeriodStatus);
        startTrack.setOnClickListener(this);
        endTrack = (Button) findViewById(R.id.StopPeriod);
        endTrack.setOnClickListener(this);
        settings  = (Button) findViewById(R.id.PeriodSettings);
        settings.setOnClickListener(this);

        if (results == 1) {
//            Log.i(TAG, "HELLO" + results[0]);
//            Log.i(TAG, results);
            Toast.makeText(PeriodActivity.this, "There is a period going on", Toast.LENGTH_SHORT).show();
            findViewById(R.id.PeriodStatus).setVisibility(View.GONE);
            findViewById(R.id.StopPeriod).setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(PeriodActivity.this, "There is no period.", Toast.LENGTH_SHORT).show();
            findViewById(R.id.PeriodStatus).setVisibility(View.VISIBLE);
            findViewById(R.id.StopPeriod).setVisibility(View.GONE);
        }


    }


    @Override
    public void onClick(View view) {
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        int id = view.getId();
        if (id == R.id.PeriodStatus) {
            db = mDBHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(PeriodTable.PeriodEntry.START_DATE, new Date().getTime());
            values.put(PeriodTable.PeriodEntry.PERIOD_STATUS, 1);
            values.put(PeriodTable.PeriodEntry.END_DATE, "not yet");
            long newRowId = db.insert(PeriodTable.PeriodEntry.TABLE_NAME, null, values);

            findViewById(R.id.PeriodStatus).setVisibility(View.GONE);
            findViewById(R.id.StopPeriod).setVisibility(View.VISIBLE);
        }
        if (id == R.id.StopPeriod) {
            int results = queryDB(db);
            if (results != 0) {
                updateDB();
            }
            findViewById(R.id.StopPeriod).setVisibility(View.GONE);
            findViewById(R.id.PeriodStatus).setVisibility(View.VISIBLE);
        }
        if (id == R.id.PeriodSettings) {
            
        }
    }
}
