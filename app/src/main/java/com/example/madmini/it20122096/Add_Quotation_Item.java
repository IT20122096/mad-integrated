package com.example.madmini.it20122096;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.madmini.R;
import com.example.madmini.it20122096.RcvAdapters.Add_Part_RCV_Adapter;
import com.example.madmini.it20122096.models.Parts;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Add_Quotation_Item extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner cat_spinner1;
    String cat;
    String q_id;
    Add_Part_RCV_Adapter rcv_adapter;
    RecyclerView part_rcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.it20122096_activity_add_quotation_item);

        cat_spinner1=(Spinner) findViewById(R.id.cat_spinner1);
        cat_spinner1.setOnItemSelectedListener(this);
        part_rcv=(RecyclerView)findViewById(R.id.part_rcv);
        part_rcv.setLayoutManager(new LinearLayoutManager(this));

        Intent intent=getIntent();
        q_id=intent.getStringExtra("id");
        System.out.println(q_id);

        String[] categories =getResources().getStringArray(R.array.menu_items_labels);
        ArrayAdapter arrayAdapter =new ArrayAdapter(this, android.R.layout.simple_spinner_item,categories);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cat_spinner1.setAdapter(arrayAdapter);




        System.out.println(cat);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if(adapterView.getId() ==R.id.cat_spinner1){
            cat=adapterView.getItemAtPosition(i).toString();
        }
        FirebaseRecyclerOptions<Parts> options =
                new FirebaseRecyclerOptions.Builder<Parts>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Parts").orderByChild("category").equalTo(cat)
                                , Parts.class)
                        .build();
        rcv_adapter= new Add_Part_RCV_Adapter(options,q_id,getApplicationContext());
        part_rcv.setAdapter(rcv_adapter);
        rcv_adapter.startListening();


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        rcv_adapter.stopListening();
    }



}