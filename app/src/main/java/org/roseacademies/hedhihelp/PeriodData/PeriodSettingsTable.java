package org.roseacademies.hedhihelp.PeriodData;


import android.provider.BaseColumns;

public class PeriodSettingsTable {
    private PeriodSettingsTable() {}

    public static class PeriodSettingsEntry implements BaseColumns {
        public static final String TABLE_NAME = "settings_entry";
        public static final String PERIOD_LENGTH = "period_length"; // measured in days
        public static final String PERIOD_GAP = "period_gap";
        public static final String isSet = "is_set";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + PeriodSettingsEntry.TABLE_NAME + " (" +
                        PeriodSettingsEntry._ID + " INTEGER PRIMARY KEY," +
                        PeriodSettingsEntry.PERIOD_LENGTH + " INTEGER," +
                        PeriodSettingsEntry.PERIOD_GAP + " INTEGER," +
                        PeriodSettingsEntry.isSet + " INTEGER);";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + PeriodSettingsEntry.TABLE_NAME;
    }
}
