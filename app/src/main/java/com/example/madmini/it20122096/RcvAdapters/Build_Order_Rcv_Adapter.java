package com.example.madmini.it20122096.RcvAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madmini.R;
import com.example.madmini.it20122096.models.Orders;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class Build_Order_Rcv_Adapter extends FirebaseRecyclerAdapter<Orders,Build_Order_Rcv_Adapter.viewHolder> {

    selectedOrder selectedOrder;

    public Build_Order_Rcv_Adapter(@NonNull FirebaseRecyclerOptions<Orders> options,selectedOrder selectedOrder) {
        super(options);
        this.selectedOrder=selectedOrder;
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull Orders model) {

        holder.o_name.setText(model.getName());
        holder.o_date.setText(model.getDate());

        holder.view_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedOrder.selectedOrder(model);
            }
        });

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_build_order_rcv,parent,false);
        return new viewHolder(view);
    }

    public interface selectedOrder{
        void selectedOrder(Orders orders);
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView o_name,o_date;
        Button complete_btn, view_btn;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            o_name=(TextView) itemView.findViewById(R.id.o_name);
            o_date=(TextView) itemView.findViewById(R.id.o_date);
            complete_btn=(Button) itemView.findViewById(R.id.complete_btn);
            view_btn=(Button) itemView.findViewById(R.id.view_btn);
        }
    }
}
