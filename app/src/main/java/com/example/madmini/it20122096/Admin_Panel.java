package com.example.madmini.it20122096;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.madmini.R;

public class Admin_Panel extends AppCompatActivity {

    Button pc_build_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        ActionBar actionBar =getSupportActionBar();
        getSupportActionBar().setTitle("Admin Panel");

        pc_build_btn=(Button) findViewById(R.id.build_order_btn);
        pc_build_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin_Panel.this, Pc_Build_Orders.class));
            }
        });
    }
}