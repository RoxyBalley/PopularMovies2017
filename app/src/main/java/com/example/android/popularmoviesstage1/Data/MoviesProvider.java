package com.example.android.popularmoviesstage1.Data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;


/**
 * Created by abs on 20/02/17.
 */
public class MoviesProvider extends ContentProvider{

    private static final String TAG = MoviesProvider.class.getSimpleName();
    private static final SQLiteQueryBuilder sFavouriteQueryBuilder = new SQLiteQueryBuilder();

    private SQLiteOpenHelper mOpenHelper;

    static final int MOVIES = 100;
    static final int MOVIES_ID = 101;
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = MoviesContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, MoviesContract.PATH_MOVIES, MOVIES);

        return matcher;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case MOVIES:
                return MoviesContract.MoviesEntry.CONTENT_TYPE;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        // Here's the switch statement that, given a URI, will determine what kind of request it is,
        // and query the database accordingly.
        Cursor retCursor;
        switch (sUriMatcher.match(uri)) {
            case MOVIES:
            {
                retCursor = getFavourite(uri, projection);
                break;
            }

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    private Cursor getFavourite(
            Uri uri, String[] projection) {
        String favouriteSetting = MoviesContract.MoviesEntry.getMovieId(uri);

        return sFavouriteQueryBuilder.query(mOpenHelper.getReadableDatabase(),
                projection,
                sFavouriteSelection,
                new String[]{favouriteSetting},
                null,
                null,
                null
        );
    }
    private static final String sFavouriteSelection =
            MoviesContract.MoviesEntry.TABLE_NAME +
                    "." + MoviesContract.MoviesEntry.MOVIE_ID + " = ? " ;

    private void notifyChange(Uri uri) {
        getContext().getContentResolver().notifyChange(uri, null);
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new MoviesDbHelper(getContext());
        return true;
    }
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case MOVIES: {
                db.insertOrThrow(MoviesContract.MoviesEntry.TABLE_NAME, null, values);
                notifyChange(uri);
                return MoviesContract.MoviesEntry.buildFavouriteUri(values.getAsString(MoviesContract.MoviesEntry.MOVIE_ID));
            }
            default: {
                throw new UnsupportedOperationException("Unknown insert uri: " + uri);
            }
        }
    }
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
            //Timber.tag(TAG).v("update(uri=" + uri + ", values=" + values.toString() + ")");
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsUpdated;
        switch (match) {
            case MOVIES:
                rowsUpdated = db.update(MoviesContract.MoviesEntry.TABLE_NAME,values, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown update uri" + uri);
        }
        if(rowsUpdated != 0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return rowsUpdated;
    }
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Timber.tag(TAG).v("delete(uri=" + uri + ")");
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsDeleted;
        if (null == selection) selection = "1";
        switch (match) {
            case MOVIES:
                rowsDeleted = db.delete(MoviesContract.MoviesEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown delete uri" + uri);
        }
        if(rowsDeleted != 0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return rowsDeleted;
    }
}
