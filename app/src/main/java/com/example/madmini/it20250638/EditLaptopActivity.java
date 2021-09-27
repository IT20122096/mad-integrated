package com.example.madmini.it20250638;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.madmini.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditLaptopActivity extends AppCompatActivity {
    private TextInputEditText edtBrandName,edtProcessor,edtHdd,edtVga,edtOther,edtPrice,edtRam,edtSsd,edtImage;
    private Button btnSave, btnDelete;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String laptopId;
    private LaptopRVModel laptopRVModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.it20250638_activity_edit_laptop);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        edtBrandName =  findViewById(R.id.edtBrandName);
        edtProcessor =  findViewById(R.id.edtProcessor);
        edtHdd =  findViewById(R.id.edtHdd);
        edtVga =  findViewById(R.id.edtVga);
        edtOther =  findViewById(R.id.edtOther);
        edtPrice =  findViewById(R.id.edtPrice);
        edtRam =  findViewById(R.id.edtRam);
        edtSsd =  findViewById(R.id.edtSsd);
        edtImage =  findViewById(R.id.edtImage);

        laptopRVModel = getIntent().getParcelableExtra("laptop");
        if(laptopRVModel != null){
            edtBrandName.setText(laptopRVModel.getName());
            edtProcessor.setText(laptopRVModel.getProcessor());
            edtHdd.setText(laptopRVModel.getHdd());
            edtVga.setText(laptopRVModel.getVga());
            edtOther.setText(laptopRVModel.getOther());
            edtPrice.setText(laptopRVModel.getPrice());
            edtRam.setText(laptopRVModel.getRam());
            edtSsd.setText(laptopRVModel.getSsd());
            edtImage.setText(laptopRVModel.getImage());
            laptopId = laptopRVModel.getName();
        }
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Laptops").child(laptopId);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = String.valueOf(edtBrandName.getText());
                String price = String.valueOf(edtPrice.getText());
                String processor = String.valueOf(edtProcessor.getText());
                String vga = String.valueOf(edtVga.getText());
                String other = String.valueOf(edtOther.getText());
                String ram = String.valueOf(edtRam.getText());
                String ssd = String.valueOf(edtSsd.getText());
                String hdd = String.valueOf(edtHdd.getText());
                String image = String.valueOf(edtImage.getText());

                Map<String,Object> map = new HashMap<>();
                map.put("name",name);
                map.put("price",price);
                map.put("processor",processor);
                map.put("vga",vga);
                map.put("other",other);
                map.put("ram",ram);
                map.put("ssd",ssd);
                map.put("hdd",hdd);
                map.put("image",image);
                map.put("laptopId",laptopId);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.updateChildren(map);
                        Toast.makeText(EditLaptopActivity.this,"Laptop Updated..",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditLaptopActivity.this, ViewActivity.class));
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(EditLaptopActivity.this,"Failed to Updated Laptop..",Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItem();
                System.out.println("working");
            }
        });
    }
    private void deleteItem(){
        databaseReference.setValue(null);
        databaseReference.removeValue();
        Toast.makeText(this,"Laptop Deleted...",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(EditLaptopActivity.this,ViewActivity.class));
    }
}