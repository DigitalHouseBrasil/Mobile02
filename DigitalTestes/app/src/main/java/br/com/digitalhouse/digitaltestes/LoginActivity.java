package br.com.digitalhouse.digitaltestes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        Toast.makeText(this, "Login com sucesso", Toast.LENGTH_SHORT).show();
    }

    public void loginWithMyFacebookButtom(View view) {
    }
}
