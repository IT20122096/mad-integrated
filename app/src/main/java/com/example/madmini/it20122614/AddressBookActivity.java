package com.example.madmini.it20122614;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.madmini.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddressBookActivity extends AppCompatActivity {

    ImageButton imgHomeBtn;
    ImageButton imgCart;
    ImageButton imgProfile;
    EditText txtAddress;
    EditText txtPhone;
    EditText txtName;
    Button saveBtn;
    AddressBook addressBook;
    DatabaseReference databaseReference;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.it20122614_activity_address_book);
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setTitle("Address Book");

        imgHomeBtn = findViewById(R.id.imageButtonHome);
        imgCart = findViewById(R.id.imageButtonCart);
        imgProfile = findViewById(R.id.imageButtonProfile);

        txtAddress = findViewById(R.id.editTextTextMultiLineAddress);
        txtPhone = findViewById(R.id.editTextPhoneContact);
        txtName = findViewById(R.id.editTextTextPersonName);
        saveBtn = findViewById(R.id.buttonAddressBookContact);
        addressBook = new AddressBook();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = txtAddress.getText().toString();
                String phoneNumber = txtPhone.getText().toString();
                String name = txtName.getText().toString();

                String uId = firebaseUser.getUid().toString();
                databaseReference = FirebaseDatabase.getInstance().getReference().child("AddressBook");

                if(TextUtils.isEmpty(address)){
                    Toast.makeText(AddressBookActivity.this, "Please enter address", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(phoneNumber)){
                    Toast.makeText(AddressBookActivity.this, "Please enter contact number", Toast.LENGTH_SHORT).show();
                }else{
                    addressBook.setAddress(address);
                    addressBook.setcNumber(phoneNumber);
                    addressBook.setName(name);

                    databaseReference.child(uId).setValue(addressBook);
                    Toast.makeText(AddressBookActivity.this, "Address Added", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddressBookActivity.this, ProfileActivity.class));
                    finish();
                }



            }
        });

    }
}