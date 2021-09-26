package com.example.madmini.it20115302.spare_parts_comp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.madmini.R;

public class AddCartSparePart extends AppCompatActivity {

    private Button submit_cart;
    private NumberPicker quantityNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cart_spare_part);

        quantityNum = findViewById(R.id.quantity_num);
        submit_cart = findViewById(R.id.btn_submit_add_cart);

        quantityNum.setMinValue(1);
        quantityNum.setMaxValue(10);

        Cart cart = (Cart) getIntent().getSerializableExtra("CART");

        // set action bar
        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("View Spare Parts");


        submit_cart.setOnClickListener( view -> {
            if ( quantityNum.getValue() <= 0 ){
                Toast.makeText(AddCartSparePart.this, "Please enter valid quantity", Toast.LENGTH_SHORT).show();
            }
            else {
                cart.setQuantity(quantityNum.getValue());

                DAOCart dao = new DAOCart();

                dao.add(cart).addOnSuccessListener( success -> {
                    Toast.makeText(AddCartSparePart.this, "Item added to cart", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener( error -> {
                    Toast.makeText(AddCartSparePart.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                });

                startActivity(new Intent(AddCartSparePart.this, ShowSpareParts.class));
                finish();
            }

        });
    }
}