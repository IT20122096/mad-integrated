package com.example.madmini.it20122096;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.madmini.R;
import com.example.madmini.it20115302.spare_parts_comp.ShowSpareParts;
import com.example.madmini.it20115302.spare_parts_comp.ViewAllSpareParts;
import com.example.madmini.it20122614.ProfileActivity;
import com.example.madmini.it20122614.RegisterActivity;
import com.example.madmini.it20250638.ViewActivity;

public class DashBoard extends AppCompatActivity {

    ImageButton laptop,parts,build,profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.it20122096_activity_dashboard);

        laptop =(ImageButton) findViewById(R.id.laptop_btn);
        parts =(ImageButton) findViewById(R.id.parts_btn);
        build =(ImageButton) findViewById(R.id.build_btn);
        profile =(ImageButton) findViewById(R.id.profile_btn);

        build.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              startActivity(new Intent(getApplicationContext(), BulidPc.class));

            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoard.this, ProfileActivity.class));
            }
        });

        parts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoard.this, ShowSpareParts.class));
            }
        });

        laptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoard.this, ViewActivity.class));
            }
        });
    }
}