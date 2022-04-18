package com.example.tenantfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class add_property extends AppCompatActivity {

    FirebaseFirestore f;
    private Button pbutton;
    private TextView desc, addr, eml, phn, name, no_bed, no_hall, no_kit;
    FirebaseAuth myauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_property);

        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        pbutton = findViewById(R.id.addp_button);
        desc = findViewById(R.id.addp_desc);
        addr = findViewById(R.id.addp_address);
        eml  = findViewById(R.id.addp_eml);
        phn = findViewById(R.id.addp_phn);
        name = findViewById(R.id.addp_name);
        no_bed = findViewById(R.id.no_bed);
        no_hall = findViewById(R.id.no_hall);
        no_kit = findViewById(R.id.no_kit);

        f = FirebaseFirestore.getInstance();
        myauth = FirebaseAuth.getInstance();

        pbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_prop();
            }
        });
    }

    private void add_prop(){
        Log.d("Tag","button clicked");
        String Desc = desc.getText().toString();
        String Addr = addr.getText().toString();
        String Eml = eml.getText().toString();
        String Phn = phn.getText().toString();
        String Name = name.getText().toString();
        String NO_BED = no_bed.getText().toString();
        String NO_HALL = no_hall.getText().toString();
        String NO_KIT = no_kit.getText().toString();

        if (Desc.isEmpty()){
            desc.setError("Field required");
        }
        if ((Addr.isEmpty())){
            addr.setError("Field required");
        }
        if (Eml.isEmpty()){
            eml.setError("Field required");
        }
        if (Phn.isEmpty()){
            phn.setError("Field required");
        }
        if (Name.isEmpty()){
            name.setError("Field requires");
        }
        if (NO_BED.isEmpty()){
            no_bed.setError("Field required");
        }
        if (NO_HALL.isEmpty()){
            no_hall.setError("Field required");
        }
        if (NO_KIT.isEmpty()){
            no_kit.setError("Field required");
        }
        else {
            DocumentReference documentReference = f.collection("Property").document(Name);
            DocumentReference drf = f.collection("Users").document(Objects.requireNonNull(myauth.getCurrentUser()).getUid()).collection("My Property").document(Name);
            HashMap<Object, String> property = new HashMap<>();
            property.put("description", Desc);
            property.put("address", Addr);
            property.put("email", Eml);
            property.put("phone", Phn);
            property.put("name", Name);
            property.put("no_of_bedroom", NO_BED);
            property.put("no_of_hall", NO_HALL);
            property.put("no_of_kitchen", NO_KIT);


            drf.set(property).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Log.d("Tag", "the data has been saved to the firestore");
                }
            });
            documentReference.set(property).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    startActivity(new Intent(add_property.this, Home.class));
                    Toast.makeText(add_property.this, "Property added", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}