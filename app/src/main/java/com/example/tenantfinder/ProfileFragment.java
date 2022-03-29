package com.example.tenantfinder;

import android.content.Intent;
import android.os.Bundle;


import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;


public class ProfileFragment extends Fragment {

    public ProfileFragment(){
        //require's  a default constructor
    }

    private FirebaseAuth myauth;
    private FirebaseFirestore fstore;
    private TextView fullname;
    private TextView email;
    private TextView phone;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        myauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        fullname = v.findViewById(R.id.Ffullname);
        email =  v.findViewById(R.id.Femail);
        phone =  v.findViewById(R.id.Fphone);
        String Uid = Objects.requireNonNull(myauth.getCurrentUser()).getUid();
        DocumentReference drf = fstore.collection("Users").document(Uid);

        Button signoutbtnview = v.findViewById(R.id.signoutbtnview);
        signoutbtnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myauth.signOut();
                Toast.makeText(v.getContext(), "Signed out",Toast.LENGTH_LONG).show();
                startActivity(new Intent(v.getContext(),MainActivity.class));
            }
        });

        drf.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String f_name = documentSnapshot.getString("first_name");
                        String l_name = documentSnapshot.getString("last_name");
                        String emaildta = documentSnapshot.getString("email");
                        String phonedta = documentSnapshot.getString("phone_no.");
                        String full_name = f_name+" "+l_name;


                        fullname.setText(full_name);
//                        email.setText(emaildta);
//                        phone.setText(phonedta);
                    }
                });
//        drf.get()
//                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                String first_n = documentSnapshot.getString("first_name");
//                String last_n = documentSnapshot.getString("last_name");
//                String email_txt = documentSnapshot.getString("email");
//                String phone_txt = documentSnapshot.getString("phone_no.");
//                String full_name = first_n+" "+last_n;
//
//                fullname.setText(full_name);
//                email.setText(email_txt);
//                phone.setText(phone_txt);
//
//            }
//        });

        return v;
    }
}