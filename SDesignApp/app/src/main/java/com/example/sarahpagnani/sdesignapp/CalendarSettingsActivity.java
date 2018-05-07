package com.example.sarahpagnani.sdesignapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sarahpagnani.sdesignapp.PeriodData.PeriodSettingsDbHelper;
import com.example.sarahpagnani.sdesignapp.PeriodData.PeriodSettingsTable;
import com.example.sarahpagnani.sdesignapp.PeriodData.Settings;

public class CalendarSettingsActivity extends AppCompatActivity implements View.OnClickListener {
    private static int DEFAULT_LENGTH = 7;
    private static int DEFAULT_GAP = 28;

    PeriodSettingsDbHelper mDBHelper;
    TextView settingsTitle;
    EditText periodLength;
    EditText lengthBetweenPeriods;
    Button saveButton;
    Button cancelButton;
    Button restoreDefaults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_period_settings);

        periodLength = (EditText) findViewById(R.id.period_length_form);
        lengthBetweenPeriods = (EditText) findViewById(R.id.length_between_periods_form);
        saveButton = (Button) findViewById(R.id.save_button);
        cancelButton = (Button) findViewById(R.id.cancel_button);
        restoreDefaults = (Button) findViewById(R.id.restore_defaults);

        mDBHelper = new PeriodSettingsDbHelper(this);

        SQLiteDatabase db = mDBHelper.getWritableDatabase();
//        db.
//        if (!isSet(db)) {
//            // Create Default Settings
//            createDefaultRow(db);
//        }
        Settings res = getSettings();


        periodLength.setText(String.valueOf(res.getLength()));
        lengthBetweenPeriods.setText(String.valueOf(res.getGap()));

        restoreDefaults.setOnClickListener(this);
        saveButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }

    public Settings getSettings() {
        Settings result;
        SQLiteDatabase db = mDBHelper.getReadableDatabase();

        String [] projection = {
                BaseColumns._ID,
                PeriodSettingsTable.PeriodSettingsEntry.PERIOD_LENGTH,
                PeriodSettingsTable.PeriodSettingsEntry.PERIOD_GAP,
                PeriodSettingsTable.PeriodSettingsEntry.isSet
        };
        String selection = PeriodSettingsTable.PeriodSettingsEntry.isSet + " = ?";
        String[]selectionArgs = {"1"};

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
        if (cursor.isAfterLast()) {
            return new Settings(DEFAULT_LENGTH, DEFAULT_GAP);
        }
        int length = cursor.getInt(cursor.getColumnIndexOrThrow(PeriodSettingsTable.PeriodSettingsEntry.PERIOD_LENGTH));
        int gap = cursor.getInt(cursor.getColumnIndex(PeriodSettingsTable.PeriodSettingsEntry.PERIOD_GAP));
        cursor.close();
        result = new Settings(length, gap);

        Toast.makeText(CalendarSettingsActivity.this, "Length: " + String.valueOf(result.getLength()) + " Gap: " + String.valueOf(result.getGap()), Toast.LENGTH_SHORT).show();

        return result;
    }

    public boolean isSet(SQLiteDatabase db) {
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

    public void createDefaultRow(SQLiteDatabase db) {
        createRow(28, 7);
    }

    public void createRow(int gap, int length) {
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        // First we check if there is a row already there
        if (isSet(db)) {
            deleteRow(db);
        }
        // If there isn't, let's make one! Geez.
        db = mDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PeriodSettingsTable.PeriodSettingsEntry.PERIOD_GAP, gap);
        values.put(PeriodSettingsTable.PeriodSettingsEntry.PERIOD_LENGTH, length);
        values.put(PeriodSettingsTable.PeriodSettingsEntry.isSet, 1);
        long newRowId = db.insert(PeriodSettingsTable.PeriodSettingsEntry.TABLE_NAME, null, values);
    }

    public void deleteRow(SQLiteDatabase db) {
        // If there is, we nuke it
//        db = mDBHelper.getWritableDatabase();
        // clean up the db, we should only have one entry
        String selection = PeriodSettingsTable.PeriodSettingsEntry.isSet + " LIKE ?";
        String[] selectionArgs = {"1"};
        int deletedRows = db.delete(PeriodSettingsTable.PeriodSettingsEntry.TABLE_NAME, selection, selectionArgs);
//            Toast.makeText(CalendarSettingsActivity.this, "This many rows: " + String.valueOf(deletedRows), Toast.LENGTH_SHORT).show();
    }

    public void saveSettings() {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        deleteRow(db);
        createRow(Integer.parseInt(lengthBetweenPeriods.getText().toString()), Integer.parseInt(periodLength.getText().toString()));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.restore_defaults) {
            periodLength.setText("7");
            lengthBetweenPeriods.setText("28");
        }
        if (id == R.id.save_button) {
            saveSettings();
            finish();
        }
        if (id == R.id.cancel_button) {
            finish();
        }
    }
}
