package com.hokagelab.www.yodu;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class hewan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hewan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Hewan");
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
