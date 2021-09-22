package com.example.madmini.it20122096;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.madmini.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Create_Quotation extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner cat_spinner;
    private Spinner product_spinner;
    String cat;
    ArrayList<String> product =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quotation);
        ActionBar act = getSupportActionBar();
        getSupportActionBar().setTitle("Create Your Quotation");

        cat_spinner=(Spinner) findViewById(R.id.cat_spinner);
        product_spinner=(Spinner) findViewById(R.id.product_spinner);
        cat_spinner.setOnItemSelectedListener(this);

        String[] categories =getResources().getStringArray(R.array.Categories);
        ArrayAdapter arrayAdapter =new ArrayAdapter(this, android.R.layout.simple_spinner_item,categories);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cat_spinner.setAdapter(arrayAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if(adapterView.getId() ==R.id.cat_spinner){
            cat=adapterView.getItemAtPosition(i).toString();

            System.out.println(cat);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}