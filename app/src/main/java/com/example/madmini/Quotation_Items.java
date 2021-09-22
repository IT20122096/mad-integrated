package com.example.madmini;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.madmini.it20122096.models.Quotations;

public class Quotation_Items extends AppCompatActivity {

    String q_id;
    TextView q_total;
    RecyclerView item_rcv;
    Button q_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotation_items);
        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Quotation Items");

        q_total=(TextView)findViewById(R.id.q_tot);
        q_add=(Button)findViewById(R.id.q_add);
        item_rcv=(RecyclerView)findViewById(R.id.item_rcv);
        item_rcv.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();

        if(intent.getExtras()!=null){
            Quotations quotations = (Quotations) intent.getSerializableExtra("quotation");

            q_id=quotations.getId();
        }
    }
}