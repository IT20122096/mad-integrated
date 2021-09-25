package com.example.madmini.it20122096.RcvAdapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madmini.R;
import com.example.madmini.it20122096.models.Q_Items;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Quotation_item_Rcv_Adapter extends FirebaseRecyclerAdapter<Q_Items, Quotation_item_Rcv_Adapter.viewHolder> {

    int count=1;
    int max_quantity;

    public Quotation_item_Rcv_Adapter(@NonNull FirebaseRecyclerOptions<Q_Items> options) {
        super(options);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull final Q_Items model) {

        max_quantity=model.getQuantity();
        count=max_quantity;

        holder.i_name.setText(model.getName());
        holder.i_price.setText("LKR : "+model.getPrice()+"0");
        holder.i_quantity.setText(count+"");
        holder.inc_btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.S)
            @Override
            public void onClick(View view) {

                if(count>=max_quantity){
                    holder.inc_btn.setEnabled(false);
                }
                else {
                    holder.i_quantity.setText(++count+"");
                    holder.dec_btn.setEnabled(true);System.out.println("c-"+count);
                }
            }
        });
        holder.dec_btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(count<=1){
                    holder.i_quantity.setText(1+"");
                    holder.dec_btn.setEnabled(false);
                }
                else {
                    holder.i_quantity.setText(--count+"");
                    holder.inc_btn.setEnabled(true);
                }
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder= new AlertDialog.Builder(holder.i_name.getContext());
                builder.setTitle("Delete Item");
                builder.setMessage("Are You Sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Quotation_Items")
                                .child(getRef(position).getKey()).removeValue();
                        Toast.makeText(holder.i_name.getContext(),"Removed Successfully",Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.i_name.getContext(),"Couldn't remove",Toast.LENGTH_LONG).show();
                    }
                });
                builder.show();
            }
        });

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.it20122096_single_quotation_item_rcv,parent,false);
        return new viewHolder(view);
    }

    class viewHolder extends RecyclerView.ViewHolder{

        TextView i_name,i_price,i_quantity;
        ImageView i_image;
        Button inc_btn, dec_btn;
        ImageButton delete;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            i_name=(TextView) itemView.findViewById(R.id.i_name);
            i_price=(TextView) itemView.findViewById(R.id.i_price);
            i_quantity=(TextView) itemView.findViewById(R.id.i_quantity);
            i_image=(ImageView) itemView.findViewById(R.id.i_image);
            inc_btn=(Button) itemView.findViewById(R.id.inc_btn);
            dec_btn=(Button) itemView.findViewById(R.id.dec_btn);
            delete=(ImageButton) itemView.findViewById(R.id.i_delete);
        }
    }
}
