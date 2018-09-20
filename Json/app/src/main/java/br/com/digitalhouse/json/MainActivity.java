package br.com.digitalhouse.json;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import br.com.digitalhouse.json.model.News;
import br.com.digitalhouse.json.model.NewsResponse;

public class MainActivity extends AppCompatActivity {

    private TextView txtData;
    private Button btnGetData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtData = findViewById(R.id.txtData);
        btnGetData = findViewById(R.id.btnGetData);

        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    //Stream para ler o arquivo
                    AssetManager manager = getAssets();
                    InputStream newJson = null;
                    newJson = manager.open("news.json");

                    BufferedReader bufferedReaderIn= new BufferedReader(new InputStreamReader(newJson));

                    //Cria um objeto Gson para analisar o JSON de forma simples
                    Gson gson = new Gson();

                    NewsResponse response = gson.fromJson(bufferedReaderIn, NewsResponse.class);

                    News noticia = response.getNews().get(0);

                    txtData.setText(noticia.getDescription());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
