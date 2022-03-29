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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;


public class MainActivity extends AppCompatActivity {

    private TextView email, password;
    private Button signinbtn;
    private FirebaseAuth myAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView textView = findViewById(R.id.signupview);
        String text = "New User? Sign Up";
        SpannableString ss = new SpannableString(text);
        ForegroundColorSpan fgcs = new ForegroundColorSpan(getColor(R.color.skyblue));
        ClickableSpan cs = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                startActivity(new Intent(MainActivity.this, Sign_Up.class));
            }
        };
        ss.setSpan(fgcs,10, 17, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ss.setSpan(cs, 10, 17, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        myAuth = FirebaseAuth.getInstance();


         email = findViewById(R.id.emailview);
        password = findViewById(R.id.passwordview);
        signinbtn = findViewById(R.id.signinbtn);

        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    public void onStart(){
        super.onStart();
        FirebaseUser current = myAuth.getCurrentUser();
        if (current != null){
            myAuth.getCurrentUser().reload();
        }
    }


    private void login(){
        String User = email.getText().toString().trim();
        String Password = password.getText().toString().trim();
        if(User.isEmpty()){
            email.setError("Enter E-mail");
        }
        if (Password.isEmpty()){
            password.setError("Enter Password");
        }
        else{
            myAuth.signInWithEmailAndPassword(User,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(MainActivity.this, Home.class));
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Incorrect E-mail and password", (Toast.LENGTH_LONG)).show();
                    }
                }
            });
        }
    }





}