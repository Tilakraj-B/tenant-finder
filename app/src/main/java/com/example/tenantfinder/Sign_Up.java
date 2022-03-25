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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Sign_Up extends AppCompatActivity {

    private TextView email, newpass, confpass;
    private Button signupbtn;
    private FirebaseAuth myAuth;

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
        signupbtn = findViewById(R.id.signupbtnview);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });
        }

    public void Go_back_login(){
        startActivity(new Intent(this, MainActivity.class));
    }


    private void signup(){
        String User = email.getText().toString().trim();
        String Password = newpass.getText().toString().trim();
        String ConfPass = confpass.getText().toString().trim();

        if(User.isEmpty()){
            email.setError("E-mail required");
        }
        if(Password.isEmpty()){
            newpass.setError("Password required");
        }
        if (ConfPass.isEmpty()){
            confpass.setError("Re-Enter Password");
        }

        // *********************CREATE A COMPARE IF STATMENT BETWEEN PASSWORD AND CONFIRM PASSWORD

        else {
            myAuth.createUserWithEmailAndPassword(User, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        startActivity(new Intent(Sign_Up.this, MainActivity.class));
                    }
                }
            });
        }
    }
}