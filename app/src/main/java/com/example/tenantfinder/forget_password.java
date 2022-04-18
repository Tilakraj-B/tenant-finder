package com.example.tenantfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;

public class forget_password extends AppCompatActivity {

    private EditText verifyemail;
    private Button verifybtn;
    FirebaseAuth myauth;
    FirebaseUser myuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }


        verifyemail = findViewById(R.id.vfyemailview);
        verifybtn = findViewById(R.id.vfybtn);
        myauth = FirebaseAuth.getInstance();

        verifybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verify();
            }
        });
    }

    private void verify(){
        String email = verifyemail.getText().toString().trim();

        if (email.isEmpty()){
            verifyemail.setError("Field required");
        }
        else {
            myauth.fetchSignInMethodsForEmail(email)
                    .addOnSuccessListener(new OnSuccessListener<SignInMethodQueryResult>() {
                        @Override
                        public void onSuccess(SignInMethodQueryResult signInMethodQueryResult) {
                            myauth.sendPasswordResetEmail(email);
                            Toast.makeText(forget_password.this, "Password reset link sent",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(forget_password.this, MainActivity.class));
                        }
                    });
        }
    }
}