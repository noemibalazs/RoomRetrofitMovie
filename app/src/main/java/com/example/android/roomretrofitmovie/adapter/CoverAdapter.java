package com.example.android.roomretrofitmovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.android.roomretrofitmovie.R;
import com.example.android.roomretrofitmovie.model.Movie;
import com.example.android.roomretrofitmovie.ui.MovieDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CoverAdapter extends ArrayAdapter<Movie>{

    private ImageView image;

    public CoverAdapter(@NonNull Context context, ArrayList<Movie> movies) {
        super(context, 0, movies);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        if (view == null){

            view = LayoutInflater.from(getContext()).inflate(R.layout.movie_cover, parent, false);
        }

        image = view.findViewById(R.id.favorite_image);

        Movie movie = getItem(position);

        final int id = movie.getId();
        final String title = movie.getOriginalTitle();
        final String posterImage = movie.getPosterImage();
        final String overview = movie.getMovieOverview();
        final String rating = movie.getUserRating();
        final String date = movie.getReleaseDate();

        Picasso.with(getContext())
                .load(posterImage)
                .placeholder(R.drawable.miss_sloane)
                .error(R.drawable.miss_sloane)
                .into(image);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MovieDetails.class);
                intent.putExtra(MovieDetails.ID, id);
                intent.putExtra(MovieDetails.TITLE, title);
                intent.putExtra(MovieDetails.IMAGE, posterImage);
                intent.putExtra(MovieDetails.OVERVIEW, overview);
                intent.putExtra(MovieDetails.RATE, rating);
                intent.putExtra(MovieDetails.DATE, date);
                getContext().startActivity(intent);
            }
        });


        return view;
    }
}
