package com.jm.mymaps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton bt_add_map;

    @Override
    protected void onStart() {
        super.onStart();
        LoadData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        bt_add_map = findViewById(R.id.bt_add_map);
        bt_add_map.setOnClickListener(v -> openAdd_map());
    }


    public void openAdd_map(){
        Intent i= new Intent(this, add_map.class);
        startActivity(i);
    }

    private void LoadData() {


    }
}

