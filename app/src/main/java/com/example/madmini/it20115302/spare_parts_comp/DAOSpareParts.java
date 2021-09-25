package com.example.madmini.it20115302.spare_parts_comp;


import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;


public class DAOSpareParts {

    private DatabaseReference databaseReference;

    public DAOSpareParts() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(SparePart.class.getSimpleName());
    }

    public Task<Void> add(SparePart sparePart) {
        return databaseReference.push().setValue(sparePart);
    }

    public Task<Void> update (String key, HashMap<String,Object> hashMap) {
        return databaseReference.child(key).updateChildren(hashMap);
    }

    public Task<Void> remove ( String key ) {
        return databaseReference.child(key).removeValue();
    }

    public Query get(String key){
        if ( key == null ) {
            return databaseReference.orderByKey().limitToFirst(8);
        }
        return databaseReference.orderByKey().startAfter(key).limitToFirst(8);
    }

    public Query get(){
        return databaseReference;
    }


}
