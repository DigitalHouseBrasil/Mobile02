package br.com.digitalhouse.digitaltestes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import static br.com.digitalhouse.digitaltestes.util.AppUtil.validateEmail;
import static br.com.digitalhouse.digitaltestes.util.AppUtil.validatePassword;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        String email = ((TextInputEditText) findViewById(R.id.edittext_user_login)).getText().toString();
        String password = ((TextInputEditText) findViewById(R.id.edittext_password_login)).getText().toString();

        if (validateEmail(email) && validatePassword(password)) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            Toast.makeText(this, "Email ou senha invalidos!", Toast.LENGTH_SHORT).show();
        }
    }

    public void loginWithMyFacebookButtom(View view) {
    }
}
