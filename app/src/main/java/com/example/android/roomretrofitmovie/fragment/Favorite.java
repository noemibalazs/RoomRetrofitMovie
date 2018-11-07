package com.example.android.roomretrofitmovie.fragment;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.android.roomretrofitmovie.R;
import com.example.android.roomretrofitmovie.adapter.FavoriteAdapter;
import com.example.android.roomretrofitmovie.room.FavoriteViewModel;
import com.example.android.roomretrofitmovie.room.MovieDatabase;
import com.example.android.roomretrofitmovie.room.MovieEntity;

import java.util.List;

public class Favorite extends Fragment {

    public Favorite() {
    }

    private GridView gridView;
    private FavoriteAdapter adapter;
    private MovieDatabase database;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.movie_list, container, false);
        gridView = view.findViewById(R.id.grid_view);

        database = MovieDatabase.getDatabase(getActivity());

        FavoriteViewModel viewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);
        viewModel.getEntities().observe(this, new Observer<List<MovieEntity>>() {
            @Override
            public void onChanged(@Nullable List<MovieEntity> movieEntities) {
                adapter = new FavoriteAdapter(getActivity(), movieEntities);
                gridView.setAdapter(adapter);

            }
        });

        return view;
    }

}
