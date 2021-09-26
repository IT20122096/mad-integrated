package com.example.madmini.it20115302.spare_parts_comp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.madmini.R;

import com.example.madmini.it20122096.DashBoard;
//import com.example.madmini.it20115302.SparePart;

public class AddSparePart extends AppCompatActivity {

    // attributes
    private EditText editName;
    private EditText editBrand;
    private EditText editModel;
    private EditText editPrice;
    private EditText editDesc;

    private Button btnSubmit;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_spare_part);

        // set action bar
        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Add Spare Part");

        // set resources
        editName = findViewById(R.id.edit_name);
        editBrand = findViewById(R.id.edit_brand);
        editModel = findViewById(R.id.edit_model);
        editPrice = findViewById(R.id.edit_price);
        editDesc = findViewById(R.id.edit_desc);
        btnSubmit = findViewById(R.id.btn_add);
        btnCancel = findViewById(R.id.btn_cancel_add);

        DAOSpareParts dao = new DAOSpareParts();

        btnSubmit.setOnClickListener( view -> {
            SparePart sparePart = new SparePart(
                    editName.getText().toString(),
                    editBrand.getText().toString(),
                    editModel.getText().toString(),
                    Double.parseDouble(editPrice.getText().toString()),
                    editDesc.getText().toString());

            dao.add(sparePart).addOnSuccessListener( success -> {
                Toast.makeText(this, "Spare Part Inserted", Toast.LENGTH_SHORT).show();

                // TODO: set the correct direction
                Intent intent = new Intent(AddSparePart.this, DashBoard.class);
                startActivity(intent);
                finish();
            }).addOnFailureListener( error -> {
                Toast.makeText(this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });

        btnCancel.setOnClickListener(view -> {
            Intent intent = new Intent(AddSparePart.this, DashBoard.class);
            startActivity(intent);
            finish();
        });


    }
}