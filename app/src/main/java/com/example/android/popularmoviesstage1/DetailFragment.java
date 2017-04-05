package com.example.android.popularmoviesstage1;

import android.content.ContentProvider;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmoviesstage1.Data.MoviesProvider;
import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailFragment extends Fragment {
    private static final String ARG_MOVIE = DetailFragment.class.getSimpleName() + "movie";
    private MovieItem movie;

    /**
     * Empty constructor for system creation.
     */
    public DetailFragment() {
        setHasOptionsMenu(true);
    }

    public static DetailFragment newInstance(final MovieItem movie) {
        final DetailFragment fragment = new DetailFragment();
        final Bundle args = new Bundle();
        args.putParcelable(ARG_MOVIE, movie);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        movie = args.getParcelable(ARG_MOVIE);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        ((TextView) rootView.findViewById(R.id.movie_title)).setText(movie.getOriginalTitle());
        ((TextView) rootView.findViewById(R.id.movie_vote_avg)).setText(movie.getVoteAverage());
        ((TextView) rootView.findViewById(R.id.movie_overview)).setText(movie.getOverview());
        ((TextView) rootView.findViewById(R.id.movie_release_date)).setText(movie.getReleaseDate(true));
       // ((ImageButton) rootView.findViewById(R.id.movie_favorite_button).setSelected(boolean inactive));
        ImageView poster = (ImageView) rootView.findViewById(R.id.movie_poster_image);
        Picasso.with(getActivity()).load(movie.getPosterPath()).placeholder(R.mipmap.ic_launcher).into(poster);
        ImageView backdrop = (ImageView) rootView.findViewById(R.id.movie_backdrop_image);
        Picasso.with(getActivity()).load(movie.getBackdropPath()).placeholder(R.mipmap.ic_launcher).into(backdrop);
        return rootView;
    }


}
