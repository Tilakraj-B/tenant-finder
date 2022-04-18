package com.example.tenantfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Objects;

public class Update_Profile extends AppCompatActivity {

    FirebaseFirestore fstore;
    FirebaseAuth myauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }


        myauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        String Uid = myauth.getCurrentUser().getUid();
        EditText f_n = findViewById(R.id.firstudt);
        EditText l_n = findViewById(R.id.lastudt);
        EditText p_n = findViewById(R.id.phoneudt);
        DocumentReference drf = fstore.collection("Users").document(Uid);


        drf.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String f_name = documentSnapshot.getString("first_name");
                String l_name = documentSnapshot.getString("last_name");
                String phonedta = documentSnapshot.getString("phone_no");
                String emailold = documentSnapshot.getString("email");
                String password = documentSnapshot.getString("password");

                f_n.setText(f_name);
                l_n.setText(l_name);
                p_n.setText(phonedta);

            }
        });

        Button updtbutton = findViewById(R.id.buttonupdate);
        updtbutton.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("clickbutton", "update button has initalized");
                String f_update = f_n.getText().toString();
                String l_update = l_n.getText().toString();
                String p_update = p_n.getText().toString();

                HashMap<String, Object> update = new HashMap<>();
                update.put("first_name", f_update);
                update.put("last_name", l_update);
                update.put("phone_no", p_update);
                drf.set(update, SetOptions.merge());


                Log.d("update", "The User data has been updated.");

                Toast.makeText(Update_Profile.this, "Profile Updated", Toast.LENGTH_LONG).show();
                startActivity(new Intent(Update_Profile.this, Home.class));
                finish();
            }
        });
    }
}