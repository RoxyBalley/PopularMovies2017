package com.example.android.popularmoviesstage1;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHelper {

    public static final String KEY_SORT = "sort_key";
    private static final String FILE_NAME = "com.example.android.popularmoviesstage1.prefs";
    private SharedPreferences prefs;

    public PreferencesHelper(Context context) {
        prefs = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }


    /**
     * Save the specified value to the shared preferences
     *
     * @param key   The key of the value you wish to load
     * @param value The value to store
     */
    public void save(String key, int value) {
        String val = "null";
        if (value == 0) {
            val = "popular";
        } else if (value == 1) {
            val = "top_rated";
        }


        prefs.edit().putString(key, val).apply();
    }


    /**
     * Load the specified value from the shared preferences
     *
     * @param key      The key of the value you wish to load
     * @param defValue The default value to be returned if no value is found
     */
    public String loadString(String key, String defValue) {
        return prefs.getString(key, defValue);
    }

}
