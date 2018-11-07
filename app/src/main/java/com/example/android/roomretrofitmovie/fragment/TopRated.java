package com.example.android.roomretrofitmovie.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.roomretrofitmovie.R;
import com.example.android.roomretrofitmovie.adapter.CoverAdapter;
import com.example.android.roomretrofitmovie.model.Movie;
import com.example.android.roomretrofitmovie.model.MovieList;
import com.example.android.roomretrofitmovie.network.ApiClient;
import com.example.android.roomretrofitmovie.network.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopRated extends Fragment {

    private GridView gridView;
    private CoverAdapter adapter;
    private ArrayList<Movie> movies;

    public TopRated(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.movie_list, container, false);

        gridView = view.findViewById(R.id.grid_view);

        ApiInterface service = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<MovieList> topRated = service.getTopRatedList(getContext().getString(R.string.api_key));

        topRated.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(@NonNull Call<MovieList> call, @NonNull Response<MovieList> response) {
                movies = response.body().getMovieList();
                adapter = new CoverAdapter(getActivity(), movies);
                gridView.setAdapter(adapter);

            }

            @Override
            public void onFailure(@NonNull Call<MovieList> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), getString(R.string.toast), Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }
}
