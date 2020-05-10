package com.hokagelab.www.yodu;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class lagu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lagu);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Lagu");

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
