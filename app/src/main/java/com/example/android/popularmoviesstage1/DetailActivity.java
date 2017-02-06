package com.example.android.popularmoviesstage1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by abs on 9/29/2016.
 */


public class DetailActivity extends ActionBarActivity {

    //Tag for passing as a parcelable
    public static final String EXTRA_MOVIE = "extra_movie";
    private MovieItem mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            if (getIntent().hasExtra(EXTRA_MOVIE)) {
                mMovie = getIntent().getParcelableExtra(EXTRA_MOVIE);
            }
            final DetailFragment fragment = DetailFragment.newInstance(mMovie);
            getSupportFragmentManager().beginTransaction().add(R.id.container, fragment).commit();
        }
    }
}
