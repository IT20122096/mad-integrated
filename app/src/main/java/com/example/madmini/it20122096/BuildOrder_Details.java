package com.example.madmini.it20122096;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
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
import com.example.madmini.it20122096.models.Orders;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

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
        setContentView(R.layout.it20122096_activity_oreder_details);

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
                        .setContentHolder(new ViewHolder(R.layout.it20122096_slip_display_dialogplus))
                        .setExpanded(true,1020)
                        .create();
                View view1 =dialogPlus.getHolderView();

                Button download =view1.findViewById(R.id.download);
                Uri uri =Uri.parse(orders.getImage());


                ImageView slip_img =view1.findViewById(R.id.slip_img);
                Glide.with(BuildOrder_Details.this).load(orders.getImage()).into(slip_img);
                System.out.println(orders.getImage());


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
        down_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus =DialogPlus.newDialog(BuildOrder_Details.this)
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

        request.setTitle("Payment slip for orderID-"+orders.getId());
        request.setDescription("Androind dada down;]load");
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"/images/"+"/"+"slip-"+orders.getId()+".png");
        request.setMimeType("*/*");
        downloadManager.enqueue(request);

    }


}