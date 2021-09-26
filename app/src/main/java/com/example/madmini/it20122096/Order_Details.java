package com.example.madmini.it20122096;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.madmini.R;
import com.example.madmini.it20122096.RcvAdapters.Quotation_List_Adapter;
import com.example.madmini.it20122614.Payment;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

public class Order_Details extends AppCompatActivity {

    Payment payment;
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
        setContentView(R.layout.it20122096_activity_oreder_details);
        ActionBar actionBar =getSupportActionBar();
        getSupportActionBar().setTitle("Order Details");

        od_name=(TextView)findViewById(R.id.od_name);
        od_address=(TextView)findViewById(R.id.od_address);
        od_pno=(TextView)findViewById(R.id.od_pno);
        od_amount=(TextView)findViewById(R.id.od_amount);
        od_date=(TextView)findViewById(R.id.od_date);

        Intent intent =getIntent();
        if(intent.getExtras()!=null){
            payment = (Payment) intent.getSerializableExtra("Payment");

        }

        FirebaseDatabase.getInstance().getReference("AddressBook").child(payment.getuId())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        od_name.setText(snapshot.child("name").getValue().toString());
                        od_address.setText(snapshot.child("address").getValue().toString());
                        od_pno.setText(snapshot.child("cNumber").getValue().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        od_amount.setText(Double.toString(payment.getPrices())+"0");
        od_date.setText(payment.getDate());
        q_id=payment.getQuotation_id();

        slip_btn=(Button) findViewById(R.id.slip_btn);
        slip_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

              final DialogPlus dialogPlus =DialogPlus.newDialog(Order_Details.this)
                        .setContentHolder(new ViewHolder(R.layout.it20122096_slip_display_dialogplus))
                        .setExpanded(true,1020)
                        .create();
                View view1 =dialogPlus.getHolderView();

                Button download =view1.findViewById(R.id.download);
                Uri uri =Uri.parse(payment.getImageURL());


                ImageView slip_img =view1.findViewById(R.id.slip_img);
                Glide.with(Order_Details.this).load(payment.getImageURL()).into(slip_img);
                System.out.println(payment.getImageURL());


                dialogPlus.show();
                download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                     downloadImage(uri);
                        Toast.makeText(getApplicationContext(),q_id,Toast.LENGTH_SHORT).show();
                        dialogPlus.dismiss();
                    }
                });
            }
        });


        down_btn=(Button) findViewById(R.id.down_btn);
        System.out.println(payment.getItemType());
        if(payment.getItemType().toString()=="pc build"){

            down_btn.setVisibility(down_btn.VISIBLE);
        }

        down_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus =DialogPlus.newDialog(Order_Details.this)
                        .setContentHolder(new ViewHolder(R.layout.it20122096_display_quotation))
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

    private void downloadImage(Uri uri) {
        DownloadManager downloadManager =(DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request =new DownloadManager.Request(uri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);

        request.setTitle("Payment slip for orderID-");
        request.setDescription("Androind dada down;]load");
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"/images/"+"/"+"slip-"+".png");
        request.setMimeType("*/*");
        downloadManager.enqueue(request);

    }


}