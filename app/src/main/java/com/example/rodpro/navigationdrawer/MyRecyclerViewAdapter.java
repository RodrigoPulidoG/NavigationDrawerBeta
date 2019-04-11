package com.example.rodpro.navigationdrawer;



import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import Modelos.Movie;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>{

    private List<Movie> movies;
    private int layout;
    private OnItemClickListener itemClickListener;

    private Context context;

    public MyRecyclerViewAdapter (List<Movie> names, int layout, OnItemClickListener listener){
        this.movies = names;
        this.layout = layout;
        this.itemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        context = parent.getContext();
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(movies.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewname;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewname=itemView.findViewById(R.id.texto);
            imageView = itemView.findViewById(R.id.imageView);
            //this.textViewname = itemView.findViewById(R.id.textViewName);
        }

        public void bind(final Movie movie, final OnItemClickListener listener){
            //Procesamos los datos a renderizar
            textViewname.setText(movie.getName());
            Picasso.with(context).load(movie.getImage()).fit().into(imageView);
            //imageView.setImageResource(movie.getImage());

            //this.textViewname.setText(name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(movie, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Movie movie,int position);
    }
}
