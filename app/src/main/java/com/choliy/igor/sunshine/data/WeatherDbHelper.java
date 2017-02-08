package com.choliy.igor.sunshine.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.choliy.igor.sunshine.data.WeatherContract.WeatherEntry.COLUMN_DATE;
import static com.choliy.igor.sunshine.data.WeatherContract.WeatherEntry.COLUMN_DEGREES;
import static com.choliy.igor.sunshine.data.WeatherContract.WeatherEntry.COLUMN_HUMIDITY;
import static com.choliy.igor.sunshine.data.WeatherContract.WeatherEntry.COLUMN_MAX_TEMP;
import static com.choliy.igor.sunshine.data.WeatherContract.WeatherEntry.COLUMN_MIN_TEMP;
import static com.choliy.igor.sunshine.data.WeatherContract.WeatherEntry.COLUMN_PRESSURE;
import static com.choliy.igor.sunshine.data.WeatherContract.WeatherEntry.COLUMN_WEATHER_ID;
import static com.choliy.igor.sunshine.data.WeatherContract.WeatherEntry.COLUMN_WIND_SPEED;
import static com.choliy.igor.sunshine.data.WeatherContract.WeatherEntry.TABLE_NAME;

/**
 * Manages a local database for weather data.
 */
class WeatherDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "weather.db";
    private static final int DATABASE_VERSION = 1;

    WeatherDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_WEATHER_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +

                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_DATE + " INTEGER NOT NULL, " +
                        COLUMN_WEATHER_ID + " INTEGER NOT NULL, " +
                        COLUMN_MIN_TEMP + " REAL NOT NULL, " +
                        COLUMN_MAX_TEMP + " REAL NOT NULL, " +
                        COLUMN_HUMIDITY + " REAL NOT NULL, " +
                        COLUMN_PRESSURE + " REAL NOT NULL, " +
                        COLUMN_WIND_SPEED + " REAL NOT NULL, " +
                        COLUMN_DEGREES + " REAL NOT NULL, " +

                        /*
                         * To ensure this table can only contain one weather entry per date, we declare
                         * the date column to be unique. We also specify "ON CONFLICT REPLACE". This tells
                         * SQLite that if we have a weather entry for a certain date and we attempt to
                         * insert another weather entry with that date, we replace the old weather entry.
                         */
                        "UNIQUE (" + COLUMN_DATE + ") ON CONFLICT REPLACE);";

        sqLiteDatabase.execSQL(SQL_CREATE_WEATHER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        final String SQL_DELETE_WEATHER_TABLE = "DROP TABLE IF EXIST " + TABLE_NAME;
        sqLiteDatabase.execSQL(SQL_DELETE_WEATHER_TABLE);
        onCreate(sqLiteDatabase);
    }
}