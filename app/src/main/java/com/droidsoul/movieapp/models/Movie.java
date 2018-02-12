package com.droidsoul.movieapp.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bear&bear on 2/11/2018.
 */

public class Movie {

    final static String postUrlPre = "https://image.tmdb.org/t/p/w342";
    final String backdropPre = "https://image.tmdb.org/t/p/w780/%s";
    String backDropPath;
    String postUrlPath;
    String title;
    double rating;
    double popularity;
    String overview;

    public String getPostUrlPath() {
        return postUrlPre + postUrlPath;
    }

    public String getBackDropPath() {
        return String.format(backdropPre, backDropPath);
    }

    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating / 2.0;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getOverview() {
        return overview;
    }

    public static Movie fromJSON(JSONObject jsonObject) {
        Movie movie = new Movie();
        try {
             movie.popularity = jsonObject.getDouble("popularity");
            movie.backDropPath = jsonObject.getString("backdrop_path");
            movie.overview = jsonObject.getString("overview");
            movie.title = jsonObject.getString("title");
            movie.rating = jsonObject.getDouble("vote_average");
            movie.postUrlPath = jsonObject.getString("poster_path");
        } catch(JSONException e) {
            e.printStackTrace();
        }
        return movie;
    }

    public static ArrayList<Movie> fromJSONArray(JSONArray jsonArray) {
        ArrayList<Movie> res = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                res.add(Movie.fromJSON(jsonArray.getJSONObject(i)));
            } catch(JSONException e) {
                e.printStackTrace();
            }
        }
        return res;
    }
}
