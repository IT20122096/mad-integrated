package com.example.madmini.it20122096;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.madmini.R;
import com.example.madmini.it20122096.BulidPc;

public class DashBoard extends AppCompatActivity {

    ImageButton laptop,parts,build,profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

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
    }
}