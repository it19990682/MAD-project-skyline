package com.example.deliverymanagerslist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity_dash extends AppCompatActivity {

    Button delivery,supplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dash);

        delivery = findViewById(R.id.button_delivery);
        supplier = findViewById(R.id.button_supplier);

        supplier.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity_sahan.class);
            startActivity(intent);
        });


    }
}