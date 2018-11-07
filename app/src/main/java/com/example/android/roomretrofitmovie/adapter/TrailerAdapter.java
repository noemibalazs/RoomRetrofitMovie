package com.example.android.roomretrofitmovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.roomretrofitmovie.R;
import com.example.android.roomretrofitmovie.model.Trailer;

import java.util.ArrayList;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {

    private Context context;
    private ArrayList<Trailer> trailerList;

    public TrailerAdapter(Context context, ArrayList<Trailer> trailers){
        this.context = context;
        this.trailerList = trailers;
    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.movie_trailer, parent, false);
        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, int position) {

        Trailer trailer = trailerList.get(position);
        final String url = trailer.getTrailerImage();
        holder.click.setImageResource(R.drawable.play);
        holder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                if (intent.resolveActivity(context.getPackageManager()) != null){
                    context.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if (trailerList == null){
            return 0;
        }
        return trailerList.size();
    }

    class TrailerViewHolder extends RecyclerView.ViewHolder{

        private ImageView click;

        public TrailerViewHolder(View itemView) {
            super(itemView);
            click = itemView.findViewById(R.id.image_play);
        }
    }
}
