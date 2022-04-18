package com.example.tenantfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.UUID;


public class MainActivity extends AppCompatActivity {

    private TextView email, password;
    private FirebaseAuth myAuth;
    private TextView forgetpass;
    ProgressBar progressBar;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //hiding the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // creating a spannable string and customizig the new user text
        TextView signup = findViewById(R.id.signupview);
//       "New User? Sign Up"
        String text = signup.getText().toString();
        SpannableString ss = new SpannableString(text);
        ForegroundColorSpan fgcs = new ForegroundColorSpan(getColor(R.color.skyblue));
        ClickableSpan cs = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                startActivity(new Intent(MainActivity.this, Sign_Up.class));
                finish();
            }
        };
        ss.setSpan(fgcs, 10, 17, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ss.setSpan(cs, 10, 17, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        signup.setText(ss);
        signup.setMovementMethod(LinkMovementMethod.getInstance());


        myAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.emailview);
        password = findViewById(R.id.passwordview);
        Button signinbtn = findViewById(R.id.signinbtn);


        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        forgetpass = findViewById(R.id.forgetbtn);
        String fgtpass = forgetpass.getText().toString();
//      "Forget Password?"
        SpannableString spannableString = new SpannableString(fgtpass);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                startActivity(new Intent(MainActivity.this, forget_password.class));
            }
        };

        spannableString.setSpan(clickableSpan, 0, 16, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        forgetpass.setText(spannableString);
        forgetpass.setMovementMethod((LinkMovementMethod.getInstance()));


        checkBox = findViewById(R.id.passwordcheck);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBox.isChecked()) {
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        progressBar = findViewById(R.id.progressbar);
    }


    // checking if previous user logged out or not if not reloggin the previous user
    public void onStart() {
        super.onStart();
        FirebaseUser current = myAuth.getCurrentUser();
        FirebaseFirestore fstore;
        fstore = FirebaseFirestore.getInstance();
        if (current != null) {
            progressBar.setVisibility(View.VISIBLE);
            String Uid = current.getUid();
            DocumentReference drf = fstore.collection("Users").document(Uid);
            drf.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    String email = documentSnapshot.getString("email");
                    String password = documentSnapshot.getString("password");
                    myAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d("Tag", "User reloded");
                                startActivity(new Intent(MainActivity.this, Home.class));
                                finish();
                            }
                        }
                    });
                }


            });
        }

    }

    // loggin in a user
    private void login() {
        String User = email.getText().toString();
        String Password = password.getText().toString();
        if (User.isEmpty()) {
            email.setError("Enter E-mail");
        } else if (Password.isEmpty()) {
            password.setError("Enter Password");
        } else {
            myAuth.signInWithEmailAndPassword(User, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(MainActivity.this, Home.class));
                        progressBar.setVisibility(View.VISIBLE);
                        finish();
                    } else {
                        Toast.makeText(MainActivity.this, "Incorrect E-mail and password", (Toast.LENGTH_LONG)).show();
                    }
                }
            });
        }
    }


}