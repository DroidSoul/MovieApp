package com.droidsoul.movieapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.droidsoul.movieapp.R;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

import static android.R.attr.rating;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        TextView tvTitle = (TextView)findViewById(R.id.tvTitle);
        ImageView ivMovieImage = (ImageView)findViewById(R.id.ivMovieImage);
        TextView tvOverview = (TextView)findViewById(R.id.tvOverview);
        TextView tvRating = (TextView)findViewById(R.id.tvRating);

        tvTitle.setText(getIntent().getStringExtra("title"));
        tvOverview.setText(getIntent().getStringExtra("synopsis"));
        tvRating.setText(String.format("%.1f", (float)getIntent().getDoubleExtra("rating", 0)) + " out of 5.0");
        Picasso.with(getBaseContext()).load(getIntent().getStringExtra("posterPath")).transform(new RoundedCornersTransformation(10, 10)).into(ivMovieImage);

    }
}
