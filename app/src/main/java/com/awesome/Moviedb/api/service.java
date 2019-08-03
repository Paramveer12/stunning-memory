package com.awesome.Moviedb.api;

import com.awesome.Moviedb.model.TrailerResponse;
import com.awesome.Moviedb.model.moviesresponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface service {

    //https://api.themoviedb.org/3/discover/movie?api_key=<<api_key>>&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1
    @GET("discover/movie")
    Call<moviesresponse> getPopularMovies(@Query("api_key") String apikey, @Query("language") String language,@Query("include_adult") String adult,
                                          @Query("sort_by") String popularity,@Query("with_original_language") String org);

    @GET("discover/movie")
    Call<moviesresponse> getTopratedMovies(@Query("api_key") String apikey,  @Query("language") String language,@Query("include_adult") String adult,
                                           @Query("sort_by") String popularity,@Query("with_original_language") String org);

    @GET("movie/{movie_id}/videos")
    Call<TrailerResponse> getMovieTrailer(@Path("movie_id") int id, @Query("api_key") String apiKey);

    //https://api.themoviedb.org/3/search/multi?api_key=0bbcf16abf3d0642d8f0a83deed6676b&language=en-US&page=1&include_adult=false&query=a
    @GET("search/movie")
    Call<moviesresponse> getsearchMovies(@Query("api_key") String apikey, @Query("language") String language,@Query("include_adult") String adult,
                                        @Query("with_original_language") String org,@Query("sort_by") String popularit,@Query("query") String searchquery);
}
