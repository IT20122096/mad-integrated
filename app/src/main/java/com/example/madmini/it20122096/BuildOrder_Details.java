package com.example.madmini.it20122096;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madmini.R;
import com.example.madmini.it20122096.RcvAdapters.Quotation_List_Adapter;
import com.example.madmini.it20122096.models.Orders;
import com.example.madmini.it20122096.models.Q_Items;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;

public class BuildOrder_Details extends AppCompatActivity {

    Orders orders;
    TextView od_name,od_address,od_pno,od_amount,od_date;
    Button slip_btn, down_btn;
    String q_id;
    Quotation_List_Adapter quotation_list_adapter;
    RecyclerView q_items;
    TextView tot;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oreder_details);

        od_name=(TextView)findViewById(R.id.od_name);
        od_address=(TextView)findViewById(R.id.od_address);
        od_pno=(TextView)findViewById(R.id.od_pno);
        od_amount=(TextView)findViewById(R.id.od_amount);
        od_date=(TextView)findViewById(R.id.od_date);

        Intent intent =getIntent();
        if(intent.getExtras()!=null){
            orders = (Orders) intent.getSerializableExtra("Orders");
        }
        od_name.setText(orders.getName());
        od_address.setText(orders.getAddress());
        od_amount.setText(Double.toString(orders.getTotal())+"0");
        od_pno.setText(orders.getPhone_num());
        od_date.setText(orders.getDate());
        q_id=orders.getQuotation_id();

        slip_btn=(Button) findViewById(R.id.slip_btn);
        slip_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              final DialogPlus dialogPlus =DialogPlus.newDialog(BuildOrder_Details.this)
                        .setContentHolder(new ViewHolder(R.layout.slip_display_dialogplus))
                        .setExpanded(true,1020)
                        .create();
                View view1 =dialogPlus.getHolderView();

                Button download =view1.findViewById(R.id.download);
                ImageView slip_img =view1.findViewById(R.id.slip_img);


                dialogPlus.show();
                download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(),q_id,Toast.LENGTH_SHORT).show();
                        dialogPlus.dismiss();
                    }
                });
            }
        });


        down_btn=(Button) findViewById(R.id.down_btn);
        down_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus =DialogPlus.newDialog(BuildOrder_Details.this)
                        .setContentHolder(new ViewHolder(R.layout.display_quotation))
                        .setExpanded(true,1300)
                        .create();
                View view2 =dialogPlus.getHolderView();




                tot=(TextView)view2.findViewById(R.id.q_tot_view);
                FirebaseDatabase.getInstance().getReference().child("Quotation").orderByChild("id").equalTo(q_id)
                        .addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                tot.setText("Total : LKR."+snapshot.child("total").getValue().toString()+".00");
                                System.out.println(q_id);


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

                dialogPlus.show();



            }
        });




    }



}