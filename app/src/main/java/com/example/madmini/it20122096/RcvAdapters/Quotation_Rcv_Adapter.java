package com.example.madmini.it20122096.RcvAdapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madmini.R;
import com.example.madmini.it20122096.models.Quotations;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Quotation_Rcv_Adapter extends FirebaseRecyclerAdapter<Quotations,Quotation_Rcv_Adapter.viewHolder> {

    private Context context;
    private SelectedQuotation selectedQuotation;
    private List<Quotations> quotationsList;


    public Quotation_Rcv_Adapter(@NonNull FirebaseRecyclerOptions<Quotations> options, Context context,SelectedQuotation selectedQuotation) {
        super(options);
        this.context=context;
        this.selectedQuotation=selectedQuotation;
    }

    public Quotation_Rcv_Adapter(@NonNull FirebaseRecyclerOptions<Quotations> options) {
        super(options);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull final Quotations model) {

        holder.q_name.setText(model.getName());
        holder.q_price.setText("Total : LKR "+model.getTotal()+"0");

        holder.q_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedQuotation.selectedQuotation(model,"view");
            }
        });
        holder.q_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedQuotation.selectedQuotation(model,"pay");
            }
        });

        holder.q_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.q_name.getContext());
                builder.setTitle("DELETE QUOTATION!!");
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Quotation")
                                .child(getRef(position).getKey()).removeValue();
                        Toast.makeText(context,"Your Quotation Deleted Successfully",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.it20122096_single_quotation_rcv,parent,false);
        return new viewHolder(view);
    }

    public interface SelectedQuotation{
        void selectedQuotation(Quotations quotaions, String type);
    }

    class viewHolder extends RecyclerView.ViewHolder{

        TextView q_name, q_price;
        Button q_view,q_pay;
        ImageButton q_delete;



        public viewHolder(@NonNull View itemView) {
            super(itemView);

            q_name=(TextView) itemView.findViewById(R.id.q_name);
            q_price=(TextView) itemView.findViewById(R.id.q_price);

            q_view=(Button) itemView.findViewById(R.id.q_view);
            q_delete=(ImageButton) itemView.findViewById(R.id.q_delete);
            q_pay=(Button) itemView.findViewById(R.id.q_pay);

           

        }


    }
}
