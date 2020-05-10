package com.hokagelab.www.yodu;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class buah extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buah);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Buah");
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
