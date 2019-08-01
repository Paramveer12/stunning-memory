package com.awesome.Moviedb.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class movie {

    @SerializedName("poster_path")
    private String posterpath;

    @SerializedName("original_title")
    private String origionaltitle;

    @SerializedName("overview")
    private String overview;

    @SerializedName("genres")
    private List<Integer> genres = new ArrayList<Integer>();

    @SerializedName("release_date")
    private String releasedate;

    @SerializedName("backdrop_path")
    private String backdroppath;

    @SerializedName("popularity")
    private Double popularity;

    @SerializedName("id")
    private Integer id;

    @SerializedName("title")
    private String title;

    @SerializedName("vote_count")
    private Integer vote_count;

    @SerializedName("vote_average")
    private Double voteaverage;

    public movie(String posterpath,Integer vote_count,Double voteaverage,String releasedate,String backdroppath,
                    String origionaltitle, String title, Integer id , String overview,Double popularity,
                    List<Integer> genres){
        this.posterpath =posterpath ;
        this.vote_count = vote_count;
        this.voteaverage = voteaverage;
        this.releasedate= releasedate;
        this.backdroppath = backdroppath;
        this.origionaltitle = origionaltitle;
        this.title = title;
        this.id = id;
        this.overview = overview;
        this.popularity = popularity;
        this.genres = genres;
    }
    String baseImageUrl = "https://image.tmdb.org/t/p/w500";

    public String getPosterpath(){
        return "https://image.tmdb.org/t/p/w500"+posterpath;
    }
    public void setPosterpath(String posterpath){
        this.posterpath = posterpath;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getOverview(){
        return overview;
    }
    public void setOverview(String overview){
        this.overview = overview;
    }
    public String getReleasedate(){
        return releasedate;
    }
    public void setReleasedate(String releasedate){
        this.releasedate = releasedate;
    }
    public Double getVoteaverage(){
        return voteaverage;
    }
    public void setVoteaverage(Double voteaverage){
        this.voteaverage = voteaverage;
    }
    public Integer getVote_count(){
        return vote_count;
    }
    public void setVote_count(Integer vote_count){
        this.vote_count = vote_count;
    }
    public Double getPopularity(){
        return popularity;
    }
    public void setPopularity(Double popularity){
        this.popularity = popularity;
    }
    public List<Integer> getGenres(){
        return genres;
    }
    public void setGenres(List<Integer> genres){
        this.genres = genres;
    }
    public String getOrigionaltitle(){
        return origionaltitle;
    }
    public void setOrigionaltitle(String origionaltitle){
        this.origionaltitle = origionaltitle;
    }
    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }

}
