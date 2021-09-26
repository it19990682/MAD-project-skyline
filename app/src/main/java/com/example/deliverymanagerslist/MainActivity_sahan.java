package com.example.deliverymanagerslist;
//pachage iports
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
public class MainActivity_sahan extends AppCompatActivity {

    RecyclerView recyclerView;
    com.example.deliverymanagerslist.MainAdapter_sahan mainAdapter;

    FloatingActionButton floatingActionButton;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sahan);

        recyclerView = (RecyclerView)findViewById(R.id.rv_sahan);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<com.example.deliverymanagerslist.MainModel_sahan> options =
                new FirebaseRecyclerOptions.Builder<com.example.deliverymanagerslist.MainModel_sahan>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("supplier"), com.example.deliverymanagerslist.MainModel_sahan.class)
                        .build();

        mainAdapter = new com.example.deliverymanagerslist.MainAdapter_sahan(options);
        recyclerView.setAdapter(mainAdapter);

        floatingActionButton= (FloatingActionButton) findViewById(R.id.floatingActionButton_sahan);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), com.example.deliverymanagerslist.AddActivity_sahan.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_sahan,menu);
        MenuItem item = menu.findItem(R.id.search_sahan);
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

    private void txtSearch(String str)
    {

        FirebaseRecyclerOptions<com.example.deliverymanagerslist.MainModel_sahan> options =
                new FirebaseRecyclerOptions.Builder<com.example.deliverymanagerslist.MainModel_sahan>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("supplier").orderByChild("name").startAt(str).endAt(str+"~"), com.example.deliverymanagerslist.MainModel_sahan.class)
                        .build();

        mainAdapter = new com.example.deliverymanagerslist.MainAdapter_sahan(options);
        mainAdapter.startListening();
        recyclerView.setAdapter(mainAdapter);
    }
}