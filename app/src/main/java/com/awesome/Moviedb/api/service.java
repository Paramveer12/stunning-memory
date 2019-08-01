package com.awesome.Moviedb.api;

import com.awesome.Moviedb.model.TrailerResponse;
import com.awesome.Moviedb.model.moviesresponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface service {

    @GET("movie/popular")
    Call<moviesresponse> getPopularMovies(@Query("api_key") String apikey);

    @GET("movie/top_rated")
    Call<moviesresponse> getTopratedMovies(@Query("api_key") String apikey);

    @GET("movie/{movie_id}/videos")
    Call<TrailerResponse> getMovieTrailer(@Path("movie_id") int id, @Query("api_key") String apiKey);
}
