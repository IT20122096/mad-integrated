package com.example.madmini.it20122096;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.madmini.R;
import com.example.madmini.it20122096.RcvAdapters.Quotation_item_Rcv_Adapter;
import com.example.madmini.it20122096.models.Q_Items;
import com.example.madmini.it20122096.models.Quotations;
import com.example.madmini.it20122614.CartActivity;
import com.example.madmini.it20122614.ProfileActivity;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Quotation_Items extends AppCompatActivity {

    String q_id;
    public double tot;
    TextView q_total;
    RecyclerView item_rcv;
    Button q_add;
    Quotation_item_Rcv_Adapter rcv_adapter;
    ImageButton home_btn,cart_btn,profile_btn_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.it20122096_activity_quotation_items);
        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Quotation Items");

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

        q_total=(TextView)findViewById(R.id.q_tot);
        item_rcv=(RecyclerView)findViewById(R.id.item_rcv);
        item_rcv.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        if(intent.getExtras()!=null){
            Quotations quotations = (Quotations) intent.getSerializableExtra("quotation");

            q_id=quotations.getId();
            q_total.setText(tot+"0");

        }

        q_add=(Button)findViewById(R.id.q_add);
        q_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Quotation_Items.this, Add_Quotation_Item.class).putExtra("id",q_id));
            }
        });



        FirebaseRecyclerOptions<Q_Items> options =
                new FirebaseRecyclerOptions.Builder<Q_Items>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Quotation_Items")
                                .orderByChild("quotation_id").equalTo(q_id), Q_Items.class)
                        .build();
        rcv_adapter= new Quotation_item_Rcv_Adapter(options);
        item_rcv.setAdapter(rcv_adapter);


        ChildEventListener query =FirebaseDatabase.getInstance().getReference().child("Quotation_Items").orderByChild("quotation_id").equalTo(q_id)
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            double price=Double.parseDouble(snapshot.child("price").getValue().toString());
                            double quantity=Double.parseDouble(snapshot.child("quantity").getValue().toString());
                            tot=tot+(price*quantity);
                            String total= Double.toString(tot);
                            q_total.setText("LKR "+total+"0");
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                        double price=Double.parseDouble(snapshot.child("price").getValue().toString());
                        double quantity=Double.parseDouble(snapshot.child("quantity").getValue().toString());
                        tot=tot-(price*quantity);

                        String total= Double.toString(tot);
                        q_total.setText(total);
                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



        FirebaseDatabase.getInstance().getReference().child("Quotation").orderByChild("id").equalTo(q_id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot snapshot1: snapshot.getChildren()) {


                            snapshot1.getRef().child("total").setValue(tot);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

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
}