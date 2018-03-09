package com.example.sarahpagnani.sdesignapp.PeriodData;


import android.provider.BaseColumns;

public final class PeriodTable {
    private PeriodTable() {}

    public static class PeriodEntry implements BaseColumns {
        public static final String TABLE_NAME = "period_entry";
        public static final String START_DATE = "start_date";
//        public static final String START_SUBTITLE = "Period start date";
        public static final String END_DATE = "end_date";
        public static final String PERIOD_STATUS = "period_status";
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PeriodEntry.TABLE_NAME + " (" +
                    PeriodEntry._ID + " INTEGER PRIMARY KEY," +
                    PeriodEntry.START_DATE + " DATE," +
                    PeriodEntry.END_DATE + " DATE," +
                    PeriodEntry.PERIOD_STATUS + " INTEGER)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PeriodEntry.TABLE_NAME;
}
