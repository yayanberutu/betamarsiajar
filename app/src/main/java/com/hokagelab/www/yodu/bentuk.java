package com.hokagelab.www.yodu;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class bentuk extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bentuk);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Bentuk");
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
