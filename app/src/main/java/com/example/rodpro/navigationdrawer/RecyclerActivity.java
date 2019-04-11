package com.example.rodpro.navigationdrawer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Modelos.Movie;

public class RecyclerActivity extends AppCompatActivity {

    private List<Movie> movies;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private int counter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        movies = this.getAllMovies();
        recyclerView = findViewById(R.id.nav_recycler);
        layoutManager = new LinearLayoutManager(this);
        //layoutManager = new GridLayoutManager(this, 2);
        //layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);

        adapter = new MyRecyclerViewAdapter(movies, R.layout.recycler_view_item, new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie, int position) {
                Toast.makeText(RecyclerActivity.this,movie+" - "+position,Toast.LENGTH_SHORT).show();
                removeMovie(position);
            }
        });

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recycler_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.add_name:
                Toast.makeText(RecyclerActivity.this,"Add",Toast.LENGTH_SHORT).show();
                this.addMovie(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addMovie(int position) {
        movies.add(position, new Movie("New Image " +(++counter),R.drawable.movienew));
        adapter.notifyItemInserted(position);
        layoutManager.scrollToPosition(position);
    }

    private void removeMovie(int position){
        Toast.makeText(RecyclerActivity.this,"del",Toast.LENGTH_SHORT).show();
        movies.remove(position);
        adapter.notifyItemRemoved(position);
    }

    private List<Movie> getAllMovies(){
        return new ArrayList<Movie>(){{
            add(new Movie("Lechuga Uno",R.drawable.movie1));
            add(new Movie("Lechuga Dos",R.drawable.movie2));
            add(new Movie("Lechuga Tres",R.drawable.movie3));
            add(new Movie("Lechuga Cuatro",R.drawable.movie4));
        }};
    }
}
