package com.nemesiss.dev.ianime.Acitivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.nemesiss.dev.ianime.R;

import java.io.InputStream;


public class SplashActivity extends iAnimeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_splash);
        jumpToMainActivity();
    }
    public void jumpToMainActivity()
    {

        Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
