package com.awesome.Moviedb.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class moviesresponse {
    @SerializedName("page")
    private int pages;
    @SerializedName("results")
    private List<movie> results;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;

    public int getPages(){
        return pages;
    }
    public void setPages(int pages){
        this.pages=pages;
    }
    public int getTotalResults(){
        return totalResults;
    }
    public void setTotalResults(int totalResults){
        this.totalResults=totalResults;
    }
    public int getTotalPages(){
        return totalPages;
    }
    public void setTotalPages(int totalPages){
        this.totalPages=totalPages;
    }
    public List<movie> getResults(){
        return results;
    }
    public void setResults(List<movie> results){
        this.results=results;
    }


}
