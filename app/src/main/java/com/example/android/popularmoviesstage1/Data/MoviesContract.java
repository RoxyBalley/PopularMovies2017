package com.example.android.popularmoviesstage1.Data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by abs on 20/02/17.
 */
public class MoviesContract {
    public static final String CONTENT_AUTHORITY = "com.example.android.popularmoviesstage1.app";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_MOVIES = "favourite";

    public interface MoviesColumns {
        String MOVIE_ID = "movie_id";
        String MOVIE_TITLE = "movie_title";
    }

    public static final class MoviesEntry implements MoviesColumns, BaseColumns {

        public static final String TABLE_NAME = "movies";
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES).build();
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIES;
/*        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIES;*/

        /** Build {@link Uri} for requested {@link #MOVIE_ID}. */
        public static Uri buildFavouriteUri(String movieId) {
            return CONTENT_URI.buildUpon().appendPath(movieId).build();
        }

        /** Read {@link #MOVIE_ID} from {@link MoviesEntry} {@link Uri}. */
        public static String getMovieId(Uri uri) {
            return uri.getPathSegments().get(1);
        }
    }
    private MoviesContract() {
        throw new AssertionError("No instances.");
    }
}
