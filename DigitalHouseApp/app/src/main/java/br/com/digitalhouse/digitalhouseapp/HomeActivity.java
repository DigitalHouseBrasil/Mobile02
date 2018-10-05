package br.com.digitalhouse.digitalhouseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView email = findViewById(R.id.email_home);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        email.setText(bundle.getString("email"));
    }
}
