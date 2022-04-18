package com.example.tenantfinder;

import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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
    private TextView phone, editprf;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        myauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        fullname = v.findViewById(R.id.Ffullname);
        email = v.findViewById(R.id.Femail);
        phone = v.findViewById(R.id.Fphone);
        DocumentReference drf = fstore.collection("Users").document(Objects.requireNonNull(myauth.getCurrentUser()).getUid());
        editprf = v.findViewById(R.id.editprf);
        progressBar = v.findViewById(R.id.progressbarprofile);

        String text = editprf.getText().toString();
        SpannableString spannableString = new SpannableString(text);
        ClickableSpan cs = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                startActivity(new Intent(v.getContext(), Update_Profile.class));
            }
        };
        // Edit Profile
        spannableString.setSpan(cs,0,12 , Spanned.SPAN_INCLUSIVE_INCLUSIVE );
        editprf.setText(spannableString);
        editprf.setMovementMethod(LinkMovementMethod.getInstance());


        Button signoutbtnview = v.findViewById(R.id.signoutbtnview);
        signoutbtnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                myauth.signOut();
                Toast.makeText(v.getContext(), "Signed out", Toast.LENGTH_LONG).show();
                startActivity(new Intent(v.getContext(), MainActivity.class));
                Log.d("signout","signed out succesfully");
                getActivity().finishAffinity();

            }
        });

        drf.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                progressBar.setVisibility(View.VISIBLE);
                String f_name = documentSnapshot.getString("first_name");
                String l_name = documentSnapshot.getString("last_name");
                String full_name = f_name+" "+l_name;
                String emaildta = documentSnapshot.getString("email");
                String phonedta = documentSnapshot.getString("phone_no");


                fullname.setText(full_name);
                email.setText(emaildta);
                phone.setText(phonedta);
            }
        });

    return v;
    }

}