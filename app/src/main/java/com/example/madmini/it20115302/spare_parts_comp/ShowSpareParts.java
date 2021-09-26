package com.example.madmini.it20115302.spare_parts_comp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.madmini.R;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;

public class ShowSpareParts extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    DAOSpareParts dao;
    FirebaseRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_spare_parts);

        // set action bar
        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Show Spare Parts - Client");

        // set resources
        swipeRefreshLayout = findViewById(R.id.spare_parts_swip_client);
        recyclerView = findViewById(R.id.rv_spare_parts_client);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        dao = new DAOSpareParts();

        FirebaseRecyclerOptions<SparePart> option =
                new FirebaseRecyclerOptions.Builder<SparePart>()
                        .setQuery(dao.get(), new SnapshotParser<SparePart>() {
                            @NonNull
                            @Override
                            public SparePart parseSnapshot(@NonNull DataSnapshot snapshot) {
                                SparePart sparePart = snapshot.getValue(SparePart.class);
                                sparePart.setKey(snapshot.getKey());
                                return sparePart;
                            }
                        }).build();

        adapter = new FirebaseRecyclerAdapter(option) {

            @Override
            protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull Object model) {


                SparePartVHClient vh = (SparePartVHClient) holder;
                SparePart sparePart = (SparePart) model;

                String price = Double.toString(sparePart.getUnitPrice());
                System.out.println("Price :" + price);

                vh.textNameClient.setText(sparePart.getName());
                vh.textDescClient.setText(sparePart.getDesc());
                vh.textPriceClient.setText("Rs. " + price );
                vh.textBrandModelClient.setText("Brand : " + sparePart.getBrand()+" | Model : " + sparePart.getModel());

                vh.btnAddCard.setOnClickListener( view -> {

                    new AlertDialog.Builder(ShowSpareParts.this)
                            .setTitle("Confirmation")
                            .setMessage("Do you want to add to cart ?")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

                                    Cart cart = new Cart();
                                    // TODO: ask from user management guy
                                    cart.setUserName("demo_user");
                                    cart.setItemId(sparePart.getKey());
                                    cart.setItemType("spare part");
                                    cart.setPrice(sparePart.getUnitPrice());

                                    Intent i = new Intent(ShowSpareParts.this, AddCartSparePart.class);
                                    i.putExtra("CART", cart);
                                    startActivity(i);

                                    finish();


                                }})
                            .setNegativeButton(android.R.string.no, null).show();

                });

            }

            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(ShowSpareParts.this)
                        .inflate(R.layout.layout_item_spare_part_client, parent, false);
                return new SparePartVHClient(view);
            }

            @Override
            public void onDataChanged() {
                // Toast.makeText(ViewAllSpareParts.this, "Data changed", Toast.LENGTH_SHORT).show();
            }

        };

        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}