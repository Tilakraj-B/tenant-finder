package com.example.tenantfinder;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;

import com.google.common.base.Objects;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    public HomeFragment(){
        //require a empty public constructor
    }

    FirebaseFirestore mystore;
    RecyclerView recyclerView;
    Recycler_Adapter recycler_adapter;
    ArrayList<Property_modal> propertylistarray;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);


        recyclerView = v.findViewById(R.id.homerecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mystore = FirebaseFirestore.getInstance();
        propertylistarray = new ArrayList<Property_modal>();
        recycler_adapter = new Recycler_Adapter(v.getContext(), propertylistarray);
        recyclerView.setAdapter(recycler_adapter);

        BindData();

        return  v;
    }

    private void BindData() {
        CollectionReference collectionReference = mystore.collection("Property");
        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null){
                    Log.d("firestoreerror", error.getMessage());
                    return;
                }

                for ( DocumentChange documentChange : value.getDocumentChanges()){

                    if (documentChange.getType() == DocumentChange.Type.ADDED){
                        propertylistarray.add(documentChange.getDocument().toObject(Property_modal.class));
                    }

                    recycler_adapter.notifyDataSetChanged();
                }
            }
        });
    }
}