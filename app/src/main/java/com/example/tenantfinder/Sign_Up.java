package com.example.tenantfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;

public class Sign_Up extends AppCompatActivity {

    private TextView email, newpass, confpass;
    private FirebaseAuth myAuth;
    private TextView fname, lname, phone;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        TextView textView = findViewById(R.id.alreadyuser);
        String text = "Already a User? Sign In";
        SpannableString ss = new SpannableString(text);
        ForegroundColorSpan fcs = new ForegroundColorSpan(getColor(R.color.skyblue));
        ClickableSpan clickableSpan= new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Go_back_login();
            }
        };

        ss.setSpan(fcs, 16, 23, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ss.setSpan(clickableSpan, 16, 23, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        myAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.newemail);
        newpass = findViewById(R.id.newpassview);
        confpass = findViewById(R.id.confpassview);
        Button signupbtn = findViewById(R.id.signupbtnview);
        fname = findViewById(R.id.firstnameview);
        lname = findViewById(R.id.lastnameview);
        phone = findViewById(R.id.newphoneview);
        fstore = FirebaseFirestore.getInstance();


        signupbtn.setOnClickListener(view -> signup());
        }

    public void Go_back_login(){
        startActivity(new Intent(this, MainActivity.class));
    }


    private void signup(){
        String Email = email.getText().toString().trim();
        String Newpass = newpass.getText().toString().trim();
        String Confpass = confpass.getText().toString().trim();
        String Fname = fname.getText().toString();
        String Lname = lname.getText().toString();
        String Phone = phone.getText().toString();
        final String[] userID = new String[1];

        //**** SET THE LENGTH OF THE STRING****

        if(Fname.isEmpty()){
            fname.setError("First Name required");
        }
        if(Lname.isEmpty()){
            lname.setError("Last Name required");
        }
        if(Phone.isEmpty()){
            phone.setError("Phone No. required");
        }
        if(Phone.length() != 10){
            phone.setError("Enter a valid No.");
        }
        if(Email.isEmpty()){
            email.setError("E-mail required");
        }
        if(Newpass.isEmpty()){
            newpass.setError("Password required");
        }
        if(Newpass.length()<6){
            newpass.setError("Password must contain 6 characters");
        }
        if (Confpass.isEmpty()){
            confpass.setError("Re-Enter Password");
        }


        // *** CREATE A IF STATMNET FOR VERIFYING THE PASSWORD
//        else if (Newpass!=Confpass){
//            confpass.setError("Password doesn't match");
//        }

        else {
            myAuth.createUserWithEmailAndPassword(Email, Newpass).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    Toast.makeText(Sign_Up.this, "Signed Up succesfully", Toast.LENGTH_LONG).show();
                    userID[0] = Objects.requireNonNull(myAuth.getCurrentUser()).getUid();
                    DocumentReference documentReference = fstore.collection("Users").document(userID[0]);
                    Map<String, Object> user = new HashMap<>();
                    user.put("first_name", Fname);
                    user.put("last_name", Lname);
                    user.put("email", Email);
                    user.put("phone_no", Phone);
                    user.put("Password", Newpass);
                    documentReference.set(user);
                    startActivity(new Intent(Sign_Up.this, MainActivity.class));
                }
            });
        }
    }
}