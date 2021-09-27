package com.example.madmini.it20250638;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.madmini.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class DisplayLaptopActivity extends AppCompatActivity {
    private TextView edtBrandName,edtProcessor,edtHdd,edtVga,edtOther,edtPrice,edtRam,edtSsd;
    private ImageView edtImage;
    private Button btnAddCart;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String lapId;
    private LaptopRVModel laptopRVModel;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.it20250638_activity_display_laptop);

        btnAddCart = (Button) findViewById(R.id.btnAddCart);
        edtBrandName =  findViewById(R.id.BrandName);
        edtProcessor =  findViewById(R.id.Processor);
        edtHdd =  findViewById(R.id.Hdd);
        edtVga =  findViewById(R.id.Vga);
        edtOther =  findViewById(R.id.Other);
        edtPrice =  findViewById(R.id.Price);
        edtRam =  findViewById(R.id.Ram);
        edtSsd =  findViewById(R.id.Ssd);
        edtImage =  findViewById(R.id.image);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Laptops");

        laptopRVModel = getIntent().getParcelableExtra("laptop");
        if(laptopRVModel != null){
            edtBrandName.setText(laptopRVModel.getName());
            edtProcessor.setText(laptopRVModel.getProcessor());
            edtHdd.setText(laptopRVModel.getHdd());
            edtVga.setText(laptopRVModel.getVga());
            edtOther.setText(laptopRVModel.getOther());
            edtPrice.setText(laptopRVModel.getPrice());
            edtRam.setText(laptopRVModel.getRam());
            edtSsd.setText(laptopRVModel.getSsd());
            Picasso.get().load(laptopRVModel.getImage()).into(edtImage);
            lapId = laptopRVModel.getName();
        }
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Laptops").child(lapId);

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DisplayLaptopActivity.this, ViewActivity.class);
                i.putExtra("laptop",laptopRVModel);
                startActivity(i);
            }
        });
    }
}