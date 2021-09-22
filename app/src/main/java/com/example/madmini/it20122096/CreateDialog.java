package com.example.madmini.it20122096;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.madmini.R;

public class CreateDialog extends AppCompatDialogFragment {

    EditText d_name;
    private DialogListner dialogListner;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());
        LayoutInflater inflater =getActivity().getLayoutInflater();
        View view =inflater.inflate(R.layout.create_dialog,null);

        builder.setView(view)
                .setTitle("Create Quotation")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name =d_name.getText().toString();
                        dialogListner.applyText(name);


                    }
                });
        d_name=view.findViewById(R.id.d_name);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            dialogListner=(DialogListner) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString()+"Must implement DialogListner");
        }
    }

    public interface DialogListner{
        void applyText(String name);
    }
}
