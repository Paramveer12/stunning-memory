package com.awesome.Moviedb;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.awesome.Moviedb.adapter.moviesadapter;
import com.awesome.Moviedb.api.client;
import com.awesome.Moviedb.api.service;
import com.awesome.Moviedb.model.movie;
import com.awesome.Moviedb.model.moviesresponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Search extends AppCompatActivity {

    EditText searchView;
    ImageView searchbtn;
    private RecyclerView recycler;
    String query;
    private moviesadapter adapter;
    private List<movie> list;
    private static Context context;
    ProgressDialog progressDialog;
    public static final String LOG_TAG = moviesadapter.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = findViewById(R.id.search_bar);
        searchbtn = findViewById(R.id.searchbtn);
        recycler = findViewById(R.id.recyclersearch);
        Search.context = getApplicationContext();
        searchView.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query = searchView.getText().toString();
                initViews();

            }
        });

    }

    public void initViews() {
        progressDialog = new ProgressDialog(Search.this);
        progressDialog.setMessage("Fetching.....");
        progressDialog.setCancelable(false);
        progressDialog.show();

        if (ContextCompat.checkSelfPermission(Search.this, Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Ask for permision
            ActivityCompat.requestPermissions(Search.this, new String[]{Manifest.permission.INTERNET}, 1);
        } else {


            list = new ArrayList<>();
            adapter = new moviesadapter(Search.context, list);

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

                recycler.setLayoutManager(new GridLayoutManager(Search.this,2));
            } else {
                recycler.setLayoutManager(new GridLayoutManager(Search.this,4));
            }
            recycler.setItemAnimator(new DefaultItemAnimator());
            recycler.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            loadJSON();
        }
    }

    public void loadJSON() {
        try {
            if (BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty()) {
                Toast.makeText(Search.this, "Please obtain API key", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                return;
            }
            Call<moviesresponse> call;
            client Client = new client();
            service apiservice = Client.getClients().create(service.class);
            call = apiservice.getsearchMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN,"en-US","false","en","popularity.desc", query);

            call.enqueue(new Callback<moviesresponse>() {
                @Override
                public void onResponse(Call<moviesresponse> call, Response<moviesresponse> response) {
                    List<movie> movies = response.body().getResults();
                    recycler.setAdapter(new moviesadapter(Search.this, movies));
                    recycler.smoothScrollToPosition(0);
//                    if (refreshLayout.isRefreshing()) {
//                        refreshLayout.setRefreshing(false);
//                    }
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<moviesresponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(Search.this, "Error Feaching data", Toast.LENGTH_SHORT).show();
                }
            });


        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(Search.this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }


}
