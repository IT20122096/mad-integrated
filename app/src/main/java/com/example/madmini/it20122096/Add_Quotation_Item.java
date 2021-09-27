package com.example.madmini.it20122096;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.madmini.R;
import com.example.madmini.it20115302.spare_parts_comp.SparePart;
import com.example.madmini.it20122096.RcvAdapters.Add_Part_RCV_Adapter;
import com.example.madmini.it20122096.models.Parts;
import com.example.madmini.it20122614.CartActivity;
import com.example.madmini.it20122614.ProfileActivity;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Add_Quotation_Item extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner cat_spinner1;
    String cat;
    String q_id;
    Add_Part_RCV_Adapter rcv_adapter;
    RecyclerView part_rcv;
    ImageButton home_btn,cart_btn,profile_btn_2;
    ArrayAdapter <CharSequence> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.it20122096_activity_add_quotation_item);
        ActionBar actionBar=getSupportActionBar();
        getSupportActionBar().setTitle("Add Items ");

        //Home Button Navigation
        home_btn=(ImageButton) findViewById(R.id.home_btn);
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DashBoard.class));

            }
        });
        cart_btn=(ImageButton)findViewById(R.id.cart_btn);
        cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CartActivity.class));
            }
        });
        profile_btn_2=(ImageButton)findViewById(R.id.profile_btn_2);
        profile_btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            }
        });

        cat_spinner1=(Spinner) findViewById(R.id.cat_spinner1);
        cat_spinner1.setOnItemSelectedListener(this);
        part_rcv=(RecyclerView)findViewById(R.id.part_rcv);
        part_rcv.setLayoutManager(new LinearLayoutManager(this));

        Intent intent=getIntent();
        q_id=intent.getStringExtra("id");
        System.out.println(q_id);


        arrayAdapter =ArrayAdapter.createFromResource(this,R.array.menu_items_labels,R.layout.it20122096_spinner_text);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cat_spinner1.setAdapter(arrayAdapter);




        System.out.println(cat);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if(adapterView.getId() ==R.id.cat_spinner1){
            cat=adapterView.getItemAtPosition(i).toString();
        }
        FirebaseRecyclerOptions<SparePart> options =
                new FirebaseRecyclerOptions.Builder<SparePart>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("SparePart").orderByChild("model").equalTo(cat)
                                ,SparePart.class)
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