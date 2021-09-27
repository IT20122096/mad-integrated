package com.example.madmini.it20250638;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.madmini.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddLaptopActivity extends AppCompatActivity {
    private TextInputEditText edtBrandName,edtProcessor,edtHdd,edtVga,edtOther,edtPrice,edtRam,edtSsd,edtImage;
    private Button btnAdd, btnClear;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String lapId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.it20250638_activity_add_laptop);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnClear = (Button) findViewById(R.id.btnClear);

        edtBrandName =  findViewById(R.id.edtBrandName);
        edtProcessor =  findViewById(R.id.edtProcessor);
        edtHdd =  findViewById(R.id.edtHdd);
        edtVga =  findViewById(R.id.edtVga);
        edtOther =  findViewById(R.id.edtOther);
        edtPrice =  findViewById(R.id.edtPrice);
        edtRam =  findViewById(R.id.edtRam);
        edtSsd =  findViewById(R.id.edtSsd);
        edtImage =  findViewById(R.id.edtImage);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Laptops");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtBrandName.getText().toString();
                String price = edtPrice.getText().toString();
                String processor = edtProcessor.getText().toString();
                String vga = edtVga.getText().toString();
                String other = edtOther.getText().toString();
                String ram = edtRam.getText().toString();
                String ssd = edtSsd.getText().toString();
                String hdd = edtHdd.getText().toString();
                String image = edtImage.getText().toString();

                lapId = name;
                LaptopRVModel laptopRVModel = new LaptopRVModel(name,price,processor,vga,other,ram,ssd,hdd,image,lapId);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child(lapId).setValue(laptopRVModel);
                        Log.i("info", "Laptop " + laptopRVModel.getName() + " Added..");
                        Toast.makeText(AddLaptopActivity.this,"Laptop " + laptopRVModel.getName() + " Added..",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddLaptopActivity.this, ViewActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(AddLaptopActivity.this,"Error is.."+ error.toString(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



    }
}