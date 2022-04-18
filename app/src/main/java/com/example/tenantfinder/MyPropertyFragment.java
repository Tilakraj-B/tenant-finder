package com.example.tenantfinder;


import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;


public class MyPropertyFragment extends Fragment {

    public MyPropertyFragment() {
        //require a constructor
    }

    FirebaseFirestore fstore;
    RecyclerView recyclerView;
    Recycler_Adapter recycler_adapter;
    ArrayList<Property_modal> propertyarraylist;
    FirebaseAuth myauth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_property, container, false);


        FloatingActionButton fbutton = v.findViewById(R.id.floatbutton);
        recyclerView = v.findViewById(R.id.mypRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        myauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        propertyarraylist = new ArrayList<Property_modal>();
        recycler_adapter = new Recycler_Adapter(v.getContext(), propertyarraylist);
        recyclerView.setAdapter(recycler_adapter);


        fbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), add_property.class));
            }
        });

        BindData();
        return v;
    }

    private void BindData() {
        String Uid = Objects.requireNonNull(myauth.getCurrentUser()).getUid();
        DocumentReference documentReference = fstore.collection("Users").document(Uid);
        CollectionReference collectionReference = documentReference.collection("My Property");
        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if(error != null){
                    Log.d("friestoreerror",error.getMessage());
                }

                if (value != null) {
                    for (DocumentChange documentChange : value.getDocumentChanges()){

                        if (documentChange.getType() == DocumentChange.Type.ADDED){
                            propertyarraylist.add(documentChange.getDocument().toObject(Property_modal.class));
                        }

                        recycler_adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }


}