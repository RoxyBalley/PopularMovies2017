package com.example.android.popularmoviesstage1.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.example.android.popularmoviesstage1.Data.MoviesContract.MoviesEntry;
import com.example.android.popularmoviesstage1.Data.MoviesContract.MoviesColumns;

/**
 * Created by abs on 20/02/17.
 */
public class MoviesDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "popularMovies.db";

    public MoviesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_MOVIES_TABLE = "CREATE TABLE " + MoviesEntry.TABLE_NAME + " ("
                + BaseColumns._ID + " INTEGER NOT NULL PRIMARY KEY,"
                + MoviesColumns.MOVIE_ID + " TEXT NOT NULL,"
                + MoviesColumns.MOVIE_TITLE + " TEXT NOT NULL,"
                + "UNIQUE (" + MoviesColumns.MOVIE_ID + ") ON CONFLICT REPLACE)";

        sqLiteDatabase.execSQL(SQL_CREATE_MOVIES_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MoviesContract.MoviesEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
