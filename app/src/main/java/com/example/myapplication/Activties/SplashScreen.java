package com.example.myapplication.Activties;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.myapplication.R;

public class SplashScreen extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_spalash_screen);

        lottieAnimationView = findViewById(R.id.lottieanim);
        lottieAnimationView.playAnimation();

        new Handler().postDelayed((Runnable) () -> {
           startActivity(new Intent(SplashScreen.this, MainActivity.class));
           finish();
        },2000);



    }
}