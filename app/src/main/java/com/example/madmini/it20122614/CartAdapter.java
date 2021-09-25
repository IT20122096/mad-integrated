package com.example.madmini.it20122614;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madmini.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class CartAdapter extends FirebaseRecyclerAdapter<Order, CartAdapter.cartViewHolder> {
    private FirebaseUser firebaseUser;
    public CartAdapter(
            @NonNull FirebaseRecyclerOptions<Order> options){
        super(options);
    }
    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull cartViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull Order model) {
//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        holder.type.setText(model.getItemType());
        holder.price.setText(model.getPrice()+"");
//        holder.quantity.setText(model.getQuantity()+"");

        holder.closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.type.getContext());
                builder.setTitle("Delete Cart item");
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("Cart").child(getRef(position).getKey()).removeValue();
                        Toast.makeText(holder.type.getContext(), "Item Removed", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.type.getContext(), "Not Removed", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });


    }

    @NonNull
    @Override
    public cartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order, parent, false);

        return new CartAdapter.cartViewHolder(view);
    }

    public class cartViewHolder extends RecyclerView.ViewHolder {

        TextView name, price, quantity, type;
        ImageButton closeBtn;
        public cartViewHolder(@NonNull View itemView){
            super(itemView);
            type = itemView.findViewById(R.id.firstname);
//            quantity = itemView.findViewById(R.id.lastname);
            price = itemView.findViewById(R.id.lastname);
            closeBtn = itemView.findViewById(R.id.imageButtonClose);



        }
    }
}
