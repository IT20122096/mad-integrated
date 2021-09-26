package com.example.madmini.it20122096.RcvAdapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madmini.R;
import com.example.madmini.it20122096.models.Orders;
import com.example.madmini.it20122614.Payment;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Build_Order_Rcv_Adapter extends FirebaseRecyclerAdapter<Payment,Build_Order_Rcv_Adapter.viewHolder> {

    selectedOrder selectedOrder;

    public Build_Order_Rcv_Adapter(@NonNull FirebaseRecyclerOptions<Payment> options,selectedOrder selectedOrder) {
        super(options);
        this.selectedOrder=selectedOrder;
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull final Payment model) {

        System.out.println(model.getuId());
        FirebaseDatabase.getInstance().getReference().child("AddressBook").child(model.getuId())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        holder.o_name.setText(snapshot.child("name").getValue().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        holder.o_date.setText(model.getDate());

        holder.view_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedOrder.selectedOrder(model);
            }
        });
        holder.complete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseDatabase.getInstance().getReference().child("OrdersTable")
                        .child(getRef(position).getKey()).removeValue();
                Toast.makeText(holder.o_name.getContext(),"Order removed",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.it20122096_single_build_order_rcv,parent,false);
        return new viewHolder(view);
    }

    public interface selectedOrder{
        void selectedOrder(Payment payment);
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
