package com.awesome.Moviedb;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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


public class Frag2 extends Fragment {


    RecyclerView recycler;
    View view;
    private SwipeRefreshLayout refreshLayout;
    private moviesadapter adapter;
    private List<movie> list;
    ProgressDialog progressDialog;
    public static final String LOG_TAG = moviesadapter.class.getName();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag2, container, false);
        recycler = view.findViewById(R.id.recycler);
        refreshLayout = view.findViewById(R.id.main_content);
        initViews();

        refreshLayout.setColorSchemeResources(android.R.color.holo_orange_dark);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initViews();
                Toast.makeText(getActivity(), "Movies Refreshed", Toast.LENGTH_SHORT).show();
            }
        });
        return view;

    }


    public void initViews() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Fetching.....");
        progressDialog.setCancelable(false);
        progressDialog.show();

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.INTERNET}, 1);
        } else {


            list = new ArrayList<>();
            adapter = new moviesadapter(getActivity(), list);

            if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

                recycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            } else {
                recycler.setLayoutManager(new GridLayoutManager(getActivity(), 4));
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
                Toast.makeText(getActivity(), "Please obtain API key", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                return;
            }
            Call<moviesresponse> call;
            client Client = new client();
            service apiservice = Client.getClients().create(service.class);
            call = apiservice.getTopratedMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN,"en-US","false","vote_count.desc","en");

            call.enqueue(new Callback<moviesresponse>() {
                @Override
                public void onResponse(Call<moviesresponse> call, Response<moviesresponse> response) {
                    List<movie> movies = response.body().getResults();
                    recycler.setAdapter(new moviesadapter(getActivity(), movies));
                    recycler.smoothScrollToPosition(0);
                    if (refreshLayout.isRefreshing()) {
                        refreshLayout.setRefreshing(false);
                    }
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<moviesresponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(getActivity(), "Error Feaching data", Toast.LENGTH_SHORT).show();
                }
            });


        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
        }

    }


}
