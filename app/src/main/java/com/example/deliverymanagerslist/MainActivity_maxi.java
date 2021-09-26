package com.example.deliverymanagerslist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity_maxi extends AppCompatActivity {

    RecyclerView recyclerView;
    MainAdapter_maxi mainAdapterMaxi;

    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<MainModel_maxi> options =
                new FirebaseRecyclerOptions.Builder<MainModel_maxi>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("manager"), MainModel_maxi.class)
                        .build();

        mainAdapterMaxi = new MainAdapter_maxi(options);
        recyclerView.setAdapter(mainAdapterMaxi);

        floatingActionButton = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(getApplicationContext(), AddActivity_maxi.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mainAdapterMaxi.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapterMaxi.startListening();
    }
//search item
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtSearch(query);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
    private void txtSearch(String str){

        FirebaseRecyclerOptions<MainModel_maxi> options =
                new FirebaseRecyclerOptions.Builder<MainModel_maxi>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("manager").orderByChild("name").startAt(str).endAt(str+"~"), MainModel_maxi.class)
                        .build();

        mainAdapterMaxi = new MainAdapter_maxi(options);
        mainAdapterMaxi.startListening();
        recyclerView.setAdapter(mainAdapterMaxi);


    }

}