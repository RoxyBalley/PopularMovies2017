package com.example.android.popularmoviesstage1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by abs on 9/29/2016.
 */

public class MainActivity extends ActionBarActivity implements MoviesFragment.Callback {

   // private final String LOG_TAG = MainActivity.class.getSimpleName();

    //private String sort_para;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Bundle args = new Bundle();
        args.putString("sort_key","popularity.desc");
        MoviesFragment fragment = new MoviesFragment();
        fragment.setArguments(args);*/
        //to add fragment to main activity layout
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_collection_swipe_refresh_layout, new MoviesFragment())
                    .commit();
        }
    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //inflater.inflate(R.menu.action_bar_spinner, menu);

        getMenuInflater().inflate(R.menu.action_bar_spinner, menu);
        MenuItem item = menu.findItem(R.id.spinner);
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       *//*         String item = parent.getItemAtPosition(position).toString();
                selectSortBy(position);*//*
                int userChoice = parent.getSelectedItemPosition();
                updateSpinner(userChoice);
*//*                SharedPreferences sharedPref = getSharedPreferences("FileName",MODE_PRIVATE);
                SharedPreferences.Editor prefEditor = sharedPref.edit();
                prefEditor.putInt("userChoiceSpinner",userChoice);
                prefEditor.apply();*//*
*//*
                SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
                editor.putString("sort", sort_para);
                editor.apply();
*//*

                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "Selected: " + userChoice, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // Create an ArrayAdapter using the string array and a default spinner layout. The default layout provided by the platform is used here.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sort_options, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears. The default layout provided by the platform is used here.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter on the spinner
        spinner.setAdapter(adapter);
        //Spinner spinner = (Spinner) findViewById(R.id.spinner);
        return true;
    }
    public void updateSpinner(int userChoice){
        SharedPreferences sharedPref = this.getSharedPreferences("FileName",MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putInt("userChoiceSpinner",userChoice);
        prefEditor.apply();
    }*/

    @Override
    public void onItemSelected(final MovieItem movie) {
        final Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_MOVIE, movie);
        startActivity(intent);
    }

/*    public String selectSortBy(int pos){
        switch (pos){
        case 0: sort_para = "popularity.desc";
            break;
            case 1: sort_para = "vote_average.desc";
                break;
            default: Toast.makeText(this,"None sorting option is selected",Toast.LENGTH_LONG).show();

        }
         //function(sort_para);
        return sort_para;
    }*/
}