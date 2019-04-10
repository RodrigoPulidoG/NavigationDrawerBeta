package com.example.rodpro.navigationdrawer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    private List<String> names;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private int counter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        names = this.getAllNames();
        recyclerView = findViewById(R.id.nav_recycler);
        layoutManager = new LinearLayoutManager(this);
        layoutManager = new GridLayoutManager(this, 2);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);

        adapter = new MyRecyclerViewAdapter(names, R.layout.recycler_view_item, new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String name, int position) {
                //Toast.makeText(RecyclerActivity.this,name+" - "+position,Toast.LENGTH_SHORT).show();
                deleteName(position);
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
                this.addName(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addName(int position) {
        names.add(position, "New name "+(++counter));
        adapter.notifyItemInserted(position);
        layoutManager.scrollToPosition(position);
    }

    private void deleteName(int position){
        names.remove(position);
        adapter.notifyItemRemoved(position);
    }

    private List<String> getAllNames(){
        return new ArrayList<String>(){{
            add("nombreUno");
            add("nombreDos");
            add("nombreTres");
            add("nombreCuatro");
            add("nombreCinco");
        }};
    }
}
