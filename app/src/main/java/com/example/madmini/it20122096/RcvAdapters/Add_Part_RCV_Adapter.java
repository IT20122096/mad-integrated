package com.example.madmini.it20122096.RcvAdapters;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.example.madmini.it20122096.models.Parts;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Add_Part_RCV_Adapter extends FirebaseRecyclerAdapter<Parts,Add_Part_RCV_Adapter.viewHolder> {

    int count=1;
    int max_quantity;
    String q_id;
    Context context;

    public Add_Part_RCV_Adapter(@NonNull FirebaseRecyclerOptions<Parts> options, String q_id, Context context) {
        super(options);
        this.q_id=q_id;
        this.context=context;
    }

    @SuppressLint({"ResourceType", "SetTextI18n"})
    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, final int position, @NonNull final Parts model) {

        max_quantity=model.getQuantity();

        holder.p_name.setText(model.getName());
        holder.p_price.setText(model.getPrice().toString());
        holder.p_quantity.setText(count+"");
        holder.inc_btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.S)
            @Override
            public void onClick(View view) {

                if(count>=max_quantity){
                    holder.inc_btn.setEnabled(false);
                }
                else {
                    holder.p_quantity.setText(++count+"");
                    holder.dec_btn.setEnabled(true);
                }
            }
        });
        holder.dec_btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(count<=1){
                    holder.p_quantity.setText(1+"");
                    holder.dec_btn.setEnabled(false);
                }
                else {
                    holder.p_quantity.setText(--count+"");
                    holder.inc_btn.setEnabled(true);
                }
            }
        });
        holder.p_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String,Object> map= new HashMap<>();
                map.put("part_id",model.getId());
                map.put("quotation_id",q_id);
                map.put("name",model.getName());
                map.put("quantity",count);
                map.put("price",model.getPrice());

                FirebaseDatabase.getInstance().getReference()
                        .child("Quotation_Items").push()
                        .setValue(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(context,"Item Added to the Quotation Successfully",Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context,"Couldn't Add the Item",Toast.LENGTH_LONG).show();
                            }
                        });
                count=1;


            }
        });
          System.out.println(max_quantity);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.it20122096_single_part_rcv,parent,false);
        return new viewHolder(view);
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView p_name, p_price,p_quantity;
        ImageButton p_add_btn;
        ImageView p_img;
        Button dec_btn, inc_btn;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            p_name=(TextView) itemView.findViewById(R.id.p_name);
            p_price=(TextView) itemView.findViewById(R.id.p_price);
            p_quantity=(TextView) itemView.findViewById(R.id.p_quantity);
            p_add_btn=(ImageButton) itemView.findViewById(R.id.p_add_btn);
            inc_btn=(Button) itemView.findViewById(R.id.increment_btn);
            dec_btn=(Button) itemView.findViewById(R.id.decrement_btn);
        }
    }
}
