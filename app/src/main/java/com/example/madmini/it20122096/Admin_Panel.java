package com.example.madmini.it20122096;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.madmini.R;
import com.example.madmini.it20115302.spare_parts_comp.AddSparePart;
import com.example.madmini.it20115302.spare_parts_comp.ViewAllSpareParts;
import com.example.madmini.it20122614.CartActivity;
import com.example.madmini.it20122614.ProfileActivity;
import com.example.madmini.it20250638.AddLaptopActivity;
import com.example.madmini.it20250638.ViewActivity;

public class Admin_Panel extends AppCompatActivity {

    Button pc_build_btn,part_edit_btn,part_add_btn,part_order_btn,lap_edit_btn,lap_add_btn,laptop_order_btn;
    ImageButton home_btn,cart_btn,profile_btn_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.it20122096_activity_admin_panel);

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

        lap_add_btn=(Button) findViewById(R.id.lap_add_btn);
        lap_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin_Panel.this, AddLaptopActivity.class));
            }
        });

        lap_edit_btn=(Button) findViewById(R.id.lap_edit_btn);
        lap_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin_Panel.this, ViewActivity.class));
            }
        });





    }
}