package br.com.digitalhouse.retofitpagination;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int pag = 1;
    private int totalPages = 20;
    private TextView txtMovies;
    private RecyclerView rcvMovies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMovies = findViewById(R.id.txtMovies);
        rcvMovies = findViewById(R.id.rcvMovies);
    }
}
