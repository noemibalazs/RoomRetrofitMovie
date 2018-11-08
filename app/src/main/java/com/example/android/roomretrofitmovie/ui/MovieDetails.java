package com.example.android.roomretrofitmovie.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.roomretrofitmovie.R;
import com.example.android.roomretrofitmovie.adapter.ReviewAdapter;
import com.example.android.roomretrofitmovie.adapter.TrailerAdapter;
import com.example.android.roomretrofitmovie.model.Review;
import com.example.android.roomretrofitmovie.model.ReviewList;
import com.example.android.roomretrofitmovie.model.Trailer;
import com.example.android.roomretrofitmovie.model.TrailerList;
import com.example.android.roomretrofitmovie.network.ApiClient;
import com.example.android.roomretrofitmovie.network.ApiInterface;
import com.example.android.roomretrofitmovie.room.MovieDatabase;
import com.example.android.roomretrofitmovie.room.MovieEntity;
import com.example.android.roomretrofitmovie.room.MovieExecutor;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieDetails extends AppCompatActivity {

    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String OVERVIEW = "overview";
    public static final String RATE = "rate";
    public static final String DATE = "date";
    public static final String IMAGE = "image";

    private TextView title;
    private TextView overview;
    private TextView rating;
    private TextView releaseDate;
    private ImageView image;
    private ImageView heart;

    private RecyclerView reviewRecycle;
    private RecyclerView trailerRecycle;
    private TrailerAdapter trailerAdapter;
    private ReviewAdapter reviewAdapter;

    private ArrayList<Trailer> trailers;
    private ArrayList<Review> reviews;

    private boolean onClick = true;

    private String movieTitle;
    private String movieDescription;
    private String movieRating;
    private String movieDate;
    private String moviePoster;
    private int movieId;

    private MovieDatabase database;
    private MovieEntity entity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        title = findViewById(R.id.tv_title);
        overview = findViewById(R.id.tv_detail_description);
        rating = findViewById(R.id.tv_detail_user_rating);
        releaseDate = findViewById(R.id.tv_detail_release_date);
        image = findViewById(R.id.iv_detail_image);
        heart = findViewById(R.id.image_heart);

        trailerRecycle = findViewById(R.id.recycle_trailer);
        LinearLayoutManager trailerManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        trailerRecycle.setLayoutManager(trailerManager);
        trailerRecycle.setHasFixedSize(true);

        reviewRecycle = findViewById(R.id.recycle_review);
        LinearLayoutManager reviewManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        reviewRecycle.setLayoutManager(reviewManager);
        reviewRecycle.setHasFixedSize(true);

        database = MovieDatabase.getDatabase(getApplicationContext());

        Intent intent = getIntent();

        if (intent == null){
            finish();
            Toast.makeText(this, getString(R.string.toast), Toast.LENGTH_SHORT).show();
        }

        movieTitle = intent.getExtras().getString(TITLE);
        movieDescription = intent.getExtras().getString(OVERVIEW);
        movieRating = intent.getExtras().getString(RATE ) + "/10";
        movieDate = intent.getExtras().getString(DATE);
        moviePoster = intent.getExtras().getString(IMAGE );
        movieId = intent.getExtras().getInt(ID);

        title.setText(movieTitle);
        overview.setText(movieDescription);
        rating.setText(movieRating);
        releaseDate.setText(movieDate);

        Picasso.with(this)
                .load(moviePoster)
                .placeholder(R.drawable.miss_sloane)
                .error(R.drawable.miss_sloane)
                .into(image);

        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);

        final Call<TrailerList> trailerList = apiInterface.getTrailerList(movieId, getString(R.string.api_key));
        trailerList.enqueue(new Callback<TrailerList>() {
            @Override
            public void onResponse(@NonNull Call<TrailerList> call, @NonNull Response<TrailerList> response) {
                if (response.body() != null) {
                    trailers = response.body().getTrailerList();
                    trailerAdapter = new TrailerAdapter(getApplicationContext(), trailers);
                    trailerRecycle.setAdapter(trailerAdapter);
                }
            }

            @Override
            public void onFailure(@NonNull Call<TrailerList> call, @NonNull Throwable t) {
                Toast.makeText(MovieDetails.this, getString(R.string.toast), Toast.LENGTH_SHORT).show();

            }
        });


        Call<ReviewList> reviewList = apiInterface.getReviewList(movieId, getString(R.string.api_key));
        reviewList.enqueue(new Callback<ReviewList>() {
            @Override
            public void onResponse(@NonNull Call<ReviewList> call, @NonNull Response<ReviewList> response) {
                if (response.body() != null) {
                    reviews = response.body().getReviewList();
                    reviewAdapter = new ReviewAdapter(getApplicationContext(), reviews);
                    reviewRecycle.setAdapter(reviewAdapter);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ReviewList> call, @NonNull Throwable t) {
                Toast.makeText(MovieDetails.this, getString(R.string.toast), Toast.LENGTH_SHORT).show();

            }
        });

        entity = new MovieEntity(movieId, movieTitle, moviePoster, movieDescription, movieRating, movieDate);

        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClick){

                    heart.setImageResource(R.drawable.green);
                    Toast.makeText(MovieDetails.this, getString(R.string.heart_picked_up), Toast.LENGTH_SHORT).show();

                    MovieExecutor.getsInstance().getDiskIo().execute(new Runnable() {
                        @Override
                        public void run() {
                            database.movieDao().insertMovie(entity);

                        }
                    });

                    onClick = false;

                } else {

                    heart.setImageResource(R.drawable.black);
                    Toast.makeText(MovieDetails.this, getString(R.string.heart_dismiss), Toast.LENGTH_SHORT).show();

                    MovieExecutor.getsInstance().getDiskIo().execute(new Runnable() {
                        @Override
                        public void run() {
                            database.movieDao().deleteMovie(entity);

                        }
                    });
                    onClick = true;
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.share){
            shareMovie();
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void shareMovie(){
        String subject = "Recommended movie for you: " + movieTitle;
        String text = "Short description: " + "\n"+ movieDescription
                + "\n" + "Release date: " + movieDate
                + "\n" + "User rating: " + movieRating;
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(Intent.createChooser(intent, getString(R.string.create_a_chooser)));



    }
}
