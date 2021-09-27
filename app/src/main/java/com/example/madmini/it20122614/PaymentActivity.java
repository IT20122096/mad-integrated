package com.example.madmini.it20122614;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madmini.R;
import com.example.madmini.it20122096.models.Quotations;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaymentActivity extends AppCompatActivity {
    Button attach;
    Button confirm;
    Uri imageUri;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    int Image_Request_Code = 7;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String uId;
    TextView txtName;
    Payment imageUploadInfo;
    List<String> list = new ArrayList<>();
    String itemType;
    String iId = null;
    String quotation_id = null;
    int price;
    Date date;
    String nowDate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.it20122614_activity_payment);
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setTitle("Payment");

        attach = findViewById(R.id.buttonAttachImageId);
        confirm = findViewById(R.id.button2ConfimPaymentId);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference("Images");
        databaseReference = FirebaseDatabase.getInstance().getReference("Images");
        progressDialog = new ProgressDialog(PaymentActivity.this);
        uId = firebaseUser.getUid().toString();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        nowDate = simpleDateFormat.format(new Date());

        Intent intent = getIntent();
        ItemOrder itemOrder = (ItemOrder) intent.getSerializableExtra("ordered");
        Quotations quotations = (Quotations) intent.getSerializableExtra("quotation");
        if(intent.getExtras()!=null && itemOrder!=null){


            itemType = itemOrder.getItemType();
            price = itemOrder.getPrice();
            iId = itemOrder.getItemId();

       }
        else if(intent.getExtras()!=null && quotations!=null){
            quotation_id = quotations.getId();
            itemType="pc build";
            price=(int)quotations.getTotal();
        }


        attach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), Image_Request_Code);
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadImage(imageUri);
            }
        });

    }
    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            imageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
//                imgview.setImageBitmap(bitmap);
            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    private void UploadImage(Uri uri) {
        if (imageUri != null) {

            progressDialog.setTitle("Image is Uploading...");
            progressDialog.show();
            StorageReference storageReference2 = storageReference.child(System.currentTimeMillis() + "." + GetFileExtension(imageUri));
            storageReference2.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            databaseReference = FirebaseDatabase.getInstance().getReference().child("OrdersTable");
                            progressDialog.dismiss();


//                            DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("");
//                            Uri url = storageReference2.getDownloadUrl()
                            storageReference2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    imageUploadInfo = new Payment(uId, uri.toString(), itemType, price,iId, nowDate,quotation_id);
                                    String ImageUploadId = databaseReference.push().getKey();
                                    databaseReference.child(ImageUploadId).setValue(imageUploadInfo);
                                    Toast.makeText(getApplicationContext(), "Image Uploaded Successfully ", Toast.LENGTH_LONG).show();
                                }
                            });

//                            imageUploadInfo = new Payment(uId, taskSnapshot.getUploadSessionUri().toString(),iId, itemType, price);



                        }
                    });
        }
        else {

            Toast.makeText(PaymentActivity.this, "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();

        }

    }

}