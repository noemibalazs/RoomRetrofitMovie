
package com.example.android.roomretrofitmovie.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.roomretrofitmovie.R;
import com.squareup.picasso.Picasso;

public class FavoriteDetail extends AppCompatActivity {

    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String OVERVIEW = "overview";
    public static final String RATE = "rate";
    public static final String DATE = "date";
    public static final String IMAGE = "image";

    private ImageView image;
    private TextView title;
    private TextView description;
    private TextView rate;
    private TextView date;

    private String mTitle;
    private String mDescription;
    private String mDate;
    private String mRate;
    private String mImage;
    private int mId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_detail);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setHomeButtonEnabled(true);

        image = findViewById(R.id.favorite_detail_image);
        title = findViewById(R.id.favorite_detail_title);
        description = findViewById(R.id.favorite_detail_description);
        rate = findViewById(R.id.favorite_detail_user_rating);
        date = findViewById(R.id.favorite_detail_release_date);

        Intent intent = getIntent();
        if (intent == null){
            finish();
            Toast.makeText(this, getString(R.string.toast), Toast.LENGTH_SHORT).show();
        }

        mId = intent.getExtras().getInt(ID);
        mTitle = intent.getExtras().getString(TITLE);
        mDate = intent.getExtras().getString(DATE);
        mImage = intent.getExtras().getString(IMAGE);
        mRate = intent.getExtras().getString(RATE) + "/10";
        mDescription = intent.getExtras().getString(OVERVIEW);

        title.setText(mTitle);
        description.setText(mDescription);
        rate.setText(mRate);
        date.setText(mDate);

        Picasso.with(this)
                .load(mImage)
                .placeholder(R.drawable.miss_sloane)
                .error(R.drawable.miss_sloane)
                .into(image);



    }
}
