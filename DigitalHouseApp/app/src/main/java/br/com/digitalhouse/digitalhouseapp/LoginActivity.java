package br.com.digitalhouse.digitalhouseapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button botaoLogin = findViewById(R.id.button_login);

        final TextInputEditText email = findViewById(R.id.txt_email);
        final TextInputEditText password = findViewById(R.id.txt_password);

        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), HomeActivity.class);

                Bundle bundle =  new Bundle();

                String emailString = email.getText().toString();
                String passwordString = password.getText().toString();

                if (emailString.equals("fabio@digital.com") &&
                        passwordString.equals("senha")) {
                    bundle.putString("email", emailString);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "Usuário e/ou senha inválido(s)", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
