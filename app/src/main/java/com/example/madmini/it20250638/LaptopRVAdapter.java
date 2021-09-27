package com.example.madmini.it20250638;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madmini.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LaptopRVAdapter extends RecyclerView.Adapter<LaptopRVAdapter.ViewHolder> {

    private final ArrayList<LaptopRVModel> laptopRVAdapterArrayList;
    private final Context context;
    int lastPos = -1;
    private final LaptopClickInterface laptopClickInterface;
    private int position;

    public LaptopRVAdapter(ArrayList<LaptopRVModel> laptopRVAdapterArrayList, Context context, LaptopClickInterface laptopClickInterface) {
        this.laptopRVAdapterArrayList = laptopRVAdapterArrayList;
        this.laptopClickInterface = laptopClickInterface;
        this.context = context;
    }

    @NonNull
    @Override
    public LaptopRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate( R.layout.laptop_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LaptopRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        this.position = position;
        LaptopRVModel laptopRVModel = laptopRVAdapterArrayList.get(position);
        holder.edtBrandName.setText(laptopRVModel.getName());
        holder.edtPrice.setText("Rs. " + laptopRVModel.getPrice());
        holder.edtProcessor.setText(laptopRVModel.getProcessor());
        holder.edtVga.setText(laptopRVModel.getVga());
        holder.edtRam.setText(laptopRVModel.getRam());
        Picasso.get().load(laptopRVModel.getImage()).into(holder.imageView);
        setAnimation(holder.itemView,position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                laptopClickInterface.onItemClick(position);
            }
        });
    }

    private void setAnimation(View itemView, int postion){
        if(postion>lastPos){
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos = postion;
        }
    }

    @Override
    public int getItemCount() {
        return laptopRVAdapterArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView edtBrandName,edtPrice,edtProcessor,edtVga,edtOther,edtRam,edtSsd,edtHdd;
        private ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            edtBrandName = itemView.findViewById(R.id.BrandName);
            edtPrice = itemView.findViewById(R.id.Price);
            edtProcessor = itemView.findViewById(R.id.Processor);
            edtVga = itemView.findViewById(R.id.Vga);
            edtOther = itemView.findViewById(R.id.Other);
            edtRam = itemView.findViewById(R.id.Ram);
            edtSsd = itemView.findViewById(R.id.Ssd);
            edtHdd = itemView.findViewById(R.id.Hdd);
            imageView = itemView.findViewById(R.id.ImageView);
        }
    }

    public interface LaptopClickInterface {
        void onItemClick(int position);
    }
}

