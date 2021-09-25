package com.example.madmini.it20122096.RcvAdapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madmini.R;
import com.example.madmini.it20122096.models.Q_Items;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class Quotation_List_Adapter extends FirebaseRecyclerAdapter<Q_Items,Quotation_List_Adapter.viewHolder> {


    double total;
    public Quotation_List_Adapter(@NonNull FirebaseRecyclerOptions<Q_Items> options) {
        super(options);

    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull Q_Items model) {
        holder.part_name.setText(model.getName()+"");
        holder.part_qty.setText(model.getQuantity()+"");
        total=model.getPrice()*(double)model.getQuantity();
        System.out.println((double)model.getQuantity());
        holder.part_tot.setText(total+"");
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_quotation_item_rcv,parent,false);
        return new viewHolder(view);
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView part_name,part_qty,part_tot;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            part_name=(TextView) itemView.findViewById(R.id.part_name);
            part_qty=(TextView) itemView.findViewById(R.id.part_qty);
            part_tot=(TextView) itemView.findViewById(R.id.part_tot);
        }
    }
}
