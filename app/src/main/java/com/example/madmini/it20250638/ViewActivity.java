package com.example.madmini.it20250638;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madmini.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity implements LaptopRVAdapter.LaptopClickInterface {

    private RecyclerView lapRV;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<LaptopRVModel> laptopRVModelArrayList;
    private RelativeLayout bottomSheetLap;
    private LaptopRVAdapter laptopRVAdapter;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.it20250638_activity_view);
        bottomSheetLap = findViewById(R.id.bottomSheetLap);
        lapRV = findViewById(R.id.lapRV);
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Laptops");
        laptopRVModelArrayList = new ArrayList<>();
        laptopRVAdapter = new LaptopRVAdapter(laptopRVModelArrayList,this,this);
        lapRV.setLayoutManager(new LinearLayoutManager(this));
        lapRV.setAdapter(laptopRVAdapter);

        getAllLaps();
    }

    private void getAllLaps() {
        laptopRVModelArrayList.clear();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                laptopRVModelArrayList.add(snapshot.getValue(LaptopRVModel.class));
                laptopRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                laptopRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                laptopRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                laptopRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onItemClick(int position) {
        displayBottomSheet(laptopRVModelArrayList.get(position));
    }

    private void displayBottomSheet(LaptopRVModel laptopRVModel){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View layout = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_laptop,bottomSheetLap);
        bottomSheetDialog.setContentView(layout);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();

        TextView BrandName = layout.findViewById(R.id.BrandName);
        TextView Processor = layout.findViewById(R.id.Processor);
        TextView Price = layout.findViewById(R.id.Price);
        TextView Ram = layout.findViewById(R.id.Ram);
        TextView Vga = layout.findViewById(R.id.Vga);
        Button btnEdit = layout.findViewById(R.id.btnEdit);
        Button btnView = layout.findViewById(R.id.btnView);
        ImageView ImageView = layout.findViewById(R.id.ImageView);

        BrandName.setText(laptopRVModel.getName());
        Processor.setText(laptopRVModel.getProcessor());
        Price.setText("Rs. " + laptopRVModel.getPrice());
        Ram.setText("Ram - " + laptopRVModel.getRam());
        Vga.setText("Vga - " + laptopRVModel.getVga());
        Picasso.get().load(laptopRVModel.getImage()).into(ImageView);

        assert btnEdit != null;
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ViewActivity.this, EditLaptopActivity.class);
                i.putExtra("laptop",laptopRVModel);
                startActivity(i);
            }
        });

        assert btnView != null;
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ViewActivity.this, DisplayLaptopActivity.class);
                i.putExtra("laptop",laptopRVModel);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.idLogOut) {
            Toast.makeText(this, "User logged out..", Toast.LENGTH_SHORT).show();
            mAuth.signOut();
            Intent i = new Intent(ViewActivity.this, ViewActivity.class);
            startActivity(i);
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}