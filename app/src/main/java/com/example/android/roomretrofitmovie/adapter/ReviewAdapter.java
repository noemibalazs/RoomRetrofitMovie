package com.example.android.roomretrofitmovie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.roomretrofitmovie.R;
import com.example.android.roomretrofitmovie.model.Review;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private Context context;
    private ArrayList<Review> reviewList;

    public ReviewAdapter(Context context, ArrayList<Review> reviewList) {
        this.context = context;
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.movie_review, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {

        Review review = reviewList.get(position);

        String authorReview = review.getAuthor();
        String commentReview = review.getComments();

        holder.author.setText(authorReview);
        holder.comment.setText(commentReview);

    }

    @Override
    public int getItemCount() {
        if (reviewList == null){
            return 0;
        }
        return reviewList.size();
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder{

        private TextView author;
        private TextView comment;

        public ReviewViewHolder(View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.tv_author);
            comment = itemView.findViewById(R.id.tv_comment);
        }
    }
}
