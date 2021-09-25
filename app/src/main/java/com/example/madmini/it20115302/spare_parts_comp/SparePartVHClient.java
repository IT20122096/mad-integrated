package com.example.madmini.it20115302.spare_parts_comp;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madmini.R;

public class SparePartVHClient extends RecyclerView.ViewHolder {

    public TextView textNameClient, textDescClient, textBrandModelClient, textPriceClient;
    public ImageView sparePartImgClient;
    public Button btnAddCard;

    public SparePartVHClient(@NonNull View itemView) {
        super(itemView);

        textNameClient = itemView.findViewById(R.id.txt_spare_part_name_client);
        textDescClient = itemView.findViewById(R.id.txt_spare_part_desc_client);
        textBrandModelClient = itemView.findViewById(R.id.txt_spare_part_brand_model_client);
        textPriceClient = itemView.findViewById(R.id.txt_spare_part_price_client);

        sparePartImgClient = itemView.findViewById(R.id.img_spare_part_client);
        btnAddCard = itemView.findViewById(R.id.btn_add_cart);
    }
}
