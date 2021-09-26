package com.example.madmini.it20115302.spare_parts_comp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.madmini.R;


import java.util.HashMap;

public class UpdateSparePart extends AppCompatActivity {

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
        setContentView(R.layout.activity_update_spare_part);

        // set action bar
        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Update Spare Part");

        // set resources
        editName = findViewById(R.id.edit_name);
        editBrand = findViewById(R.id.edit_brand);
        editModel = findViewById(R.id.edit_model);
        editPrice = findViewById(R.id.edit_price);
        editDesc = findViewById(R.id.edit_desc);
        btnSubmit = findViewById(R.id.btn_update);
        btnCancel = findViewById(R.id.btn_cancel);

        DAOSpareParts dao = new DAOSpareParts();

        SparePart sparePart_edit = (SparePart) getIntent().getSerializableExtra("EDIT");

        if (sparePart_edit != null) {
            editName.setText(sparePart_edit.getName());
            editBrand.setText(sparePart_edit.getBrand());
            editModel.setText(sparePart_edit.getModel());
            editPrice.setText(Double.toString(sparePart_edit.getUnitPrice()));
            editDesc.setText(sparePart_edit.getDesc());
        } else {
            Toast.makeText(getApplicationContext(), "No Spare Part Selected", Toast.LENGTH_SHORT).show();
        }

        btnSubmit.setOnClickListener( view -> {

            String regexStr = "^[0-9]*$";
            if (this.isEmpty(editName) || this.isEmpty(editBrand) || this.isEmpty(editModel) || this.isEmpty(editPrice) || this.isEmpty(editDesc)){
                Toast.makeText(UpdateSparePart.this, "Please fill mandatory fields", Toast.LENGTH_SHORT).show();
            }
            else if(!editPrice.getText().toString().trim().matches(regexStr))
            {
                Toast.makeText(UpdateSparePart.this, "Please enter valid price", Toast.LENGTH_SHORT).show();
            }
            else {

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("name", editName.getText().toString());
                hashMap.put("brand", editBrand.getText().toString());
                hashMap.put("model", editModel.getText().toString());
                hashMap.put("unitPrice", Double.parseDouble(editPrice.getText().toString()));
                hashMap.put("desc", editDesc.getText().toString());

                dao.update(sparePart_edit.getKey(), hashMap).addOnSuccessListener(success -> {
                    Toast.makeText(this, "Spare Part Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateSparePart.this, ViewAllSpareParts.class);
                    startActivity(intent);
                    finish();
                }).addOnFailureListener(error -> {
                    Toast.makeText(this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                });

            }


        });

        btnCancel.setOnClickListener( view -> {

            Intent intent = new Intent(UpdateSparePart.this, ViewAllSpareParts.class);
            startActivity(intent);
            finish();
        });
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
}