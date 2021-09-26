package com.example.madmini.it20122096;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.madmini.R;
import com.example.madmini.it20115302.spare_parts_comp.AddSparePart;
import com.example.madmini.it20115302.spare_parts_comp.ViewAllSpareParts;

public class Admin_Panel extends AppCompatActivity {

    Button pc_build_btn,part_edit_btn,part_add_btn,part_order_btn,lap_edit_btn,lap_add_btn,laptop_order_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.it20122096_activity_admin_panel);

        ActionBar actionBar =getSupportActionBar();
        getSupportActionBar().setTitle("Admin Panel");

        pc_build_btn=(Button) findViewById(R.id.build_order_btn);
        pc_build_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin_Panel.this, Pc_Build_Orders.class));
            }
        });

        part_order_btn=(Button) findViewById(R.id.part_order_btn);
        part_order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin_Panel.this, PartOrders.class));
            }
        });

        part_edit_btn=(Button)findViewById(R.id.part_edit_btn);
        part_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin_Panel.this, ViewAllSpareParts.class));
            }
        });

        part_add_btn=(Button) findViewById(R.id.part_add_btn);
        part_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin_Panel.this, AddSparePart.class));
            }
        });

        laptop_order_btn= (Button) findViewById(R.id.laptop_order_btn);
        laptop_order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin_Panel.this, LaptopOrders.class));
            }
        });




    }
}