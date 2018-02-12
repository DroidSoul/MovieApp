package com.droidsoul.movieapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.droidsoul.movieapp.R;
import com.droidsoul.movieapp.activities.DetailActivity;
import com.droidsoul.movieapp.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by bear&bear on 2/11/2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    private List<Movie> mMovies;
    private Context mContext;
    private int orientation;



    public MovieAdapter(Context context, List<Movie> movies) {
        this.mMovies = movies;
        this.mContext = context;
        this.orientation = context.getResources().getConfiguration().orientation;
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tvTitle;
        public TextView tvOverview;
        public ImageView ivMovieImage;

        ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivMovieImage = itemView.findViewById(R.id.ivMovieImage);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Movie movie = mMovies.get(position);
                Intent i = new Intent(mContext, DetailActivity.class);
                i.putExtra("rating", movie.getRating());
                i.putExtra("popularity", String.valueOf(movie.getPopularity()));
                i.putExtra("synopsis", movie.getOverview());
                i.putExtra("posterPath", movie.getPostUrlPath());
                i.putExtra("title", movie.getTitle());
                mContext.startActivity(i);
            }
        }


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View movieView = inflater.inflate(R.layout.item_movie, parent, false);

        MovieAdapter.ViewHolder viewHolder = new MovieAdapter.ViewHolder(movieView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = mMovies.get(position);
        holder.tvTitle.setText(movie.getTitle());
        holder.tvOverview.setText(movie.getOverview());
        if (orientation == 1) {
            Picasso.with(getContext()).load(movie.getPostUrlPath()).transform(new RoundedCornersTransformation(10, 10)).into(holder.ivMovieImage);
        }
        else {
            Picasso.with(getContext()).load(movie.getBackDropPath()).transform(new RoundedCornersTransformation(10, 10)).into(holder.ivMovieImage);
        }
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    private Context getContext() {
        return mContext;
    }

}
