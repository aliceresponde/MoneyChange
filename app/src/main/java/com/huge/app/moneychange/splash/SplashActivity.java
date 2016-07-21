package com.huge.app.moneychange.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.huge.app.moneychange.R;
import com.huge.app.moneychange.main.ui.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        goToMainScreen();
    }

    public void goToMainScreen(){
        startActivity(new Intent(this, MainActivity.class));
    }
}
