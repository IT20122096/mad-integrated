package com.example.madmini.it20122096;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.madmini.R;
import com.example.madmini.it20122096.RcvAdapters.Build_Order_Rcv_Adapter;
import com.example.madmini.it20122096.models.Orders;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Pc_Build_Orders extends AppCompatActivity implements Build_Order_Rcv_Adapter.selectedOrder {

    RecyclerView order_rcv;
    Build_Order_Rcv_Adapter rcv_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.it20122096_activity_pc_build_orders);

        ActionBar actionBar =getSupportActionBar();
        getSupportActionBar().setTitle("PC Build Orders");

        order_rcv=(RecyclerView) findViewById(R.id.order_rcv);
        order_rcv.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Orders> options=
                new FirebaseRecyclerOptions.Builder<Orders>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Orders"),Orders.class)
                .build();
        rcv_adapter=new Build_Order_Rcv_Adapter(options,this::selectedOrder);
        order_rcv.setAdapter(rcv_adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        rcv_adapter.startListening();

    }
    @Override
    protected void onStop() {
        super.onStop();
        rcv_adapter.stopListening();
    }

    @Override
    public void selectedOrder(Orders orders) {
        startActivity(new Intent(Pc_Build_Orders.this, BuildOrder_Details.class).putExtra("Orders",orders));
    }
}