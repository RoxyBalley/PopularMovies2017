package com.example.android.popularmoviesstage1;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends ArrayAdapter<MovieItem> {

    public static final String LOG_TAG = MoviesAdapter.class.getSimpleName();
    private final Context mContext;
    private final int mReseouceId;
    private final List<MovieItem> mMovies;

    public MoviesAdapter(final Context context, final int resource, final ArrayList<MovieItem> movies) {
        super(context, resource, movies);
        mContext = context;
        mReseouceId = resource;
        mMovies = movies;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        final MovieItem movie = mMovies.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(mReseouceId, parent, false); //mResourceId = R.layout.movieItem
        }
        String url = movie.getPosterPath();
        String title = movie.getOriginalTitle();
        ImageView imageView = (ImageView) convertView.findViewById(R.id.movies_collection_item_poster);
        Picasso.with(mContext).load(url).into(imageView);
        TextView textView = (TextView) convertView.findViewById(R.id.movie_collection_title);
        textView.setText("     " + title);
        return convertView;
    }
}
