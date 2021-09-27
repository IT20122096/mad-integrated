package com.example.madmini.it20122096;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.madmini.R;
import com.example.madmini.it20122096.RcvAdapters.Build_Order_Rcv_Adapter;
import com.example.madmini.it20122614.CartActivity;
import com.example.madmini.it20122614.Payment;
import com.example.madmini.it20122614.ProfileActivity;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class PartOrders extends AppCompatActivity implements Build_Order_Rcv_Adapter.selectedOrder{

    RecyclerView order_rcv;
    Build_Order_Rcv_Adapter rcv_adapter;
    ImageButton home_btn,cart_btn,profile_btn_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.it20122096_activity_pc_build_orders);

        ActionBar actionBar =getSupportActionBar();
        getSupportActionBar().setTitle("Spare Part Orders");

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

        order_rcv=(RecyclerView) findViewById(R.id.order_rcv3);
        order_rcv.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Payment> options=
                new FirebaseRecyclerOptions.Builder<Payment>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("OrdersTable").orderByChild("itemType").equalTo("spare part"),Payment.class)
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
    public void selectedOrder(Payment payment) {
        startActivity(new Intent(PartOrders.this, Order_Details.class).putExtra("Payment",payment));
    }
}