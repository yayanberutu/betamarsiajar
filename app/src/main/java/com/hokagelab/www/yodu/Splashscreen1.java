package com.hokagelab.www.yodu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.hokagelab.www.yodu.activity.LoginActivity;

public class Splashscreen1 extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        imageView= findViewById(R.id.imageView);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent intenku = new Intent(getApplicationContext(),
                        LoginActivity.class   );
                startActivity(intenku);
            }
        },3000);
        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.rotate);
        imageView.startAnimation(myanim);
    }

}