package com.example.deliverymanagerslist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {

    EditText name,company,tel,date,image;
    Button btnAdd,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name = (EditText) findViewById(R.id.txtName);
        company = (EditText) findViewById(R.id.txtCom);
        tel = (EditText) findViewById(R.id.txtTel);
        date = (EditText)findViewById(R.id.txtDate);
        image = (EditText)  findViewById(R.id.txtImageUrl);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnBack = (Button) findViewById(R.id.btnBack);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               insertData();
               clearAll();

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });


    }
private void insertData()
{
    Map<String,Object> map = new HashMap<>();
    map.put("name",name.getText().toString());
    map.put("company",company.getText().toString());
    map.put("tel",tel.getText().toString());
    map.put("date",date.getText().toString());
    map.put("image",image.getText().toString());

    FirebaseDatabase.getInstance().getReference().child("manager").push()
            .setValue(map)
            .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(AddActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddActivity.this, "Error while inserction", Toast.LENGTH_SHORT).show();

                }
            });


}
        private  void clearAll()
        {
         name.setText("");
         company.setText("");
         tel.setText("");
         date.setText("");
         image.setText("");

        }
}