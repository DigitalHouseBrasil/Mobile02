  package leo.dh.primeiroprojetoudemy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

  public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void alterarTexto (View view){

        TextView texto = (TextView) findViewById(R.id.textoExibicao);
        texto.setText("Texto alterado");

    }

}
