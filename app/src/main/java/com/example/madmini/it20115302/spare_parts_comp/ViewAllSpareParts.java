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
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.madmini.R;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;

public class ViewAllSpareParts extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    DAOSpareParts dao;
    FirebaseRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_spare_parts);

        // set action bar
        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("View Spare Parts");

        // set resources
        swipeRefreshLayout = findViewById(R.id.spare_parts_swip);
        recyclerView = findViewById(R.id.rv_spare_parts);

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


                SparePartVH vh = (SparePartVH) holder;
                SparePart sparePart = (SparePart) model;

                vh.textName.setText(sparePart.getName());
                vh.textDesc.setText(sparePart.getDesc());
                vh.textPrice.setText("Rs. " + (Double.toString(sparePart.getUnitPrice())) );
                vh.textBrandModel.setText("Brand : " + sparePart.getBrand()+" | Model : " + sparePart.getModel());

                vh.textOption.setOnClickListener( view -> {
                    PopupMenu popupMenu = new PopupMenu(ViewAllSpareParts.this, vh.textOption);
                    popupMenu.inflate(R.menu.option_spare_parts_menu);
                    popupMenu.setOnMenuItemClickListener( menuItem -> {
                        switch (menuItem.getItemId()){
                            case R.id.menu_edit:
                                Intent intent = new Intent(ViewAllSpareParts.this, UpdateSparePart.class);
                                intent.putExtra("EDIT", sparePart);
                                startActivity(intent);
                                break;
                            case R.id.menu_remove:
                                new AlertDialog.Builder(ViewAllSpareParts.this)
                                        .setTitle("Delete Confirmation")
                                        .setMessage("Do you really want to Delete?")
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int whichButton) {
                                                DAOSpareParts dao = new DAOSpareParts();
                                                dao.remove(sparePart.getKey()).addOnSuccessListener( success -> {
                                                    Toast.makeText(ViewAllSpareParts.this, "Spare Part Removed", Toast.LENGTH_SHORT).show();
                                                }).addOnFailureListener( error -> {
                                                    Toast.makeText(ViewAllSpareParts.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                                                });
                                            }})
                                        .setNegativeButton(android.R.string.no, null).show();

                                break;
                        }
                        return false;
                    });
                    popupMenu.show();
                });
            }

            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(ViewAllSpareParts.this)
                        .inflate(R.layout.layout_item_spare_part, parent, false);
                return new SparePartVH(view);
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