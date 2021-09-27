package com.example.madmini.it20122096;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.madmini.R;
import com.example.madmini.it20122096.RcvAdapters.Quotation_List_Adapter;
import com.example.madmini.it20122096.RcvAdapters.Quotation_item_Rcv_Adapter;
import com.example.madmini.it20122096.models.Q_Items;
import com.example.madmini.it20122614.CartActivity;
import com.example.madmini.it20122614.Payment;
import com.example.madmini.it20122614.ProfileActivity;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DisplayQuotationList extends AppCompatActivity {

    RecyclerView list_rcv;
    TextView total;
    Quotation_List_Adapter quotation_list_adapter;
    Payment payment;
    String q_id;
    ImageButton home_btn,cart_btn,profile_btn_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.it20122096_activity_display_quotation_list);
        ActionBar actionBar=getSupportActionBar();
        getSupportActionBar().setTitle("Quotation List");

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

        list_rcv=(RecyclerView) findViewById(R.id.list_rcv);
        list_rcv.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        if (intent.getExtras()!=null){
            payment=(Payment) intent.getSerializableExtra("payment");
            q_id=payment.getQuotation_id().toString();
        }

        FirebaseRecyclerOptions<Q_Items> options =
                new FirebaseRecyclerOptions.Builder<Q_Items>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Quotation_Items")
                                .orderByChild("quotation_id").equalTo(q_id), Q_Items.class)
                        .build();
        quotation_list_adapter= new Quotation_List_Adapter(options);
        list_rcv.setAdapter(quotation_list_adapter);

        total=(TextView) findViewById(R.id.total);
        System.out.println(q_id);
        FirebaseDatabase.getInstance().getReference().child("Quotation").orderByChild("id").equalTo(q_id)
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        total.setText(snapshot.child("total").getValue()+"");
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


    }
    @Override
    protected void onStart() {
        super.onStart();
        quotation_list_adapter.startListening();

    }
    @Override
    protected void onStop() {
        super.onStop();
        quotation_list_adapter.stopListening();
    }
}