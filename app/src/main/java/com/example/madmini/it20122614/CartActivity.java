package com.example.madmini.it20122614;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.madmini.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CartActivity extends AppCompatActivity implements CartAdapter.SelectItem {
    private CartAdapter adapter;
    private RecyclerView recyclerView;
    private TextView txtTotalFee, txtEmpty;
    private Button checkOut;
    private ScrollView scrollView;
    private double total;
    DatabaseReference mBase;
    private int count = 0;
    private double tQty = 0;

    CartAdapter cartAdapter;
//    private Order order;
//    FirebaseAuth firebaseAuth;
//    FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.it20122614_activity_cart);
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setTitle("Cart");

        recyclerView = findViewById(R.id.recyclerView);
        txtTotalFee = findViewById(R.id.textViewTotalFee);
        scrollView = findViewById(R.id.scrollView);
        txtEmpty = findViewById(R.id.textViewDeliveryFee);
//        totalQty = findViewById(R.id.textViewFeeId);
//        checkOut = findViewById(R.id.buttonCheckOut);
//        order = new Order();
//        firebaseAuth = FirebaseAuth.getInstance();
//        firebaseUser = firebaseAuth.getCurrentUser();
//        String uid = firebaseUser.getUid();
        mBase = FirebaseDatabase.getInstance().getReference().child("Cart");

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<ItemOrder> options = new FirebaseRecyclerOptions.Builder<ItemOrder>().setQuery(mBase, ItemOrder.class).build();
        adapter = new CartAdapter(options, getApplicationContext(), this::selectItems);
        recyclerView.setAdapter(adapter);



        FirebaseDatabase.getInstance().getReference().child("Cart")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                        double qun=Double.parseDouble(snapshot.child("quantity").getValue().toString());
                        double price=Double.parseDouble(snapshot.child("price").getValue().toString());
                        total=total+price;
                        count = count + 1;

//                        tQty = tQty + qun;

                        txtTotalFee.setText(Double.toString(total));
                        txtEmpty.setText(count+"");
//                        totalQty.setText(Double.toString(tQty));
                        String totalL = Double.toString(total);


                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                        double price=Double.parseDouble(snapshot.child("price").getValue().toString());
                        total=total-price;
                        count = count - 1;
                        txtTotalFee.setText(Double.toString(total));
                        txtEmpty.setText(count+"");

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
//        checkOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(CartActivity.this, PaymentActivity.class));
//            }
//        });





    }
    @Override protected void onStart(){
        super.onStart();
        adapter.startListening();
    }
    protected void OnStop(){
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void selectItems(ItemOrder itemOrder) {

        startActivity(new Intent(CartActivity.this, PaymentActivity.class).putExtra("ordered", itemOrder));

    }
//    private void calculateCard(){
//        double Totals = Math.round((order.getTotal()));
//        txtTotalFee.setText("Rs "+order.getTotal());
//
//    }
}

