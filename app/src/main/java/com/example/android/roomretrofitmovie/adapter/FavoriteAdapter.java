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
import com.example.android.roomretrofitmovie.room.MovieEntity;
import com.example.android.roomretrofitmovie.ui.FavoriteDetail;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteAdapter extends ArrayAdapter<MovieEntity> {

    private ImageView image;


    public FavoriteAdapter(@NonNull Context context, List<MovieEntity> entities) {
        super(context, 0, entities);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listView = convertView;
        if (listView == null){
            listView = LayoutInflater.from(getContext()).inflate(R.layout.movie_cover, parent, false);
        }

        image = listView.findViewById(R.id.favorite_image);

        MovieEntity entity = getItem(position);

        final String poster = entity.getImage();
        final String title = entity.getTitle();
        final String description = entity.getOverview();
        final String date = entity.getReleaseDate();
        final String rate = entity.getUserRating();
        final int id = entity.getId();

        Picasso.with(getContext())
                .load(poster)
                .placeholder(R.drawable.miss_sloane)
                .error(R.drawable.miss_sloane)
                .into(image);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FavoriteDetail.class);
                intent.putExtra(FavoriteDetail.ID, id);
                intent.putExtra(FavoriteDetail.TITLE, title);
                intent.putExtra(FavoriteDetail.OVERVIEW, description);
                intent.putExtra(FavoriteDetail.RATE, rate);
                intent.putExtra(FavoriteDetail.DATE, date);
                intent.putExtra(FavoriteDetail.IMAGE, poster);
                getContext().startActivity(intent);

            }
        });

        return listView;
    }
}
