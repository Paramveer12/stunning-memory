package com.awesome.Moviedb.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.awesome.Moviedb.Details;
import com.awesome.Moviedb.R;
import com.awesome.Moviedb.model.movie;

import java.util.List;

public class moviesadapter extends RecyclerView.Adapter<moviesadapter.MyViewHolder> {

    private Context mContext;
    private List<movie> moviesList;

    public moviesadapter(Context mContext,List<movie> moviesList){
        this.mContext = mContext;
        this.moviesList =moviesList;
    }
    @Override
    public moviesadapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup,int i){
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.moviecard,viewGroup,false);

        return new MyViewHolder(view);
    }

    @Override
    public  void onBindViewHolder(final moviesadapter.MyViewHolder viewHolder,int i){

        viewHolder.title.setText(moviesList.get(i).getOrigionaltitle());
        String vote = Double.toString(moviesList.get(i).getVoteaverage());
        viewHolder.userratings.setText(vote);

        Glide.with(mContext)
                .load(moviesList.get(i).getPosterpath())
                .placeholder(R.drawable.load)
                .into(viewHolder.thumbnail);
    }

    @Override
    public int getItemCount(){
        return moviesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView title,userratings;
        public ImageView thumbnail;

        public MyViewHolder(View view){
            super(view);
            title = view.findViewById(R.id.title);
            userratings = view.findViewById(R.id.userreview);
            thumbnail = view.findViewById(R.id.thumbnail);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        movie clickedDataItem = moviesList.get(pos);
                        Intent intent = new Intent(mContext, Details.class);
                        intent.putExtra("id",moviesList.get(pos).getId());
                        intent.putExtra("original_title",moviesList.get(pos).getOrigionaltitle());
                        intent.putExtra("poster_path",moviesList.get(pos).getPosterpath());
                        intent.putExtra("overview",moviesList.get(pos).getOverview());
                        intent.putExtra("vote_average",moviesList.get(pos).getVoteaverage());
                        intent.putExtra("release_date",moviesList.get(pos).getReleasedate());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        Toast.makeText(view.getContext(), "You Clicked "+clickedDataItem.getOrigionaltitle(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

}
