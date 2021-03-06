package com.example.android.popularmoviesstage1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class DetailActivity extends AppCompatActivity {

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