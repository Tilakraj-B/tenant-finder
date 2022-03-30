package com.example.tenantfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class Update_Profile extends AppCompatActivity {

    FirebaseFirestore fstore;
    FirebaseAuth myauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        myauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        String Uid = myauth.getCurrentUser().getUid();
        EditText f_n = findViewById(R.id.firstudt);
        EditText l_n = findViewById(R.id.lastudt);
        EditText e_m = findViewById(R.id.emailudt);
        EditText p_n = findViewById(R.id.phoneudt);
        DocumentReference drf = fstore.collection("Users").document(Uid);


        drf.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String f_name = documentSnapshot.getString("first_name");
                String l_name = documentSnapshot.getString("last_name");
                String emaildta = documentSnapshot.getString("email");
                String phonedta = documentSnapshot.getString("phone_no");


                f_n.setText(f_name);
                l_n.setText(l_name);
                e_m.setText(emaildta);
                p_n.setText(phonedta);
            }
        });

    }
}