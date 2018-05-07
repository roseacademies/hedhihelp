package com.example.sarahpagnani.sdesignapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sarahpagnani.sdesignapp.PeriodData.PeriodDbHelper;
import com.example.sarahpagnani.sdesignapp.PeriodData.PeriodSettingsDbHelper;
import com.example.sarahpagnani.sdesignapp.PeriodData.PeriodSettingsTable;
import com.example.sarahpagnani.sdesignapp.PeriodData.PeriodTable;

import sun.bob.mcalendarview.MCalendarView;
import sun.bob.mcalendarview.MarkStyle;
import sun.bob.mcalendarview.listeners.OnDateClickListener;
import sun.bob.mcalendarview.vo.DateData;
import sun.bob.mcalendarview.vo.MarkedDates;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener{
    PeriodDbHelper mDBHelper;
    PeriodSettingsDbHelper periodSettingsDbHelper;
    private Button startTrack;
    private Button endTrack;
    private Button settings;
    private MCalendarView mCalendar;
    boolean isPeriod;
    Date startDate;
    DateData prev;
    int length;
    int gap;

    public static Date addDay(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, i);
        return cal.getTime();
    }
    public static Date addMonth(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, i);
        return cal.getTime();
    }
    public static Date addYear(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, i);
        return cal.getTime();
    }

    public boolean isSet() {
        SQLiteDatabase db = periodSettingsDbHelper.getReadableDatabase();

        String [] projection = {
                BaseColumns._ID,
                PeriodSettingsTable.PeriodSettingsEntry.isSet
        };

        String selection = PeriodSettingsTable.PeriodSettingsEntry.isSet + " LIKE ?";
        String[] selectionArgs = {"1"};

        Cursor cursor = db.query(
                PeriodSettingsTable.PeriodSettingsEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        cursor.moveToNext();
        boolean result = !cursor.isAfterLast();
        cursor.close();
        return result;

    }

    public void createDefaultRow() {
        SQLiteDatabase db = periodSettingsDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PeriodSettingsTable.PeriodSettingsEntry.PERIOD_GAP, 28);
        values.put(PeriodSettingsTable.PeriodSettingsEntry.PERIOD_LENGTH, 7);
        values.put(PeriodSettingsTable.PeriodSettingsEntry.isSet, 1);
        long newRowId = db.insert(PeriodSettingsTable.PeriodSettingsEntry.TABLE_NAME, null, values);
    }

    public Cursor getSettingsCursor() {
        SQLiteDatabase db = periodSettingsDbHelper.getReadableDatabase();

        String [] projection = {
                BaseColumns._ID,
                PeriodSettingsTable.PeriodSettingsEntry.PERIOD_GAP,
                PeriodSettingsTable.PeriodSettingsEntry.PERIOD_LENGTH
        };

        String selection = PeriodSettingsTable.PeriodSettingsEntry.isSet + " LIKE ?";
        String[] selectionArgs = {"1"};

        Cursor cursor = db.query(
                PeriodSettingsTable.PeriodSettingsEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        cursor.moveToNext();
        return cursor;
    }

    public int getGap(Cursor cursor) {
        return cursor.getInt(cursor.getColumnIndex(PeriodSettingsTable.PeriodSettingsEntry.PERIOD_GAP));
    }

    public int getLength(Cursor cursor) {
        return cursor.getInt(cursor.getColumnIndex(PeriodSettingsTable.PeriodSettingsEntry.PERIOD_LENGTH));
    }

    public void highlightLengthDays() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        Calendar keepStart = cal;
        int i = 0;
        do {
            mCalendar.markDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH));
            cal.add(Calendar.DAY_OF_YEAR, 1);
            i++;
        } while (i <= length);
        keepStart.add(Calendar.DAY_OF_YEAR, gap);
        i = 0;
        do {
            mCalendar.markDate(keepStart.get(Calendar.YEAR), keepStart.get(Calendar.MONTH)+1, keepStart.get(Calendar.DAY_OF_MONTH));
            cal.add(Calendar.DAY_OF_YEAR, 1);
            i++;
        } while (i <= length);

    }

    public void clearAllSelected() {
        MarkedDates marked = mCalendar.getMarkedDates();
        ArrayList<DateData> dates= marked.getAll();
        for (DateData d : dates) {
            mCalendar.unMarkDate(d);
        }
    }

    public void unhighlightThem() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        Calendar keepStart = cal;
        int i = 0;
        do {
            mCalendar.unMarkDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH));
            cal.add(Calendar.DAY_OF_YEAR, 1);
            i++;
        } while (i <= length);
        cal.add(Calendar.DAY_OF_YEAR, gap);
        i=0;
        do {
            mCalendar.unMarkDate(keepStart.get(Calendar.YEAR), keepStart.get(Calendar.MONTH)+1, keepStart.get(Calendar.DAY_OF_MONTH));
            cal.add(Calendar.DAY_OF_YEAR, 1);
            i++;
        } while (i <= length);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        // This gets a new reference to the database
        mDBHelper = new PeriodDbHelper(this);
        SQLiteDatabase db = mDBHelper.getReadableDatabase();

        periodSettingsDbHelper = new PeriodSettingsDbHelper(this);


        // Check the database to see if a settings row hasn't been created yet
        if (!isSet()) {
            createDefaultRow();
        }

        // If it has been created then we're rolling
        Cursor cursor = getSettingsCursor();
        gap = getGap(cursor);
        length = getLength(cursor);
        cursor.close();


        int result = queryDB(db);

        // Let's start doing some period logic
        long start = getStartDate(db);
        startDate = new Date(start);

        // This gets the buttons mapped to what's going on
        startTrack = (Button) findViewById(R.id.PeriodStatus);
        startTrack.setOnClickListener(this);
        endTrack = (Button) findViewById(R.id.StopPeriod);
        endTrack.setOnClickListener(this);
        settings  = (Button) findViewById(R.id.PeriodSettings);
        settings.setOnClickListener(this);

        // Let's map the CalendarView to something real quick
        mCalendar = (MCalendarView) findViewById(R.id.calendarView);
        mCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onDateClick(View view, DateData date) {
                Date current = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(current);
                mCalendar.markDate(date);
//                mCalendar.markDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
                if (prev != null) {
                    mCalendar.unMarkDate(prev);
                }
                prev = date;

                mCalendar.unMarkDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
            }
        });
//        mCalendar.setBackgroundColor(Color.CYAN);
        mCalendar.setMarkedStyle(MarkStyle.BACKGROUND, Color.parseColor("#53868B"));
//        mCalendar.setMarkedCell(R.layout.activity_calendar_cell_view);
        updateUI(result);

    }

    public void updateUI(int periodStatus) {
        if (periodStatus == 1) {
//            Log.i(TAG, "HELLO" + results[0]);
//            Log.i(TAG, results);
//            Toast.makeText(CalendarActivity.this, "There is a period going on", Toast.LENGTH_SHORT).show();
            highlightLengthDays();
            findViewById(R.id.PeriodStatus).setVisibility(View.GONE);
            findViewById(R.id.StopPeriod).setVisibility(View.VISIBLE);
        } else {
//            Toast.makeText(CalendarActivity.this, "There is no period.", Toast.LENGTH_SHORT).show();
            unhighlightThem();
            findViewById(R.id.PeriodStatus).setVisibility(View.VISIBLE);
            findViewById(R.id.StopPeriod).setVisibility(View.GONE);
        }

    }

    public long getStartDate(SQLiteDatabase db) {
        String[] projection = {
                BaseColumns._ID,
                PeriodTable.PeriodEntry.START_DATE
        };

        String selection = PeriodTable.PeriodEntry.PERIOD_STATUS + " = ?";
        String[]selectionArgs = {"1"};

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

        if (cursor.isAfterLast()) return -1;
        long startDate = cursor.getLong(cursor.getColumnIndex(PeriodTable.PeriodEntry.START_DATE));
        Toast.makeText(CalendarActivity.this, "START DATE: " + String.valueOf(startDate), Toast.LENGTH_SHORT).show();
        cursor.close();
        return startDate;
    }


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
    }

    @Override
    public void onClick(View view) {
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        int id = view.getId();
        if (id == R.id.PeriodStatus) {
            db = mDBHelper.getWritableDatabase();

            Cursor cursor = getSettingsCursor();
            gap = getGap(cursor);
            length = getLength(cursor);
            cursor.close();

            ContentValues values = new ContentValues();
            if (prev != null){
                Calendar c = Calendar.getInstance();
                c.set(prev.getYear(), prev.getMonth()-1, prev.getDay());
                startDate = c.getTime();
            }
            else
                startDate = new Date();
            values.put(PeriodTable.PeriodEntry.START_DATE, startDate.getTime());
            values.put(PeriodTable.PeriodEntry.PERIOD_STATUS, 1);
            values.put(PeriodTable.PeriodEntry.END_DATE, "not yet");
            long newRowId = db.insert(PeriodTable.PeriodEntry.TABLE_NAME, null, values);
            updateUI(1);
        }
        if (id == R.id.StopPeriod) {
            int results = queryDB(db);
            unhighlightThem();
            clearAllSelected();
            if (results != 0) {
                updateDB();
            }
            prev = null;
            updateUI(0);
        }
        if (id == R.id.PeriodSettings) {
            startActivity(new Intent(CalendarActivity.this, CalendarSettingsActivity.class));
            Cursor cursor = getSettingsCursor();
            gap = getGap(cursor);
            length = getLength(cursor);
        }
    }
}
