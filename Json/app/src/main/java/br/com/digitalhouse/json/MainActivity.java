package br.com.digitalhouse.json;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.json.adapters.RecyclerViewNewsAdapter;
import br.com.digitalhouse.json.model.News;
import br.com.digitalhouse.json.model.NewsResponse;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btnGetData;
    private RecyclerViewNewsAdapter adapter;
    List<News> newsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview_news);
        btnGetData = findViewById(R.id.btnGetData);

        adapter = new RecyclerViewNewsAdapter(newsList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    //Stream para ler o arquivo
                    AssetManager manager = getAssets();
                    InputStream newJson = null;
                    newJson = manager.open("news.json");

                    BufferedReader bufferedReaderIn = new BufferedReader(new InputStreamReader(newJson));

                    //Cria um objeto Gson para analisar o JSON de forma simples
                    Gson gson = new Gson();

                    NewsResponse response = gson.fromJson(bufferedReaderIn, NewsResponse.class);

                    adapter.update(response.getNews());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
