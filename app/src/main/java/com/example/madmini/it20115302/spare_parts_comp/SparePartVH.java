package com.example.madmini.it20115302.spare_parts_comp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madmini.R;

public class SparePartVH extends RecyclerView.ViewHolder {

    public TextView textName, textDesc, textBrandModel, textPrice, textOption;
    public ImageView sparePartImg;
    public SparePartVH(@NonNull View itemView) {
        super(itemView);
        textName = itemView.findViewById(R.id.txt_spare_part_name);
        textDesc = itemView.findViewById(R.id.txt_spare_part_desc);
        textBrandModel = itemView.findViewById(R.id.txt_spare_part_brand_model);
        textPrice = itemView.findViewById(R.id.txt_spare_part_price);
        textOption = itemView.findViewById(R.id.txt_option_spare);

        sparePartImg = itemView.findViewById(R.id.img_spare_part);
    }
}
