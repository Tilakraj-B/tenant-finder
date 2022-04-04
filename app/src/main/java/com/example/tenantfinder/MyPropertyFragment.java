package com.example.tenantfinder;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MyPropertyFragment extends Fragment {

    public MyPropertyFragment(){
        //require a constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_property, container, false);

        FloatingActionButton fbutton = v.findViewById(R.id.floatbutton);
        fbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(v.getContext(), add_property.class));
            }
        });

        return v;
    }
}