package com.huge.app.moneychange.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.huge.app.moneychange.R;
import com.huge.app.moneychange.main.ui.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.img_logo)
    ImageView imgLogo;
    private long splashDelay = 3500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        imgLogo.startAnimation(animation1);

        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                goToMainScreen();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, splashDelay);

    }

    public void goToMainScreen() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
