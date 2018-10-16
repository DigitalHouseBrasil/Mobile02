package br.com.digitalhouse.digitalhouseapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                jump(null);
            }
        }, 3000);
    }

    public void jump(View view) {
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        timer.cancel();
        finish();
    }
}
