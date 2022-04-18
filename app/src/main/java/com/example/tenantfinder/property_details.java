package com.example.tenantfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class property_details extends AppCompatActivity {

    private  TextView noofbed, noofhall,noofkit, description, address, name, email, phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_details);

        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        noofbed = findViewById(R.id.detnoofbed);
        noofhall = findViewById(R.id.detnoofhall);
        noofkit = findViewById(R.id.detnoofkit);
        description = findViewById(R.id.detdescview);
        address = findViewById(R.id.detadrsview);
        name = findViewById(R.id.detnameview);
        email = findViewById(R.id.detemailview);
        phone = findViewById(R.id.detphoneview);

        Intent i = getIntent();
        noofbed.setText(i.getStringExtra("no_of_bedroom"));
        noofhall.setText(i.getStringExtra("no_of_hall"));
        noofkit.setText(i.getStringExtra("no_of_kitchen"));
        description.setText(i.getStringExtra("description"));
        address.setText(i.getStringExtra("address"));
        name.setText(i.getStringExtra("name"));
        email.setText(i.getStringExtra("email"));
        phone.setText(i.getStringExtra("phone"));
    }
}