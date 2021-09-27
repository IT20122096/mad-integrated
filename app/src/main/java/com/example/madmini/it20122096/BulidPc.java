package com.example.madmini.it20122096;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.madmini.R;
import com.example.madmini.it20122096.RcvAdapters.Quotation_Rcv_Adapter;
import com.example.madmini.it20122096.models.Quotations;
import com.example.madmini.it20122614.CartActivity;
import com.example.madmini.it20122614.PaymentActivity;
import com.example.madmini.it20122614.ProfileActivity;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class BulidPc extends AppCompatActivity implements CreateDialog.DialogListner,Quotation_Rcv_Adapter.SelectedQuotation {

    ImageButton home_btn,cart_btn,profile_btn_2;
    RecyclerView recyclerView;
    Quotation_Rcv_Adapter rcv_adapter;
    Button create_btn,delete;

    String uID= FirebaseAuth.getInstance().getCurrentUser().getUid().toString();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.it20122096_activity_bulid_pc);
        ActionBar act = getSupportActionBar();
        getSupportActionBar().setTitle("Build Your PC");



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

        //Create New Quotation
        create_btn=(Button)findViewById(R.id.create_btn);
        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });


        //Recycle View
        recyclerView=(RecyclerView) findViewById(R.id.rcv1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Quotations> options =
                new FirebaseRecyclerOptions.Builder<Quotations>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Quotation").orderByChild("user_id").equalTo(uID), Quotations.class)
                        .build();
        rcv_adapter= new Quotation_Rcv_Adapter(options,getApplicationContext(),this::selectedQuotation);
        recyclerView.setAdapter(rcv_adapter);




    }

    private void openDialog() {
        CreateDialog createDialog =new CreateDialog();
        createDialog.show(getSupportFragmentManager(),"create dialog");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void applyText(String name) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        String qID= dtf.format(now);

        Map<String, Object> map =new HashMap<>();
                map.put("name",name);
                map.put("total",0);
                map.put("id",qID);
                map.put("user_id",uID);
        FirebaseDatabase.getInstance().getReference().child("Quotation").push().setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(BulidPc.this,name+"Created Successfully!",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(BulidPc.this,"Couldn't Create Your Quotation",Toast.LENGTH_LONG).show();
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

    @Override
    public void selectedQuotation(Quotations quotaions, String type) {

        if (type=="view"){
            startActivity(new Intent(BulidPc.this, Quotation_Items.class).putExtra("quotation",quotaions));
        }
        else if (type=="pay"){
            startActivity(new Intent(BulidPc.this, PaymentActivity.class).putExtra("quotation",quotaions));
        }
        else {}

    }
}