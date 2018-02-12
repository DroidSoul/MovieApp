package com.droidsoul.movieapp.activities;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.droidsoul.movieapp.R;
import com.droidsoul.movieapp.adapters.MovieAdapter;
import com.droidsoul.movieapp.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieAcitivty extends AppCompatActivity {

    ArrayList<Movie> movies;
    RecyclerView rvMovies;
    MovieAdapter movieAdapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_acitivty);
        movies = new ArrayList<>();
        rvMovies = (RecyclerView)findViewById(R.id.rvMovies);
        movieAdapter = new MovieAdapter(this, movies);
        linearLayoutManager = new LinearLayoutManager(this);
        rvMovies.setLayoutManager(linearLayoutManager);
        rvMovies.setAdapter(movieAdapter);
        getDataFromApi();

    }
    public void getDataFromApi() {
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJSONResults = null;
                try {
                    movieJSONResults = response.getJSONArray("results");
                    movies.addAll(Movie.fromJSONArray(movieJSONResults));
                    movieAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }
}
