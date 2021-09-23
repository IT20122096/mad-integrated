package com.example.madmini.it20122096;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.madmini.R;
import com.example.madmini.it20122096.models.Orders;

public class Order_Details extends AppCompatActivity {

    Orders orders;
    TextView od_name,od_address,od_pno,od_amount,od_date;
    Button slip_btn, down_btn;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oreder_details);

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


    }


}